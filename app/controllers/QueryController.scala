package controllers

import models.{SearchResult, SearchResultDB, QueryText}
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json
import twitter4j.{Query, Status}
import play.api.mvc.{Action, Controller}

import scala.collection.JavaConversions._

/**
 * Created by Jake on 18/11/2015.
 */
class QueryController extends TwitterInstance with Controller {

  val queryForm: Form[QueryText] = Form {
    mapping(
      "query" -> text
    )(QueryText.apply)(QueryText.unapply)
  }

  def querySearch() = Action { implicit request =>
      val tweets: List[twitter4j.Status] = twitter.search(new Query(queryForm.bindFromRequest.get.queryText)).getTweets.toList
      println(s"collected ${tweets.size} tweets.")
      saveResults(tweets)
      Redirect(routes.Application.index())
  }

  def saveResults(tweets: List[twitter4j.Status]): Unit = {
    for{tweet: twitter4j.Status <- tweets} yield {
      val resultTweet: SearchResult = new SearchResult(tweet.getUser.getScreenName + "\n\t" + tweet.getText + "\n" + tweet.getCreatedAt.toString)
      SearchResultDB.save(resultTweet)
    }
  }

  def getQueryResults = Action {
      val searchResults = SearchResultDB.query[SearchResult].fetch
      Ok(Json.toJson(searchResults))
  }

}
