import com.mikesilversides.hellomike._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  implicit val swagger = new HelloSwagger

  override def init(context: ServletContext) {
    context.mount(new HelloMikeServlet, "/*")
    context mount (new ResourcesApp, "/api-docs/*")
  }
}
