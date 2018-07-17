package sg.edu.np.s10171369.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class HeroGuideAdapter extends RecyclerView.Adapter<HeroGuideAdapter.ViewHolder> {

    List dataSet;
    Context hcontext;

    public HeroGuideAdapter(Context context, List values) {
        dataSet = values;
        hcontext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        HeroDataModel item;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
            textView = v.findViewById(R.id.textView);
            relativeLayout = v.findViewById(R.id.relativeLayout);
        }

        public void setData(HeroDataModel item) {
            this.item = item;

            relativeLayout.setBackgroundResource(item.getHeroImage());
            //imageView.setImageResource(item.getHeroImage());
            textView.setText(item.getHeroName());
        }
    }

    @Override
    public HeroGuideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(hcontext).inflate(R.layout.recycler_view_item, parent, false);

        view.setOnClickListener(HeroGuide.myOnClickListener);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData((HeroDataModel) dataSet.get(position));
    }

    @Override
    public int getItemCount(){
        return dataSet.size();
    }
}
