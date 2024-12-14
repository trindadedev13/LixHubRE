package dev.trindadedev.lixhub.dialog.accounts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.trindadedev.lixhub.R
import dev.trindadedev.lixhub.databinding.AccountLayoutBinding
import com.bumptech.glide.Glide

class AccountsAdapter :
  ListAdapter<Account, AccountsAdapter.AccountsAdapterViewHolder>(AccountDiffCallback()) {

  var accountListener: AccountListener? = null

  class AccountsAdapterViewHolder(
    private val binding: AccountLayoutBinding
  ) : ViewHolder(binding.root) {

    fun bind(account: Account, isSingle: Boolean, isFirst: Boolean, isLast: Boolean) {
      binding.accName.text = account.name
      binding.accEmail.text = account.email
      Glide.with(binding.accAvatar.context).load(account.avatar).into(binding.accAvatar)

      val backgroundResource = when {
        isSingle -> R.drawable.shape_alone
        isFirst -> R.drawable.shape_top
        isLast -> R.drawable.shape_bottom
        else -> R.drawable.shape_middle
      }
      binding.root.setBackgroundResource(backgroundResource)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsAdapterViewHolder {
    val binding = AccountLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return AccountsAdapterViewHolder(binding)
  }

  override fun onBindViewHolder(holder: AccountsAdapterViewHolder, position: Int) {
    val account = getItem(position)
    val isSingle = currentList.size == 1
    val isFirst = position == 0
    val isLast = position == currentList.size - 1
    holder.bind(account, isSingle, isFirst, isLast)

    holder.itemView.setOnClickListener {
      accountListener?.onPicked(account, position)
    }
  }

  private class AccountDiffCallback : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
      return oldItem == newItem
    }
  }
}