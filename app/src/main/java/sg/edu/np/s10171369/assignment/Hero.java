package sg.edu.np.s10171369.assignment;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Hero extends AppCompatActivity {

    RecyclerView skillView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        HeroDataModel item = HeroGuide.data.get(HeroGuide.indexPosition);

        CollapsingToolbarLayout nameText = findViewById(R.id.collapsing_toolbar);
        nameText.setTitle(item.getHeroTitle() + item.getHeroName());

        TextView heroTitle = findViewById(R.id.NameTextView);
        heroTitle.setText(item.getHeroTitle());

        TextView storyText = findViewById(R.id.StoryTextView);
        storyText.setText(item.getHeroStory());

        ImageView heroImage = findViewById(R.id.app_bar_image);
        heroImage.setImageResource(item.getHeroImage());

        ImageView UWImage = findViewById(R.id.UWImageVIew);
        UWImage.setImageResource(item.getHeroUW());

        ImageView UT3Image = findViewById(R.id.UT3ImageView);
        UT3Image.setImageResource(item.getUT3Image());

        skillView = findViewById(R.id.SkillsRecyclerView);
        HeroAdapter itemAdapter = new HeroAdapter(this, item.getSkillList());
        skillView.setAdapter(itemAdapter);

        layoutManager = new LinearLayoutManager(this);
        skillView.setLayoutManager(layoutManager);

        skillView.setFocusable(false);
        nameText.requestFocus();
    }
}
