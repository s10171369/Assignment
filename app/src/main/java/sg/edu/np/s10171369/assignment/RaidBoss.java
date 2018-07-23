package sg.edu.np.s10171369.assignment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class RaidBoss extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    public RaidBossDataModel boss = RaidGuide.data.get(RaidGuide.indexPosition);
    public int difficulty = boss.getHardDifficulty();

    public ImageView bossImageView;
    public TextView raidNameText;
    public TextView bossName;
    public TextView bossDescText;
    public Toolbar toolbar;
    public CollapsingToolbarLayout collapsingToolbarLayout;
    public RecyclerView skillView;
    public RecyclerView.Adapter skillsAdapter;
    public RecyclerView heroView;
    public RecyclerView.Adapter heroesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_boss);

        bossImageView = findViewById(R.id.app_bar_image);
        bossImageView.setImageResource(boss.getBossImage());

        raidNameText = findViewById(R.id.RaidNameTextView);
        raidNameText.setText(boss.getRaidName());

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(boss.getBossName());

        bossName = findViewById(R.id.BossNameTextView);
        bossName.setVisibility(bossImageView.GONE);

        bossDescText = findViewById(R.id.BossDescTextSwitcher);
        bossDescText.setText(boss.getBossDescription());

        skillView = findViewById(R.id.skillView);
        skillView.setItemAnimator(new DefaultItemAnimator());
        skillView.setLayoutManager(new LinearLayoutManager(this));
        skillsAdapter = new RaidBossSkillsAdapter(this, RaidGuide.TurtleSkill);
        skillView.setAdapter(skillsAdapter);

        heroView = findViewById(R.id.heroView);
        heroesAdapter = new RaidBossHeroesAdapter(this, boss.getRecommendedHeroes());
        heroView.setAdapter(heroesAdapter);
        heroView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager heroViewLayout = new GridLayoutManager(this, 3);
        heroView.setLayoutManager(heroViewLayout);

        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.normal_mode);

        skillView.setFocusable(false);
        bossDescText.requestFocus();

        final AppBarLayout appBar = findViewById(R.id.appbar);
        appBar.addOnOffsetChangedListener(this);

        Switch switchMode = findViewById(R.id.switchMode);
        switchMode.setChecked(false);
        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //Selected Hard Mode
                    bossImageView.setImageResource(0);
                    raidNameText.setText(null);
                    collapsingToolbarLayout.setTitle(null);
                    bossDescText.setText(null);
                    skillView.setAdapter(null);
                    heroView.setAdapter(null);
                }
                else {
                    bossImageView.setImageResource(boss.getBossImage());
                    raidNameText.setText(boss.getRaidName());
                    collapsingToolbarLayout.setTitle(boss.getBossName());
                    bossDescText.setText(boss.getBossDescription());
                    skillView.setAdapter(skillsAdapter);
                    heroView.setAdapter(heroesAdapter);
                }
            }
        });

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if(Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange())
        {
            //Collapsed
            startAlphaAnimation(toolbar, 0, View.VISIBLE);
        }
        else if(verticalOffset ==0)
        {
            //Expanded
            startAlphaAnimation(toolbar, 0, View.INVISIBLE);
        }
        else{
            startAlphaAnimation(toolbar, 0, View.INVISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.normal_mode, menu);
        return true;
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
