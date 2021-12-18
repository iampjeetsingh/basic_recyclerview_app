package io.github.iampjeetsingh.assignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.github.iampjeetsingh.assignment.databinding.UserRowBinding
import io.github.iampjeetsingh.assignment.models.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    val users: ArrayList<User> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return  users.size
    }

    class UserViewHolder(private val viewBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: User) {
            (viewBinding as UserRowBinding).user = item
            viewBinding.executePendingBindings()
        }
    }
}