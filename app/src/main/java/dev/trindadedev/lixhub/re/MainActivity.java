package dev.trindadedev.lixhub.re;

import androidx.annotation.NonNull;
import android.os.Bundle;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import dev.trindadedev.lixhub.re.dialog.accounts.AccountsSheet;
import dev.trindadedev.lixhub.re.databinding.MainBinding;

public class MainActivity extends AppCompatActivity {

  private MainBinding binding;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    binding = MainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    
    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    getSupportActionBar().setHomeButtonEnabled(false);
    binding.toolbar.setNavigationOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    binding.btnGoogleLogin.setOnClickListener(clickedView -> {
      var sheet = new AccountsSheet(this);
      sheet.setAccountListener((account, position) -> {
        onPicked(account.getName(), account.getEmail(), account.getAvatar(), position);
      });
      sheet.show();
    });
  }

  public void onPicked(
    @NonNull final String name,
    @NonNull final String email,
    @NonNull final String avatar,
    @NonNull final int position
  ) {
    var i = new Intent(this, AfterloginActivity.class);

    i.putExtra("name", name);
    i.putExtra("email", email);
    i.putExtra("avatar", avatar);
    i.putExtra("position", position);

    startActivity(i);
    finish();
  }
}
