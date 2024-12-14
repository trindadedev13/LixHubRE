package dev.trindadedev.lixhub.dialog.accounts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import dev.trindadedev.lixhub.R;
import java.util.List;

public class AccountsAdapter
    extends RecyclerView.Adapter<AccountsAdapter.AccountsAdapterViewHolder> {

  private List<Account> accountList;
  private AccountListener accountListener;

  public AccountsAdapter(List<Account> accountList) {
    this.accountList = accountList;
  }

  public static class AccountsAdapterViewHolder extends RecyclerView.ViewHolder {
    final TextView accName;
    final TextView accEmail;
    final ShapeableImageView accAvatar;

    public AccountsAdapterViewHolder(@NonNull View itemView) {
      super(itemView);
      accName = itemView.findViewById(R.id.acc_name);
      accEmail = itemView.findViewById(R.id.acc_email);
      accAvatar = itemView.findViewById(R.id.acc_avatar);
    }
  }

  @NonNull
  @Override
  public AccountsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.account_layout, parent, false);
    return new AccountsAdapterViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull AccountsAdapterViewHolder holder, int position) {
    Account account = accountList.get(position);
    holder.accName.setText(account.getName());
    holder.accEmail.setText(account.getEmail());
    Glide.with(holder.accAvatar.getContext()).load(account.getAvatar()).into(holder.accAvatar);

    int backgroundResource;
    if (accountList.size() == 1) {
      backgroundResource = R.drawable.shape_alone;
    } else if (position == 0) {
      backgroundResource = R.drawable.shape_top;
    } else if (position == accountList.size() - 1) {
      backgroundResource = R.drawable.shape_bottom;
    } else {
      backgroundResource = R.drawable.shape_middle;
    }
    holder.itemView.setBackgroundResource(backgroundResource);

    holder.itemView.setOnClickListener(
        v -> {
          if (accountListener != null) {
            accountListener.onPicked(account, position);
          }
        });
  }

  @Override
  public int getItemCount() {
    return accountList.size();
  }

  public void updateAccounts(List<Account> accountList) {
    this.accountList = accountList;
  }

  public void setAccountListener(AccountListener accountListener) {
    this.accountListener = accountListener;
  }
}
