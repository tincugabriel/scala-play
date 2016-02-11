package controllers

import play.api._
import play.api.data.{ Form, Forms, Mapping }
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def testFoo(id: String) = Action {
    // This is the supposed list we get from doc service
    val params = List("foo", "bar", "baz")
    Ok(views.html.test(id, params))
  }

  def handlePost = Action { implicit request => {
    val data = Re.form.bindFromRequest.data
    println(data)
    Ok(data.map(x => x._1+"~~"+x._2).fold("")((x,y) => x+"\n"+y))
  }
  }

  case class Re(urls: String)
  object Re {
    import Forms.{ mapping, list, text }
    val form = Form(mapping (
      "ignore" -> text
    )(Re.apply)(Re.unapply))
  }
}
