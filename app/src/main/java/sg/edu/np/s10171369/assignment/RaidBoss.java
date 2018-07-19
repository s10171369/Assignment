package sg.edu.np.s10171369.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.ImageView;

public class RaidBoss extends AppCompatActivity {

    //Toolbar bossNameText = (Toolbar) findViewById(R.id.toolBar);
    //ImageView bossImageView = findViewById(R.id.BossimageSwitcher);
    //TextView raidNameText;
    //TextView bossDescText;
    //RecyclerView skillView;
    //RecyclerView.LayoutManager layoutManager;
    //RecyclerView heroView;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_boss);

        Toolbar bossNameText = findViewById(R.id.toolbar);
        ImageView bossImageView = findViewById(R.id.app_bar_image);
        TextView raidNameText = findViewById(R.id.RaidNameTextView);
        TextView bossDescText = findViewById(R.id.BossDescTextSwitcher);
        RecyclerView skillView = findViewById(R.id.skillView);
        RecyclerView heroView = findViewById(R.id.heroView);

        skillView.setItemAnimator(new DefaultItemAnimator());
        skillView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter skillsAdapter = new RaidBossSkillsAdapter(this, RaidGuide.TurtleSkill);
        skillView.setAdapter(skillsAdapter);

        heroView.setItemAnimator(new DefaultItemAnimator());
        heroView.setLayoutManager(new AutoFitGridLayoutManager(this, 100));
        RecyclerView.Adapter heroesAdapter = new RaidBossHeroesAdapter(this, HeroGuide.data);
        heroView.setAdapter(heroesAdapter);



    }
}
