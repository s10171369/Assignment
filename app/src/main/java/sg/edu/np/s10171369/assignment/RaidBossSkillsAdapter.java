package sg.edu.np.s10171369.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RaidBossSkillsAdapter extends RecyclerView.Adapter<RaidBossSkillsAdapter.ViewHolder> {
    List<Skill> skillList;
    Context context;

    public RaidBossSkillsAdapter(Context src, List<Skill> skills){
        context = src;
        skillList = skills;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView skillImage;
        TextView nameText;
        TextView cooldownText;
        TextView descText;
        TextView skillLight;
        TextView skillDark;

        public ViewHolder(View v){
            super(v);
            skillImage = v.findViewById(R.id.SkillImageView);
            nameText = v.findViewById(R.id.TitleTextView);
            cooldownText = v.findViewById(R.id.CoolDownTextView);
            descText = v.findViewById(R.id.DescTextView);
            skillLight = v.findViewById(R.id.LightTextView);
            skillDark = v.findViewById(R.id.DarkTextView);
        }

        public void setData(int index){
            Skill skill = skillList.get(index);
            skillImage.setImageBitmap(skill.getSkillImage());
            nameText.setText(skill.getSkillName());
            cooldownText.setText(skill.getSkillCooldown() + " Seconds");
            descText.setText(skill.getSkillDescription());
            skillLight.setVisibility(View.GONE);
            skillDark.setVisibility(View.GONE);
            //skillLight.setText(null);
            //skillDark.setText(null);
        }
    }

    @Override
    public RaidBossSkillsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_skills, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(position);
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

}
