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

        ImageView bossImageView = findViewById(R.id.app_bar_image);
        bossImageView.setImageResource(boss.getBossImage());

        TextView raidNameText = findViewById(R.id.RaidNameTextView);
        raidNameText.setText(boss.getRaidName());

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(boss.getBossName());

        TextView bossName = findViewById(R.id.BossNameTextView);
        bossName.setVisibility(bossImageView.GONE);

        TextView bossDescText = findViewById(R.id.BossDescTextSwitcher);
        bossDescText.setText(boss.getBossDescription());

        RecyclerView skillView = findViewById(R.id.skillView);
        RecyclerView heroView = findViewById(R.id.heroView);

        skillView.setItemAnimator(new DefaultItemAnimator());
        skillView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter skillsAdapter = new RaidBossSkillsAdapter(this, RaidGuide.TurtleSkill);
        skillView.setAdapter(skillsAdapter);

        //skillView.setFocusable(false);
        //raidNameText.requestFocus();

        //AutoFitGridLayoutManager heroViewLayout = new AutoFitGridLayoutManager(this, 200);
        GridLayoutManager heroViewLayout = new GridLayoutManager(this, 3);
        heroView.setLayoutManager(heroViewLayout);
        RecyclerView.Adapter heroAdapter = new RaidBossHeroesAdapter(this, boss.getRecommendedHeroes());
        heroView.setAdapter(heroAdapter);
        //heroView.setItemAnimator(new DefaultItemAnimator());

        //heroView.setLayoutManager(new LinearLayoutManager(this));

    }
}
