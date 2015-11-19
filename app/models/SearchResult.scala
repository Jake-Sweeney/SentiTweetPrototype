package models

import play.api.libs.json.Json

/**
 * Created by Jake on 18/11/2015.
 */

case class SearchResult (tweet: String)

object SearchResult {
  implicit val resultFormat = Json.format[SearchResult]
}
