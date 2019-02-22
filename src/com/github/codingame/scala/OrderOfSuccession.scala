package com.github.codingame.scala

import scala.collection.mutable
import scala.io.StdIn
import scala.math._

object OrderOfSuccession extends App {
  val n = StdIn.readInt()
  val personsMap: mutable.HashMap[String, Person] = mutable.HashMap.empty
  val head :: tail = personsList

  for (i <- 0 until n) {
    val Array(name, parent, _birth, death, religion, gender) = StdIn.readLine() split " "
    val birth = _birth.toInt
    val isDead = if (death == "-") false else true
    val person = new Person(name, parent, birth, isDead, religion, gender)
    personsMap(person.name) = person
    personsList = person :: personsList
  }
  val ancestor = personsList.filter(_.parent == "-")
  Person.createLinks(head, tail, personsMap)
  var personsList = List.empty[Person]

  if (!ancestor.head.isDead)
    println(ancestor.head.name)

  Person.printOrderOfSuccession(ancestor.head.children.toList)

}

case class Person(name: String, parent: String, birth: Int, isDead: Boolean, religion: String, gender: String) extends Ordered[Person] {

  val children = new mutable.TreeSet[Person]()

  override def compare(p: Person): Int = {

    if (parent == "-")
      -1
    else if (p.parent == "-")
      1
    else if (gender == p.gender) {
      if (birth < p.birth)
        -1
      else if (birth > p.birth) 1
      else 0
    }
    else if (gender == "M" && p.gender == "F")
      -1
    else
      1
  }
}

object Person {

  def createLinks(person: Person, persons: List[Person], personsMap: mutable.HashMap[String, Person]): Unit = {

    if (person != null) {
      if (person.parent != "-") {
        val parent = personsMap.get(person.parent)
        parent match {
          case Some(p: Person) => p.children += person
          case None => System.err.println(person + "has not a parent!")
        }

      }
      if (persons.nonEmpty) {
        val nextPerson :: tail = persons
        createLinks(nextPerson, tail, personsMap)
      }
    }
  }

  def printOrderOfSuccession(siblings: List[Person]): Unit = {

    for (sibling <- siblings) {
      if (!sibling.isDead && sibling.religion == "Anglican")
        println(sibling.name)
      printOrderOfSuccession(sibling.children.toList)
    }
  }
}






