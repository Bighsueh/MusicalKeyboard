package tw.edu.nfu.gm.hsueh.musicalkeyboard.ui.keyboards;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.io.IOException;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tw.edu.nfu.gm.hsueh.musicalkeyboard.R;

public class KeyboardsFragment extends Fragment {

    private Button musicbutton1, musicbutton2, musicbutton3, musicbutton4, musicbutton5, musicbutton6, musicbutton7, musicbutton8, musicbutton9, musicbutton10;
    private KeyboardsViewModel keyboardsViewModel;
    private MediaPlayer mp1, mp2, mp3, mp4, mp5, mp6, mp7, mp8, mp9, mp10;
    private String melody, temp_melody;


    private class Data {
        int id;
        String name;
        String melody;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        keyboardsViewModel = new ViewModelProvider(this).get(KeyboardsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_keyboard, container, false);

        // 建立OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Button btn_record = (Button) root.findViewById(R.id.btn_record);
        Button btn_replay = (Button) root.findViewById(R.id.btn_replay);
        Button btn_upload = (Button) root.findViewById(R.id.btn_upload);

        //數字代號對應的檔名：
        //1->turmpetc
        //2->turmpetd
        //3->turmpete
        //4->turmpetf
        //5->turmpetg
        //6->turmpeta
        //7->turmpetb
        //8->turmpet0c
        //9->turmpet0d
        //10->turmpet0e

        MediaPlayer mp1 = MediaPlayer.create(root.getContext(), R.raw.turmpetc);
        MediaPlayer mp2 = MediaPlayer.create(root.getContext(), R.raw.turmpetd);
        MediaPlayer mp3 = MediaPlayer.create(root.getContext(), R.raw.turmpete);
        MediaPlayer mp4 = MediaPlayer.create(root.getContext(), R.raw.turmpetf);
        MediaPlayer mp5 = MediaPlayer.create(root.getContext(), R.raw.turmpetg);
        MediaPlayer mp6 = MediaPlayer.create(root.getContext(), R.raw.turmpeta);
        MediaPlayer mp7 = MediaPlayer.create(root.getContext(), R.raw.turmpetb);
        MediaPlayer mp8 = MediaPlayer.create(root.getContext(), R.raw.turmpet0c);
        MediaPlayer mp9 = MediaPlayer.create(root.getContext(), R.raw.turmpet0d);
        MediaPlayer mp10 = MediaPlayer.create(root.getContext(), R.raw.turmpet0e);

        musicbutton1 = (Button) root.findViewById(R.id.musicbutton1);
        musicbutton2 = (Button) root.findViewById(R.id.musicbutton2);
        musicbutton3 = (Button) root.findViewById(R.id.musicbutton3);
        musicbutton4 = (Button) root.findViewById(R.id.musicbutton4);
        musicbutton5 = (Button) root.findViewById(R.id.musicbutton5);
        musicbutton6 = (Button) root.findViewById(R.id.musicbutton6);
        musicbutton7 = (Button) root.findViewById(R.id.musicbutton7);
        musicbutton8 = (Button) root.findViewById(R.id.musicbutton8);
        musicbutton9 = (Button) root.findViewById(R.id.musicbutton9);
        musicbutton10 = (Button) root.findViewById(R.id.musicbutton10);

        musicbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_melody += "1";
                mp1.start();
            }
        });
        musicbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_melody += "2";
                mp2.start();
            }
        });
        musicbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_melody += "3";
                mp3.start();
            }
        });
        musicbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_melody += "4";
                mp4.start();
            }
        });
        musicbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_melody += "5";
                mp5.start();
            }
        });
        musicbutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_melody += "6";
                mp6.start();
            }
        });
        musicbutton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_melody += "7";
                mp7.start();
            }
        });
        musicbutton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_melody += "8";
                mp8.start();
            }
        });
        musicbutton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_melody += "9";
                mp9.start();
            }
        });
        musicbutton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_melody += "0";
                mp10.start();
            }
        });

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_record.getText() == "RECORD") {
                    temp_melody = "";
                    btn_record.setText("RECORDING");
                } else {
                    melody = temp_melody;
                    btn_record.setText("RECORD");
                }
            }
        });
        btn_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(
                        root.getContext(),
                        melody,
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(melody!=""){
                    String url = "https://c91a-2001-288-6004-36-6c9e-f855-de1f-69c3.ngrok.io/api/";
                    url += melody;
                    // 建立Request，設置連線資訊
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    // 建立Call
                    Call call = client.newCall(request);

                    // 執行Call連線到網址
                    call.enqueue(new Callback() {


                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                        }

                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                    });
                    melody = "";
                }

            }
        });

        keyboardsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);


            }
        });
        return root;
    }


}