package tw.edu.nfu.gm.hsueh.musicalkeyboard.ui.home;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tw.edu.nfu.gm.hsueh.musicalkeyboard.R;

class Data{
    int id;
    String name;
    String melody;
    public Data(int i,String n,String m){
        id = i;
        name = n;
        melody = m;
    }
}
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView rcv_music;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;


    private LinkedList<Data> testData = new LinkedList<Data>() {
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        cData();
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rcv_music = root.findViewById(R.id.rcv_music);
        rcv_music.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(root.getContext());
        rcv_music.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter();
        rcv_music.setAdapter(mAdapter);


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return root;
    }

    OkHttpClient client = new OkHttpClient().newBuilder().build();


    private void cData(){

        Request request = new Request.Builder()
                .url("https://f7ac-2001-288-6004-36-6c9e-f855-de1f-69c3.ngrok.io/api/getMelody")
                .build();

        // 建立Call
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                failed
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String result = response.body().string();
                Log.d("OkHttp result", result);
                result = result.replace("[","");
                result = result.replace("]","");
                result = result.replace("},{",";");
                result = result.replace("}","");
                result = result.replace("{","");
                result = result.replace("\"id\":","");
                result = result.replace("\"name\":","");
                result = result.replace("\"melody\":","");
                result = result.replace("\"","");
                Log.d("OkHttp result",result);
                String[] str = result.split(";");
                for(String s:str){
                    String[] ss = s.split(",");
                    Log.d("OkHttp result",ss[0]);
                    Data d = new Data(Integer.parseInt(ss[0]),ss[1],ss[2]);
                    testData.add(d);
                }


            }
        });

    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



        public class MyViewHolder extends RecyclerView.ViewHolder {
            public View itemView;
            public TextView name;
            public ImageButton play;
            public MyViewHolder(@NonNull View v) {
                super(v);
                itemView = v;
                name = itemView.findViewById(R.id.tv_musicname);
                play = itemView.findViewById(R.id.btn_musicplay);

            }
        }
        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View item_music = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_music,parent,false);

            MyViewHolder item = new MyViewHolder(item_music);

            return item;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.name.setText(String.valueOf(testData.get(position).name));



            holder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    play_music(testData.get(holder.getAdapterPosition()).name);

                }
            });
        }

        @Override
        public int getItemCount() {
            return testData.size();
        }



    }

    int[] raw_music = {R.raw.turmpetc,R.raw.turmpetd,R.raw.turmpete
            ,R.raw.turmpetf,R.raw.turmpetg,R.raw.turmpeta,R.raw.turmpetb,R.raw.turmpet0c,R.raw.turmpet0d,R.raw.turmpet0e};
    public void play_music(String s){
        int slen = s.length();

       int count = 0;
       for (int i = 0;i<slen;i++){
           MediaPlayer player = MediaPlayer.create(getContext()
                   ,raw_music[Integer.parseInt(String.valueOf(s.charAt(i)))]);
            player.start();
            do{
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {

                    }
                });
            }while (player.isPlaying());
           player.stop();
           player.release();
       }


    }





}