package dev.trindadedev.lixhub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import dev.trindadedev.lixhub.databinding.FragmentHomeBinding;

public class HomeFragmentActivity extends Fragment {

  private FragmentHomeBinding binding;

  @NonNull
  @Override
  public View onCreateView(
      @NonNull LayoutInflater layoutInflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(layoutInflater, container, false);
    return binding.getRoot();
  }
}
