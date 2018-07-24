package sg.edu.np.s10171369.assignment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HeroGuide extends AppCompatActivity {

    RecyclerView recyclerView;
    static View.OnClickListener myOnClickListener;
    static int indexPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_guide);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("List Of Heroes");

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);

        HeroGuideAdapter itemAdapter = new HeroGuideAdapter(this, Guides.heroDataList);
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
