package boot

import javax.inject._

import chat.ChatService
import com.google.inject.AbstractModule
import org.atmosphere.play.AtmosphereCoordinator._
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

/**
  * Created by kasasae on 18/10/2016.
  */

@Singleton
class ChatLoader @Inject()(lifecycle: ApplicationLifecycle) {

  def initialize(): Unit = {
    instance.discover(classOf[ChatService]).ready
  }

  /**
    * Shut down Atmosphere when application closes
    */
  lifecycle.addStopHook { () =>
    Future.successful {
      instance.shutdown
    }
  }

  initialize()
}


class ChatModule extends AbstractModule {
  protected def configure: Unit = {
    bind(classOf[ChatLoader]).asEagerSingleton()
  }
}
