package dev.trindadedev.lixhub.re;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import dev.trindadedev.lixhub.re.databinding.FragmentHomeBinding;

public class HomeFragmentActivity extends Fragment {

  private FragmentHomeBinding binding;

  @NonNull
  @Override
  public View onCreateView(
    @NonNull LayoutInflater layoutInflater,
    @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState
  ) {
    binding = FragmentHomeBinding.inflate(layoutInflater, container, false);
    return binding.getRoot();
  }
}
