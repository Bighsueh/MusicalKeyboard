package tw.edu.nfu.gm.hsueh.musicalkeyboard.ui.keyboards;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KeyboardsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KeyboardsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}