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

public class RaidBossHeroesAdapter extends RecyclerView.Adapter<RaidBossHeroesAdapter.ViewHolder>{

    List heroList;
    Context context;

    public RaidBossHeroesAdapter(Context src, List heroes) {
        heroList = heroes;
        context = src;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;
        public ImageView imageView;
        public TextView textView;
        HeroDataModel item;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
            textView = v.findViewById(R.id.textView);
            relativeLayout = v.findViewById(R.id.relativeLayout);
        }

        public void setData(HeroDataModel hero) {
            this.item = hero;

            imageView.setImageBitmap(item.getHeroImage());
        }
    }

    @Override
    public RaidBossHeroesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position){
        Vholder.setData((HeroDataModel) heroList.get(position));
    }

    @Override
    public int getItemCount(){
        return heroList.size();
    }
}