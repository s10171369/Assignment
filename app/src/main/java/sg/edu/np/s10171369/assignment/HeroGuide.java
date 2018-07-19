package sg.edu.np.s10171369.assignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HeroGuide extends AppCompatActivity {

    RecyclerView recyclerView;
    static List<HeroDataModel> data;
    static View.OnClickListener myOnClickListener;
    static int indexPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_guide);

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        List<Skill> demiaSkills = new ArrayList<>();
        demiaSkills.add(new Skill(R.drawable.worryhugged, "skill1","cc","1 less mana", "increased chance"));
        demiaSkills.add(new Skill(R.drawable.worryhugged, "skill2","buff","1 less mana", "increased chance"));
        demiaSkills.add(new Skill(R.drawable.worryhugged, "skill3","cc","1 less mana", "increased chance"));
        demiaSkills.add(new Skill(R.drawable.worryhugged, "skill4","passive","1 less mana", "increased chance"));
        List<Skill> soniaSkills = new ArrayList<>();
        soniaSkills.add(new Skill(R.drawable.worryhugged, "skill1","cc","1 less mana", "increased chance"));
        soniaSkills.add(new Skill(R.drawable.worryhugged, "skill2","buff","1 less mana", "increased chance"));
        soniaSkills.add(new Skill(R.drawable.worryhugged, "skill3","cc","1 less mana", "increased chance"));
        soniaSkills.add(new Skill(R.drawable.worryhugged, "skill4","passive","1 less mana", "increased chance"));
        data = new ArrayList<>();
        data.add(new HeroDataModel(R.drawable.worryhugged, "Sonia", "UltimateTanker","HeroStory", R.drawable.worryhugged, R.drawable.worryhugged, soniaSkills));
        data.add(new HeroDataModel(R.drawable.worryhugged, "Demia", "UltimateTanker","HeroStory", R.drawable.worryhugged, R.drawable.worryhugged, demiaSkills));

        HeroGuideAdapter itemAdapter = new HeroGuideAdapter(this, data);
        recyclerView.setAdapter(itemAdapter);

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 300);
        recyclerView.setLayoutManager(layoutManager);
    }

    private class MyOnClickListener implements View.OnClickListener{
        private Context context;
        private MyOnClickListener(Context src) {
            this.context = src;
        }
        @Override
        public void onClick(View v) {
            // code what happens when user clicks on an item
            launchHeroPage(v);
        }
    }

    private void launchHeroPage(View v){
        indexPosition = recyclerView.getChildPosition(v);
        Intent intent = new Intent(this, Hero.class);
        startActivity(intent);
    }

}
