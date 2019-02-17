package scala

object HorseRacingDuals extends App {

  val n = readInt
  var horses = Vector.empty[Int]
  for (i <- 0 until n) {
    val pi = readInt
    horses = horses :+ pi.toInt
  }
  val sortedHorses = horses.sorted

  val differences = for (i <- 0 until sortedHorses.size - 1)
    yield sortedHorses(i + 1) - sortedHorses(i)

  println(differences.min)
}
