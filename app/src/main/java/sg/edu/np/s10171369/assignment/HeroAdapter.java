package sg.edu.np.s10171369.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {
    public List<Skill> skillList;
    Context context;
    public HeroAdapter(Context src, List<Skill> list) {
        context = src;
        skillList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView heroImage;
        public TextView nameText;
        public TextView descText;
        public TextView cooldownText;
        public TextView lightText;
        public TextView darkText;

        public ViewHolder(View v) {
            super(v);
            heroImage = v.findViewById(R.id.SkillImageView);
            nameText = v.findViewById(R.id.TitleTextView);
            descText = v.findViewById(R.id.DescTextView);
            cooldownText = v.findViewById(R.id.CoolDownTextView);
            lightText = v.findViewById(R.id.LightTextView);
            darkText = v.findViewById(R.id.DarkTextView);
        }

        public void setData(int index) {
           Skill skill = skillList.get(index);
           heroImage.setImageBitmap(skill.getSkillImage());
           nameText.setText(skill.getSkillName());
           descText.setText(skill.getSkillDescription());
           cooldownText.setVisibility(itemView.GONE);
           lightText.setText("Light Perk\n" + skill.getSkillLight());
           darkText.setText("Dark Perk\n"  + skill.getSkillDark());
        }
    }

    @Override
    public HeroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_skills, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position){
        Vholder.setData(position);
    }

    @Override
    public int getItemCount(){
        return skillList.size();
    }

}
