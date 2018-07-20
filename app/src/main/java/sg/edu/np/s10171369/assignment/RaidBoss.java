package sg.edu.np.s10171369.assignment;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class RaidBoss extends AppCompatActivity {

    RaidBossDataModel boss = RaidGuide.data.get(RaidGuide.indexPosition);
    int difficulty = boss.getHardDifficulty();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_boss);

        ImageView bossImageView = findViewById(R.id.BossImageView);
        TextView raidNameText = findViewById(R.id.RaidNameTextView);
        TextView bossName = findViewById(R.id.BossNameTextView);
        TextView bossDescText = findViewById(R.id.BossDescTextSwitcher);
        RecyclerView skillView = findViewById(R.id.skillView);
        RecyclerView heroView = findViewById(R.id.heroView);

        bossImageView.setImageResource(boss.getBossImage());
        raidNameText.setText(boss.getRaidName());
        bossName.setText(boss.getBossName());
        bossDescText.setText(boss.getBossDescription());

        skillView.setItemAnimator(new DefaultItemAnimator());
        skillView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter skillsAdapter = new RaidBossSkillsAdapter(this, RaidGuide.TurtleSkill);
        skillView.setAdapter(skillsAdapter);

        skillView.setFocusable(false);
        bossName.requestFocus();

        //AutoFitGridLayoutManager heroViewLayout = new AutoFitGridLayoutManager(this, 200);
        GridLayoutManager heroViewLayout = new GridLayoutManager(this, 3);
        heroView.setLayoutManager(heroViewLayout);
        RecyclerView.Adapter heroAdapter = new RaidBossHeroesAdapter(this, boss.getRecommendedHeroes());
        heroView.setAdapter(heroAdapter);
        //heroView.setItemAnimator(new DefaultItemAnimator());

        //heroView.setLayoutManager(new LinearLayoutManager(this));

    }
}
