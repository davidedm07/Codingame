package com.github.codingame.scala

import scala.io.StdIn
object HorseRacingDuals extends App {

  val n = StdIn.readInt

  val horses = for (i <- 0 until n; pi = StdIn.readInt) yield pi
  val sortedHorses = horses.sorted
  val differences = for (i <- 0 until sortedHorses.size - 1)
    yield sortedHorses(i + 1) - sortedHorses(i)

  println(differences.min)
}
