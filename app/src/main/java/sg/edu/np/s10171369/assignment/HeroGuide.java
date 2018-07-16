package sg.edu.np.s10171369.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HeroGuide extends AppCompatActivity {

    RecyclerView recyclerView;
    List<HeroDataModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_guide);

        recyclerView = findViewById(R.id.recyclerView);
        data = new ArrayList<>();
        data.add(new HeroDataModel(R.drawable.worryhugged, "Sonia", "UltimateTanker","HeroStory", R.drawable.worryhugged, R.drawable.worryhugged,
                new Skill(R.drawable.worryhugged, "skill1","cc","1 less mana", "increased chance"),
                new Skill(R.drawable.worryhugged, "skill2","buff","1 less mana", "increased chance"),
                new Skill(R.drawable.worryhugged, "skill3","cc","1 less mana", "increased chance"),
                new Skill(R.drawable.worryhugged, "skill4","passive","1 less mana", "increased chance")));
        data.add(new HeroDataModel(R.drawable.worryhugged, "Demia", "UltimateTanker","HeroStory", R.drawable.worryhugged, R.drawable.worryhugged,
                new Skill(R.drawable.worryhugged, "skill1","cc","1 less mana", "increased chance"),
                new Skill(R.drawable.worryhugged, "skill2","buff","1 less mana", "increased chance"),
                new Skill(R.drawable.worryhugged, "skill3","cc","1 less mana", "increased chance"),
                new Skill(R.drawable.worryhugged, "skill4","passive","1 less mana", "increased chance")));

        HeroGuideAdapter itemAdapter = new HeroGuideAdapter(this, data);
        recyclerView.setAdapter(itemAdapter);

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 300);
        recyclerView.setLayoutManager(layoutManager);
    }
}
