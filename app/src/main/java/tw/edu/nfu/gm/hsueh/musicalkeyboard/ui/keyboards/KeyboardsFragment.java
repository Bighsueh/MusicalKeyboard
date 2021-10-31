package tw.edu.nfu.gm.hsueh.musicalkeyboard.ui.keyboards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import tw.edu.nfu.gm.hsueh.musicalkeyboard.R;

public class KeyboardsFragment extends Fragment {

    private KeyboardsViewModel keyboardsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        keyboardsViewModel =
                new ViewModelProvider(this).get(KeyboardsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_keyboard, container, false);
        final TextView textView = root.findViewById(R.id.text_keyboards);
        keyboardsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}