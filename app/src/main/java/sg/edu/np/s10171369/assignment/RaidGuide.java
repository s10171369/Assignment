package sg.edu.np.s10171369.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RaidGuide extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RaidGuideAdapter itemAdapter;

    List<RaidBossDataModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_guide);

        // add data
        List<Skill> TurtleSkill = new ArrayList<>();
        TurtleSkill.add(new Skill(R.drawable.worryhugged, "Avalache","Rains ice crystals from above", 35));
        List<HeroDataModel> RecommendedHeroes = new ArrayList<>();
        RecommendedHeroes = HeroGuide.data;
        data.add(new RaidBossDataModel("Guild Conquest", "Turd", R.drawable.worryhugged, "Hard Ass Boss", TurtleSkill, RecommendedHeroes));

        itemAdapter = new RaidGuideAdapter(this, data);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
}
