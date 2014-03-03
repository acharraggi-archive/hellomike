package com.mikesilversides.hellomike

import org.scalatra._
import scalate.ScalateSupport
// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

// Swagger support
import org.scalatra.swagger._

class HelloMikeServlet(implicit val swagger: Swagger) extends HellomikeStack with NativeJsonSupport 
  with AuthenticationSupport
  /* with JacksonJsonSupport */      with SwaggerSupport {

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit val jsonFormats: Formats = DefaultFormats

  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  override protected val applicationName = Some("hellomike")
  protected val applicationDescription = "This is the hello API. It exposes one operation that says responds with 'world'."

  val getHello = 
    (apiOperation[Response]("getHello")
      summary "Get Hello message"
      notes "Gets the hello message.")

  get("/hello",operation(getHello)) {
    basicAuth
    ResponseData.msg
    //<html>
    //  <body>
    //    <h1>Hello, world!</h1>
    //    Say <a href="hello-scalate">hello to Scalate</a>.
    //  </body>
    //</html>
  }
  
}



// A Response object to use as a faked-out data model
case class Response(message: String)

object ResponseData {

  /**
   * a fake response so we can simulate retrievals.
   */
  var msg = 
      Response("world")
}
