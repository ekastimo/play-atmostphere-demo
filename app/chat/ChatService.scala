package chat

import java.io.IOException

import org.atmosphere.config.service.{Disconnect, ManagedService, Ready}
import org.atmosphere.cpr.{AtmosphereResource, AtmosphereResourceEvent}
import play.api.Logger


/**
  * Simple annotated class that demonstrate the power of Atmosphere. This class supports all transports, support
  * message length garantee, heart beat, message cache thanks to the @managedAService.
  */
@ManagedService(path = "/chat")
class ChatService {
  private val logger: Logger = Logger(classOf[ChatService])

  /**
    * Invoked when the connection as been fully established and suspended, e.g ready for receiving messages.
    *
    * @param r
    */
  @Ready def onReady(r: AtmosphereResource) {
    logger.info(s"Browser ${r.uuid} connected.")
  }

  /**
    * Invoked when the client disconnect or when an unexpected closing of the underlying connection happens.
    *
    * @param event
    */
  @Disconnect def onDisconnect(event: AtmosphereResourceEvent) {
    if (event.isCancelled) logger.info(s"Browser ${event.getResource.uuid} unexpectedly disconnected")
    else if (event.isClosedByClient) logger.info(s"Browser ${event.getResource.uuid} closed the connection")
  }

  /**
    * Simple annotated Method that demonstrate how
    * [[org.atmosphere.config.managed.Encoder]] and  [[org.atmosphere.config.managed.Decoder]]
    * can be used.
    *
    * @param message an instance of [[Message]]
    * @return
    * @throws IOException In case the encoder is not able to parse the string
    */
  @org.atmosphere.config.service.Message(encoders = Array(classOf[JacksonEncoder]), decoders = Array(classOf[JacksonDecoder]))
  @throws[IOException]
  def onMessage(message: Message): Message = {
    logger.info(s"${message.author} just send ${message.message}")
    message
  }
}