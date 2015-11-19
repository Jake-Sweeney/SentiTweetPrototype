package models

import play.api.libs.json.Json

/**
 * Created by Jake on 31/10/2015.
 */

case class Person (name: String)

object Person {
  implicit val personFormat = Json.format[Person]
}