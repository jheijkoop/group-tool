package models

import java.io.{PrintWriter, File}

object Meetups {
  type Meetup = (String, String)
  val storeName = "store.csv"
  private var meetups = fromDisk

  def fromDisk: Map[String, String] = {
    Option(scala.io.Source.fromFile(storeName))
      .map { store =>
        store
          .mkString
          .split('\n')
          .filter(_.trim.nonEmpty)
          .map(row => row.split(',').toList)
          .flatMap(values => values.headOption.zip(values.tail.headOption))
          .toMap
      }
        .getOrElse(Map.empty)
  }

  def add(meetup: Meetup) = {
    meetups = meetups + meetup

    val store = new PrintWriter(storeName)
    try {
      store.write(
        meetups
          .map(meetup => List(meetup._1, meetup._2).mkString(","))
          .mkString("\n")) + "\n"
    } finally {
      store.close()
    }
  }

  def list = meetups
}
