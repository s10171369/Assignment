package sg.edu.np.s10171369.assignment;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class RaidBoss extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    public List<RaidBossDataModel> raidBossList = Guides.raidBossDataList;
    public RaidBossDataModel selectedBoss = RaidGuide.selectedBoss;
    public RaidBossDataModel displayBoss;
    static public boolean hardDifficulty = false;
    public RaidBossDataModel hardBoss;
    public List<HeroDataModel> NormalRecommendedHeroes = new ArrayList<HeroDataModel>();
    public List<HeroDataModel> HardRecommendedHeroes = new ArrayList<HeroDataModel>();

    public ImageView bossImageView;
    public TextView raidNameText;
    public TextView bossName;
    public TextView bossDescText;
    public Toolbar toolbar;
    public CollapsingToolbarLayout collapsingToolbarLayout;
    public RecyclerView skillView;
    public RecyclerView.Adapter normalSkillsAdapter;
    public RecyclerView.Adapter hardSkillsAdapter;
    public RecyclerView heroView;
    public RecyclerView.Adapter normalHeroesAdapter;
    public RecyclerView.Adapter hardHeroesAdapter;

    FragmentActivity listener;

    public RaidBoss(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    //@Override
    //public void onCreate(Bundle savedInstanceState){
    //    super.onCreate(savedInstanceState);
    //    setHasOptionsMenu(true);
    //}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_raid_boss, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        for (int i = 0; i < raidBossList.size(); i++) {
            RaidBossDataModel temp = raidBossList.get(i);
            String tempName = temp.getBossName();
            String selectedName = selectedBoss.getBossName();
            if (tempName.contains(selectedName)) {
                if (temp.getHardDifficulty() != 1) {
                    displayBoss = temp;
                }
                else{
                    hardDifficulty = true;
                }
            }
        }

        DBHandler dbHandler = new DBHandler(getActivity());

        if (hardDifficulty) {
            hardBoss = dbHandler.findHardBoss(displayBoss.getBossName());
            HardRecommendedHeroes = dbHandler.getRecommendedHeroes(hardBoss.getRecommendedHeroes());
        }
        NormalRecommendedHeroes = dbHandler.getRecommendedHeroes(displayBoss.getRecommendedHeroes());

        bossImageView = view.findViewById(R.id.app_bar_image);
        bossImageView.setImageBitmap(displayBoss.getBossImage());

        raidNameText = view.findViewById(R.id.RaidNameTextView);
        raidNameText.setText(displayBoss.getRaidName());

        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(displayBoss.getBossName());

        bossName = view.findViewById(R.id.BossNameTextView);
        bossName.setVisibility(bossImageView.GONE);

        bossDescText = view.findViewById(R.id.BossDescTextSwitcher);
        bossDescText.setText(displayBoss.getBossDescription());

        skillView = view.findViewById(R.id.skillView);
        skillView.setItemAnimator(new DefaultItemAnimator());
        skillView.setLayoutManager(new LinearLayoutManager(getActivity()));

        normalSkillsAdapter = new RaidBossSkillsAdapter(getActivity(), dbHandler.getBossSkills(displayBoss.getBossName(), 0));
        hardSkillsAdapter = new RaidBossSkillsAdapter(getActivity(), dbHandler.getBossSkills(displayBoss.getBossName(), 1));

        skillView.setAdapter(normalSkillsAdapter);

        heroView = view.findViewById(R.id.heroView);
        normalHeroesAdapter = new RaidBossHeroesAdapter(getActivity(), NormalRecommendedHeroes);
        hardHeroesAdapter = new RaidBossHeroesAdapter(getActivity(), HardRecommendedHeroes);

        heroView.setAdapter(normalHeroesAdapter);
        heroView.setItemAnimator(new DefaultItemAnimator());

        GridLayoutManager heroViewLayout = new GridLayoutManager(getActivity(), 3);
        heroView.setLayoutManager(heroViewLayout);

        skillView.setFocusable(false);
        heroView.setFocusable(false);
        bossDescText.requestFocus();

        final AppBarLayout appBar = view.findViewById(R.id.appbar);
        appBar.addOnOffsetChangedListener(this);

        if (hardBoss != null) {
            toolbar = view.findViewById(R.id.titleToolBar);
            toolbar.inflateMenu(R.menu.normal_mode);

            Switch switchMode = view.findViewById(R.id.switchMode);
            switchMode.setChecked(false);
            switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //Selected Hard Mode
                        bossImageView.setImageBitmap(hardBoss.getBossImage());
                        raidNameText.setText(hardBoss.getRaidName());
                        collapsingToolbarLayout.setTitle(hardBoss.getBossName());
                        bossDescText.setText(hardBoss.getBossDescription());
                        skillView.setAdapter(hardSkillsAdapter);
                        heroView.setAdapter(hardHeroesAdapter);
                    }
                    else {
                        bossImageView.setImageBitmap(displayBoss.getBossImage());
                        raidNameText.setText(displayBoss.getRaidName());
                        collapsingToolbarLayout.setTitle(displayBoss.getBossName());
                        bossDescText.setText(displayBoss.getBossDescription());
                        skillView.setAdapter(normalSkillsAdapter);
                        heroView.setAdapter(normalHeroesAdapter);
                        heroView.setAdapter(normalHeroesAdapter);
                    }
                }
            });
        }

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.normal_mode, menu);
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        if (hardDifficulty) {
            AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                    ? new AlphaAnimation(0f, 1f)
                    : new AlphaAnimation(1f, 0f);

            alphaAnimation.setDuration(duration);
            alphaAnimation.setFillAfter(true);
            v.startAnimation(alphaAnimation);
        }
    }

    //@Override
    //public void onBackPressed() {
    //
    //}

}

/*public class RaidBoss extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    public List<RaidBossDataModel> raidBossList = Guides.raidBossDataList;
    public RaidBossDataModel selectedBoss = RaidGuide.selectedBoss;
    public RaidBossDataModel displayBoss;
    static public boolean hardDifficulty = false;
    public RaidBossDataModel hardBoss;
    public List<HeroDataModel> NormalRecommendedHeroes = new ArrayList<HeroDataModel>();
    public List<HeroDataModel> HardRecommendedHeroes = new ArrayList<HeroDataModel>();

    public ImageView bossImageView;
    public TextView raidNameText;
    public TextView bossName;
    public TextView bossDescText;
    public Toolbar toolbar;
    public CollapsingToolbarLayout collapsingToolbarLayout;
    public RecyclerView skillView;
    public RecyclerView.Adapter normalSkillsAdapter;
    public RecyclerView.Adapter hardSkillsAdapter;
    public RecyclerView heroView;
    public RecyclerView.Adapter normalHeroesAdapter;
    public RecyclerView.Adapter hardHeroesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_boss);

        for (int i = 0; i < raidBossList.size(); i++) {
            RaidBossDataModel temp = raidBossList.get(i);
            String tempName = temp.getBossName();
            String selectedName = selectedBoss.getBossName();
            if (tempName.contains(selectedName)) {
                if (temp.getHardDifficulty() != 1) {
                    displayBoss = temp;
                }
                else{
                    hardDifficulty = true;
                }
            }
        }

        DBHandler dbHandler = new DBHandler(this);
        if (hardDifficulty) {
            hardBoss = dbHandler.findHardBoss(displayBoss.getBossName());
            HardRecommendedHeroes = dbHandler.getRecommendedHeroes(hardBoss.getRecommendedHeroes());
        }
        NormalRecommendedHeroes = dbHandler.getRecommendedHeroes(displayBoss.getRecommendedHeroes());

        bossImageView = findViewById(R.id.app_bar_image);
        bossImageView.setImageBitmap(displayBoss.getBossImage());

        raidNameText = findViewById(R.id.RaidNameTextView);
        raidNameText.setText(displayBoss.getRaidName());

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(displayBoss.getBossName());

        bossName = findViewById(R.id.BossNameTextView);
        bossName.setVisibility(bossImageView.GONE);

        bossDescText = findViewById(R.id.BossDescTextSwitcher);
        bossDescText.setText(displayBoss.getBossDescription());

        skillView = findViewById(R.id.skillView);
        skillView.setItemAnimator(new DefaultItemAnimator());
        skillView.setLayoutManager(new LinearLayoutManager(this));

        normalSkillsAdapter = new RaidBossSkillsAdapter(this, dbHandler.getBossSkills(displayBoss.getBossName(), 0));
        hardSkillsAdapter = new RaidBossSkillsAdapter(this, dbHandler.getBossSkills(displayBoss.getBossName(), 1));

        skillView.setAdapter(normalSkillsAdapter);

        heroView = findViewById(R.id.heroView);
        normalHeroesAdapter = new RaidBossHeroesAdapter(this, NormalRecommendedHeroes);
        hardHeroesAdapter = new RaidBossHeroesAdapter(this, HardRecommendedHeroes);

        heroView.setAdapter(normalHeroesAdapter);
        heroView.setItemAnimator(new DefaultItemAnimator());

        GridLayoutManager heroViewLayout = new GridLayoutManager(this, 3);
        heroView.setLayoutManager(heroViewLayout);

        skillView.setFocusable(false);
        heroView.setFocusable(false);
        bossDescText.requestFocus();

        final AppBarLayout appBar = findViewById(R.id.appbar);
        appBar.addOnOffsetChangedListener(this);

        if (hardBoss != null) {
            toolbar = findViewById(R.id.titleToolBar);
            toolbar.inflateMenu(R.menu.normal_mode);

            Switch switchMode = findViewById(R.id.switchMode);
            switchMode.setChecked(false);
            switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //Selected Hard Mode
                        bossImageView.setImageBitmap(hardBoss.getBossImage());
                        raidNameText.setText(hardBoss.getRaidName());
                        collapsingToolbarLayout.setTitle(hardBoss.getBossName());
                        bossDescText.setText(hardBoss.getBossDescription());
                        skillView.setAdapter(hardSkillsAdapter);
                        heroView.setAdapter(hardHeroesAdapter);
                    } else {
                        bossImageView.setImageBitmap(displayBoss.getBossImage());
                        raidNameText.setText(displayBoss.getRaidName());
                        collapsingToolbarLayout.setTitle(displayBoss.getBossName());
                        bossDescText.setText(displayBoss.getBossDescription());
                        skillView.setAdapter(normalSkillsAdapter);
                        heroView.setAdapter(normalHeroesAdapter);
                        heroView.setAdapter(normalHeroesAdapter);
                    }
                }
            });
        }
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
        if (hardDifficulty) {
            AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                    ? new AlphaAnimation(0f, 1f)
                    : new AlphaAnimation(1f, 0f);

            alphaAnimation.setDuration(duration);
            alphaAnimation.setFillAfter(true);
            v.startAnimation(alphaAnimation);
        }
    }
}*/
