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
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class RaidGuide extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter itemAdapter;
    List<RaidBossDataModel> raidGuideData;
    static View.OnClickListener myOnClickListener;
    static RaidBossDataModel selectedBoss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid_guide);

        // Get and set data
        DBHandler dbHandler = new DBHandler(this);
        raidGuideData = dbHandler.getDistinctRaidBosses();

        myOnClickListener = new MyOnClickListener(this);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("List of Raid Bosses");
        recyclerView = findViewById(R.id.recyclerView);
        itemAdapter = new RaidGuideAdapter(this, raidGuideData);
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
        int indexPosition = recyclerView.getChildPosition(v);
        selectedBoss = raidGuideData.get(indexPosition);
        Intent intent = new Intent(this, RaidBoss.class);
        startActivity(intent);
    }
}
