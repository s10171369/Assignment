package sg.edu.np.s10171369.assignment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

public class Guides extends AppCompatActivity {

    static List<HeroDataModel> heroDataList;
    static List<RaidBossDataModel> raidBossDataList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter itemAdapter;
    static View.OnClickListener myOnClickListener;

    List<MainPageDataModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);

        //ViewCompat.setTransitionName(findViewById(R.id.appbar), "transit");
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("SendHaelp");

        myOnClickListener = new MyOnClickListener(this);

        // add data into arraylist
        data.add(new MainPageDataModel(R.drawable.worryhugged, "Hero Guide"));
        data.add(new MainPageDataModel(R.drawable.worryhugged, "Raid Guide"));

        // Get hero data
        DBHandler dbHandler = new DBHandler(this);
        heroDataList = dbHandler.getAllHeroes();
        raidBossDataList = dbHandler.getAllRaidBosses();

        recyclerView = findViewById(R.id.recyclerView);
        itemAdapter = new MainPageCustomAdapter(data);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);
        //recyclerView.setOnClickListener(new myOnClickListener(this));
    }

    private class MyOnClickListener implements View.OnClickListener{
        private Context context;
        private MyOnClickListener(Context src) {
            this.context = src;
        }
        @Override
        public void onClick(View v) {
            // code what happens when user clicks on an item
            launchPages(v);
        }
    }
    private void launchPages(View v) {
        int index = recyclerView.getChildPosition(v);
        if (index == 0){
            Intent intent = new Intent(this, HeroGuide.class);
            startActivity(intent);
        }
        if (index == 1){
            Intent intent = new Intent(this, RaidGuide.class);
            startActivity(intent);
        }
    }
}
