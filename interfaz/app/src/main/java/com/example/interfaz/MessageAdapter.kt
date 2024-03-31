package com.example.interfaz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interfaz.Constants.RECEIVE_ID
import com.example.interfaz.Constants.SEND_ID

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
  var messagesList = mutableListOf<Message>()

  inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvMessage: TextView = itemView.findViewById(R.id.tv_message)
    val tvBotMessage: TextView = itemView.findViewById(R.id.tv_bot_message)
    init {
      itemView.setOnClickListener {

        //Remove message on the item clicked
        messagesList.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
    return MessageViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.mensajes, parent, false)
    )
  }

  override fun getItemCount(): Int {
    return messagesList.size
  }

  // @SuppressLint("SetTextI18n")
  override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
    val currentMessage = messagesList[position]

    when (currentMessage.id) {
      SEND_ID -> {
        holder.tvMessage.apply {
          text = currentMessage.message
          visibility = View.VISIBLE
        }
        holder.tvBotMessage.visibility = View.GONE
      }
      RECEIVE_ID -> {
        holder.tvBotMessage.apply {
          text = currentMessage.message
          visibility = View.VISIBLE
        }
        holder.tvMessage.visibility = View.GONE
      }
    }
  }

  fun insertMessage(message: Message) {
    this.messagesList.add(message)
    notifyItemInserted(messagesList.size)
  }
}