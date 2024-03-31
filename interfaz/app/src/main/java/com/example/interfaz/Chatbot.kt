package com.example.interfaz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interfaz.Constants.RECEIVE_ID
import com.example.interfaz.Constants.SEND_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Chatbot : AppCompatActivity(){
  private lateinit var btnSend:Button
  private lateinit var rvMessages:RecyclerView
  private lateinit var etMessage:EditText

  private lateinit var adapter: MessageAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.chat)

    btnSend = findViewById(R.id.btn_send)
    rvMessages = findViewById(R.id.rv_messages)
    etMessage = findViewById(R.id.et_message)

    recyclerView()

    clickEvents()

    val message = "Bienvenido a SAC-Fútbol Chatbot! ¿En que lo puedo ayudar?"

    adapter.insertMessage(Message(message, RECEIVE_ID))

    rvMessages.scrollToPosition(adapter.itemCount - 1)
  }

  private fun clickEvents() {

    //Send a message
    btnSend.setOnClickListener {
      sendMessage()
    }

    //Scroll back to correct position when user clicks on text view
    etMessage.setOnClickListener {
      GlobalScope.launch {
        delay(100)

        withContext(Dispatchers.Main) {
          rvMessages.scrollToPosition(adapter.itemCount - 1)

        }
      }
    }
  }

  private fun recyclerView() {
    adapter = MessageAdapter()
    rvMessages.adapter = adapter
    rvMessages.layoutManager = LinearLayoutManager(applicationContext)

  }

  override fun onStart() {
    super.onStart()
    //In case there are messages, scroll to bottom when re-opening app
    GlobalScope.launch {
      delay(100)
      withContext(Dispatchers.Main) {
        rvMessages.scrollToPosition(adapter.itemCount - 1)
      }
    }
  }

  private fun sendMessage() {
    val message = etMessage.text.toString()

    if (message.isNotEmpty()) {

      etMessage.setText("")

      adapter.insertMessage(Message(message, SEND_ID))
      rvMessages.scrollToPosition(adapter.itemCount - 1)

      botResponse(message)
    }
  }

  private fun botResponse(message: String) {
    GlobalScope.launch {
      //Fake response delay
      delay(1000)

      withContext(Dispatchers.Main) {
        //Gets the response
        val response = "El bot dice $message"

        //Inserts our message into the adapter
        adapter.insertMessage(Message(response, RECEIVE_ID))

        //Scrolls us to the position of the latest message
        rvMessages.scrollToPosition(adapter.itemCount - 1)

      }
    }
  }
}