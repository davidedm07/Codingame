package com.github.codingame.scala

import scala.io.StdIn

object TheLastCrusade extends App {

  // w: number of columns.
  // h: number of rows.
  val Array(w, h) = for (i <- StdIn.readLine split " ") yield i.toInt
  var levels = scala.collection.mutable.ListBuffer.empty[List[String]]
  for (i <- 0 until h) {
    val line = StdIn.readLine // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
    val list: List[String] = line.split(" ").toList
    levels += list
  }
  val ex = StdIn.readInt // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).
  val exitDown = List(1, 3, 7, 8, 9, 12, 13)
  // game loop
  while (true) {
    val Array(_xi, _yi, pos) = StdIn.readLine split " "
    val xi = _xi.toInt
    val yi = _yi.toInt

    val currentPos: Int = levels(yi)(xi).toInt
    Console.err.println(currentPos)

    currentPos match {
      case 2 => if (pos == "RIGHT") println((xi - 1) + " " + yi) else println((xi + 1) + " " + yi)
      case 4 => if (pos == "TOP") println((xi - 1) + " " + yi) else println(xi + " " + (yi + 1))
      case 5 => if (pos == "TOP") println((xi + 1) + " " + yi) else println(xi + " " + (yi + 1))
      case 6 => if (pos == "RIGHT") println((xi - 1) + " " + yi) else println((xi + 1) + " " + yi)
      case 10 => println((xi - 1) + " " + yi)
      case 11 => println((xi + 1) + " " + yi)
      case _ => println(xi + " " + (yi + 1))
    }

    // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
  }
}
