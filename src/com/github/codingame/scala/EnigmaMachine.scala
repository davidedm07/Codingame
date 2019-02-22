package com.github.codingame.scala

import scala.io.StdIn

/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object EnigmaMachine extends App {

  var operation = StdIn.readLine()
  val pseudorandomnumber = StdIn.readInt

  val rotorsSeq = for(i <- 0 until 3; rotor:String = StdIn.readLine()) yield rotor
  val rotors = rotorsSeq.toList

  val message = StdIn.readLine()

  val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

  val messageFiltered = message.toUpperCase.filter(c => c>= 'A' && c<= 'Z')
  val messageWithIndex = messageFiltered.zipWithIndex

  //starting shift
  val messageAfterShift = if(operation == "ENCODE")messageWithIndex.map(tuple => RotorsMod.getCharacterShift(tuple._1,alphabet,tuple._2,pseudorandomnumber,operation)).mkString else message

  Console.err.println(messageAfterShift)

  //rotors modifications
  val modAfterRotors = if(operation == "ENCODE") RotorsMod.encode(messageAfterShift,rotors,alphabet) else RotorsMod.decode(messageAfterShift,rotors.reverse,alphabet)
  Console.err.println(modAfterRotors)

  val result = if(operation =="DECODE") modAfterRotors.zipWithIndex.map(tuple => RotorsMod.getCharacterShift(tuple._1,alphabet,tuple._2,pseudorandomnumber,operation)).mkString else modAfterRotors

  println(result)
}
object RotorsMod {
  def encode(message: String, rotors: List[String], alphabet: String): String = {
    if (rotors.isEmpty)
      message
    else {
      val rotor :: rest = rotors
      val messageMod = message.map(c => rotor.charAt(alphabet.indexOf(c))).mkString
      encode(messageMod, rest, alphabet)
    }
  }

  def decode(message: String, rotors: List[String], alphabet: String): String = {
    if (rotors.isEmpty)
      message
    else {
      val rotor :: rest = rotors
      val messageDecod = message.map(c => alphabet.charAt(rotor.indexOf(c))).mkString
      decode(messageDecod, rest, alphabet)
    }
  }

  def getCharacterShift(c:Char, alphabet:String,index:Int, number:Int,operation:String):Char = {
    val indexInAlphabet = if(operation=="ENCODE") alphabet.indexOf(c) + index + number else alphabet.indexOf(c) - index - number
    /*var value:Int = indexInAlphabet
    val circleIndex = if(operation=="ENCODE") {
      while(value>25) {
        value = value - 26
      }
      value
    } else {
      while(value<0) {
        value = 26 + value
      }
      value
    }*/
    Console.err.println(indexInAlphabet)
    val circleIndex = if(indexInAlphabet < 0 && Math.abs(indexInAlphabet) != 26) 26 - Math.abs(indexInAlphabet) % 26 else indexInAlphabet % 26
    Console.err.println(circleIndex)
    alphabet.charAt(circleIndex)
  }
}