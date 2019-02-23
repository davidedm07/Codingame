package com.github.codingame.scala

import scala.collection._
import scala.io.StdIn

object Scrabble extends App {
  val n = StdIn.readInt()
  val lettersValue = scala.collection.mutable.HashMap.empty[Char, Int]

  // populating the map with the values for each letter
  "eaionrtlsu".foreach(c => lettersValue += (c ->1))
  "dg".foreach(c => lettersValue += (c ->2))
  "bcmp".foreach(c => lettersValue += (c ->3))
  "fhvwy".foreach(c => lettersValue += (c ->4))
  lettersValue ++= mutable.HashMap('k'->5,'j'->8,'x' ->8, 'z' -> 10, 'q' -> 10)

  Console.err.println(lettersValue)
  val wordScore =  for(_ <- 0 until n; w = StdIn.readLine)
    yield w -> w.map(c => lettersValue(c)).sum

  //TODO fix ordering problem here, multiple possibilities chooses the second word inserted instead of the first ones
  val highestScore = wordScore.toList.map {
    case (w,s) => (s,w)
  }.sorted.reverse

  Console.err.println(highestScore)
  val letters = StdIn.readLine

  val possibleWords = highestScore.map {
    case (_,w) =>
      val inCommon = letters.filter(c => w.contains(c)).length
      if (inCommon == w.length) w

  }
  val solution = possibleWords.filter(_ != ())
  Console.err.println(solution)

  println(solution.head)
}

