package sg.edu.np.s10171369.assignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class RaidGuide extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter itemAdapter;
    static View.OnClickListener myOnClickListener;
    static int indexPosition;
    static List<Skill> TurtleSkill;

    List<RaidBossDataModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_guide);

        myOnClickListener = new MyOnClickListener(this);

        // add data
        TurtleSkill= new ArrayList<>();
        TurtleSkill.add(new Skill(R.drawable.worryhugged, "Avalache","Rains ice crystals from above", 35));
        List<HeroDataModel> RecommendedHeroes = new ArrayList<>();
        RecommendedHeroes = HeroGuide.data;
        data.add(new RaidBossDataModel(R.drawable.worryhugged, "Guild Conquest", "Turd", "Hard Ass Boss", TurtleSkill, RecommendedHeroes));

        recyclerView = findViewById(R.id.recyclerView);
        itemAdapter = new RaidGuideAdapter(this, data);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);

    }
    private class MyOnClickListener implements View.OnClickListener{
        private Context context;
        private MyOnClickListener(Context src) {
            this.context = src;
        }
        @Override
        public void onClick(View v) {
            // code what happens when user clicks on an item
            launchRaidBossPage(v);
        }
    }
    private void launchRaidBossPage(View v) {
        indexPosition = recyclerView.getChildPosition(v);
        Intent intent = new Intent(this, RaidBoss.class);
        startActivity(intent);
    }
}
