package sg.edu.np.s10171369.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RaidGuideAdapter extends RecyclerView.Adapter<RaidGuideAdapter.ViewHolder> {
    List<RaidBossDataModel> dataSet;
    Context context;

    public RaidGuideAdapter(Context src, List<RaidBossDataModel> values) {
        context = src;
        dataSet = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View v){
            super(v);
            imageView = v.findViewById(R.id.imageView);
            textView = v.findViewById(R.id.textView);
        }

        public void setData(RaidBossDataModel item){
            imageView.setImageResource(item.getBossImage());
            textView.setText(item.getBossName());
        }
    }

    @Override
    public RaidGuideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cards_layout, parent, false);
        view.setOnClickListener(RaidGuide.myOnClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
