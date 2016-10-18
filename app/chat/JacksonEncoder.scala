package chat

import java.io.IOException

import com.fasterxml.jackson.databind.ObjectMapper
import org.atmosphere.config.managed.Encoder

/**
  * Created by kasasae on 18/10/2016.
  */
class JacksonEncoder extends Encoder[Message, String] {
  private val mapper: ObjectMapper = new ObjectMapper
  def encode(m: Message): String = try
    mapper.writeValueAsString(m)
  catch {
    case e: IOException => {
      throw new RuntimeException(e)
    }
  }
}
