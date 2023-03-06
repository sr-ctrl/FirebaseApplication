package com.example.firebaseapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseapplication.databinding.ChatItemBinding
import com.example.firebaseapplication.network.dto.User

class UserListAdapter(val context:Context,val userList:ArrayList<chatRoomData>):RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.senderName.text = userList[position].username
        holder.binding.msg.text = userList[position].msg
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(val binding:ChatItemBinding):RecyclerView.ViewHolder(binding.root)
}

data class chatRoomData(
    val username:String,
    val msg:String
)