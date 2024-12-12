package dev.trindadedev.lixhub.re;

import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import dev.trindadedev.lixhub.re.databinding.AfterLoginBinding;

public class AfterloginActivity extends AppCompatActivity {

  private AfterLoginBinding binding;
  
  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    binding = AfterLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    initializeLogic();
  }

  private void initializeLogic() {
    var isAddToBackStack = false;
    var fragment = new HomeFragmentActivity();
    var transaction = getSupportFragmentManager().beginTransaction();
    transaction.setCustomAnimations(
        R.anim.enter_transition,
        R.anim.exit_transition,
        R.anim.pop_enter_transition,
        R.anim.pop_exit_transition);
    transaction.replace(binding.frame.getId(), fragment);
    if (isAddToBackStack) transaction.addToBackStack(null);
    transaction.commit();
  }

  @Override
  public void onBackPressed() {
    new MaterialAlertDialogBuilder(AfterloginActivity.this)
        .setTitle(getString(R.string.exit))
        .setMessage(getString(R.string.exit_message))
        .setPositiveButton(getString(R.string.exit), (d, w) -> {
          d.dismiss();
          finish();
        })
        .setNegativeButton(getString(R.string.common_word_cancel), (d, w) -> {
          d.dismiss();
        })
        .show();
  }
}
