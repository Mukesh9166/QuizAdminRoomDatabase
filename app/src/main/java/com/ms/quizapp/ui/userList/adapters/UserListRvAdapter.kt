package com.ms.quizapp.ui.userList.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ms.quizapp.R
import com.ms.quizapp.data.network.models.UserDataList
import com.ms.quizapp.databinding.LayoutUserListBinding

class UserListRvAdapter : RecyclerView.Adapter<UserListRvAdapter.ViewHolder> {
    private var context: Context? = null
    private var arrUserList: MutableList<UserDataList> = ArrayList()

    constructor(context: Context?, arrUserList: MutableList<UserDataList>) : super() {
        this.context = context
        this.arrUserList = arrUserList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutUserListBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val userModel = arrUserList[position]

        context?.let {
            Glide.with(it)
                .load(userModel.picture ?: "")
                .placeholder(R.drawable.ic_palaceholder)
                .into(holder.binding.ivUserImg)
        }



        holder.binding.tvUserName.text =
            "${userModel.title} ${userModel.firstName} ${userModel.lastName}"

    }

    fun filteredList(filterList: MutableList<UserDataList>) {
        arrUserList = filterList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return arrUserList.size
    }

    class ViewHolder(itemBinding: LayoutUserListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding = itemBinding
    }

}