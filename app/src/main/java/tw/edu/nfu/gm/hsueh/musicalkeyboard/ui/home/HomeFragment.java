package tw.edu.nfu.gm.hsueh.musicalkeyboard.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

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





        return root;
    }
    private void cData(){
        testData = new LinkedList<>();
        for(int i=0;i<10;i++){
            Data d = new Data(i,String.valueOf(Math.random()),String.valueOf(Math.random()));
            testData.add(d);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



        public class MyViewHolder extends RecyclerView.ViewHolder {
            public View itemView;
            public TextView name;
            public MyViewHolder(@NonNull View v) {
                super(v);
                itemView = v;
                name = itemView.findViewById(R.id.tv_musicname);

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
            holder.name.setText(String.valueOf(testData.get(position).id));
        }

        @Override
        public int getItemCount() {
            return testData.size();
        }



    }

}