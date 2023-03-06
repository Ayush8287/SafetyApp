package com.example.safetyapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberAdapter(private val listMember: List<MemberModel>) : RecyclerView.Adapter<MemberAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.item_member,parent,false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: MemberAdapter.ViewHolder, position: Int) {

        val item = listMember[position]
        holder.userName.text=item.name
    }

    override fun getItemCount(): Int {
        return listMember.size
    }

    class ViewHolder(val item: View): RecyclerView.ViewHolder(item) {

        val imageUser = item.findViewById<ImageView>(R.id.img_user)
        val userName = item.findViewById<TextView>(R.id.user_name)

    }

}