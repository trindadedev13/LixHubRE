package dev.trindadedev.lixhub;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import dev.trindadedev.lixhub.databinding.ActivityMainBinding;
import dev.trindadedev.lixhub.dialog.accounts.AccountsSheet;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    getSupportActionBar().setHomeButtonEnabled(false);

    binding.toolbar.setNavigationOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    binding.btnGoogleLogin.setOnClickListener(
        clickedView -> {
          var sheet = new AccountsSheet(this);
          sheet.setAccountListener(
              (account, position) -> {
                onPicked(account.getName(), account.getEmail(), account.getAvatar(), position);
              });
          sheet.show();
        });
  }

  private void onPicked(
      @NonNull final String name,
      @NonNull final String email,
      @NonNull final String avatar,
      @NonNull final int position) {
    var i = new Intent(this, AfterloginActivity.class);

    i.putExtra("name", name);
    i.putExtra("email", email);
    i.putExtra("avatar", avatar);
    i.putExtra("position", position);

    startActivity(i);
    finish();
  }
}
