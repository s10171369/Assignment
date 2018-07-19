package sg.edu.np.s10171369.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class RaidBossHeroesAdapter extends RecyclerView.Adapter<RaidBossHeroesAdapter.ViewHolder>{
    List<HeroDataModel> heroList;
    Context context;

    public RaidBossHeroesAdapter(Context src, List<HeroDataModel> heroes) {
        heroList = heroes;
        context = src;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;
        public TextView heroName;

        public ViewHolder(View v) {
            super(v);
            relativeLayout = v.findViewById(R.id.relativeLayout);
        }

        public void setData(HeroDataModel item) {
            relativeLayout.setBackgroundResource(item.getHeroImage());
        }
    }

    @Override
    public RaidBossHeroesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position){
        Vholder.setData(heroList.get(position));
    }

    @Override
    public int getItemCount(){
        return heroList.size();
    }
}