package chat

import java.io.IOException

import com.fasterxml.jackson.databind.ObjectMapper
import org.atmosphere.config.managed.Decoder

/**
  * Created by kasasae on 18/10/2016.
  */
class JacksonDecoder extends Decoder[String, Message] {
  private val mapper: ObjectMapper = new ObjectMapper
  def decode(s: String): Message = try
    mapper.readValue(s, classOf[Message])
  catch {
    case e: IOException => {
      throw new RuntimeException(e)
    }
  }
}
