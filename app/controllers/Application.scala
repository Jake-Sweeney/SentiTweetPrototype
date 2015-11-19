package controllers

import models.{Database, Person}
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc._

class Application extends Controller {

  def index = Action {
//    OK == status code 200
    Ok(views.html.index("SentiTweet"))
  }

  val personForm: Form[Person] = Form {
    mapping(
      "name" -> text
    )(Person.apply)(Person.unapply)
  }

  def addPerson = Action { implicit  request =>
    val person: Person =  personForm.bindFromRequest.get
    Database.save(person)
    Redirect(routes.Application.index())
  }

  def getPersons = Action {
    val persons = Database.query[Person].fetch
    Ok(Json.toJson(persons))
  }

}
