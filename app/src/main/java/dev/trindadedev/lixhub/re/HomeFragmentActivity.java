package dev.trindadedev.lixhub.re;

import android.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class HomeFragmentActivity extends Fragment {

  private LinearLayout linear1;
  private RelativeLayout relativelayout1;
  private TextView textview1;

  @NonNull
  @Override
  public View onCreateView(
      @NonNull LayoutInflater _inflater,
      @Nullable ViewGroup _container,
      @Nullable Bundle _savedInstanceState
  ) {
    View _view = _inflater.inflate(R.layout.home_fragment, _container, false);
    initialize(_savedInstanceState, _view);
    return _view;
  }

  private void initialize(Bundle _savedInstanceState, View _view) {
    linear1 = _view.findViewById(R.id.linear1);
    relativelayout1 = _view.findViewById(R.id.relativelayout1);
    textview1 = _view.findViewById(R.id.textview1);
  }
}
