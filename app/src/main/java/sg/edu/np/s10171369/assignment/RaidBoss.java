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

    RaidBossDataModel boss = RaidGuide.data.get(RaidGuide.indexPosition);
    int difficulty = boss.getHardDifficulty();

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_boss);

        ImageView bossImageView = findViewById(R.id.app_bar_image);
        bossImageView.setImageResource(boss.getBossImage());

        TextView raidNameText = findViewById(R.id.RaidNameTextView);
        raidNameText.setText(boss.getRaidName());

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
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

        skillView.setFocusable(false);
        raidNameText.requestFocus();

        RecyclerView.Adapter heroAdapter = new RaidBossHeroesAdapter(this, boss.getRecommendedHeroes());
        heroView.setAdapter(heroAdapter);
        //heroView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager heroViewLayout = new GridLayoutManager(this, 3);
        heroView.setLayoutManager(heroViewLayout);

        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.normal_mode);

        final AppBarLayout appBar = findViewById(R.id.appbar);
        appBar.addOnOffsetChangedListener(this);

        Switch switchMode = findViewById(R.id.switchMode);
        switchMode.setChecked(false);
        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //Selected Hard Mode
                }
                else {
                    //Selected Normal Mode
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
