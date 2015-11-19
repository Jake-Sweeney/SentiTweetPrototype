package controllers

import play.api.mvc.{Action, Controller}

/**
 * Created by Jake on 18/11/2015.
 */
class MyController extends Controller {

  def displayMessage = Action {
    Ok(views.html.myPage("What's the craic?"))
  }

}
