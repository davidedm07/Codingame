package scala



/**
  * Auto-generated code below aims at helping you parse
  * the standard input according to the problem statement.
  **/
object Solution extends App {
  val operation = "DECODE"
  val pseudorandomnumber = -7

  var rotors: List[String] = List("BDFHJLCPRTXVZNYEIWGAKMUSQO") // insert into list all the rotors

  val message = "BQCPV"

  val messageFiltered = message.toUpperCase.filter(c => c >= 'A' && c <= 'Z')
  Console.err.println(messageFiltered)
  val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

  val messageWithIndex = messageFiltered.zipWithIndex
  Console.err.println(messageWithIndex)

  //starting shift
  val messageAfterShift = if (operation == "ENCODE") messageWithIndex.map(tuple => RotorsMod.getCharacterShift(tuple._1, alphabet, tuple._2, pseudorandomnumber, operation)).mkString else message

  Console.err.println(messageAfterShift)

  //rotors modifications
  val modAfterRotors = if (operation == "ENCODE") RotorsMod.encode(messageAfterShift, rotors, alphabet) else RotorsMod.decode(messageAfterShift, rotors, alphabet)
  Console.err.println(modAfterRotors)

  val result = if (operation == "DECODE") modAfterRotors.zipWithIndex.map(tuple => RotorsMod.getCharacterShift(tuple._1, alphabet, tuple._2, pseudorandomnumber, operation)).mkString else modAfterRotors

  println("RESULT: " + result)
}

object RotorsMod {
  def encode(message: String, rotors: List[String], alphabet: String): String = {
    if (rotors.isEmpty)
      message
    else {
      val rotor :: rest = rotors
      val messageMod = message.map(c => rotor.charAt(alphabet.indexOf(c))).mkString
      Console.err.println(messageMod)
      encode(messageMod, rest, alphabet)
    }
  }

  def decode(message: String, rotors: List[String], alphabet: String): String = {
    if (rotors.isEmpty)
      message
    else {
      val rotor :: rest = rotors
      val messageDecod = message.map(c => alphabet.charAt(rotor.indexOf(c))).mkString
      Console.err.println(messageDecod)
      decode(messageDecod, rest, alphabet)
    }
  }

  def getCharacterShift(c: Char, alphabet: String, index: Int, number: Int, operation: String): Char = {

    if (c <= 'A' || c >= 'Z')
      c
    else {
      Console.err.println("CHAR " + c)
      val indexInAlphabet = if (operation == "ENCODE") alphabet.indexOf(c) + index + number else alphabet.indexOf(c) - index - number
      var value: Int = indexInAlphabet
      val circleIndex = if (operation == "ENCODE") {
        while (value > 25) {
          value = value - 26
        }
        if (value < 0) value + 26 else value
      } else {
        Console.err.println("Index decode " + value)
        while (value < 0) {
          value = 26 + indexInAlphabet
        }
        if (value > 25) value - 26 else value
      }
      Console.err.println("Index decode after while " + value)
      alphabet.charAt(circleIndex)
    }
  }
}