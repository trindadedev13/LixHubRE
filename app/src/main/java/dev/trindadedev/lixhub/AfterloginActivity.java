package dev.trindadedev.lixhub;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import dev.trindadedev.lixhub.databinding.ActivityAfterLoginBinding;

public class AfterloginActivity extends AppCompatActivity {

  private ActivityAfterLoginBinding binding;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    binding = ActivityAfterLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    var fragment = new HomeFragmentActivity();
    var transaction = getSupportFragmentManager().beginTransaction();
    transaction.setCustomAnimations(
        R.anim.enter_transition,
        R.anim.exit_transition,
        R.anim.pop_enter_transition,
        R.anim.pop_exit_transition);
    transaction.replace(binding.frame.getId(), fragment);
    transaction.commit();
  }

  @Override
  public void onBackPressed() {
    new MaterialAlertDialogBuilder(this)
        .setTitle(getString(R.string.exit))
        .setMessage(getString(R.string.exit_message))
        .setPositiveButton(
            getString(R.string.exit),
            (d, w) -> {
              d.dismiss();
              finish();
            })
        .setNegativeButton(
            getString(R.string.common_word_cancel),
            (d, w) -> {
              d.dismiss();
            })
        .show();
  }
}
