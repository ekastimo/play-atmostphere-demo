package chat

/**
  * Created by kasasae on 18/10/2016.
  */

import java.util.Date

case class Message(var author: String, var message: String) {
  private val time: Long = new Date().getTime
  def this() {
    this("", "")
  }
}
