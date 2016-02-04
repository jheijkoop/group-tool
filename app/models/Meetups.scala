package models

object Meetups {
  type Meetup = (String, String)
  private var meetups = Map.empty[String, String]

  def add(meetup: Meetup) = {
    meetups = meetups + meetup
  }

  def list = meetups
}
