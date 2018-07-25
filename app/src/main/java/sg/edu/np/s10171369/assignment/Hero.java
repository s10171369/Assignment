package sg.edu.np.s10171369.assignment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

        HeroDataModel item = Guides.heroDataList.get(HeroGuide.indexPosition);
        DBHandler dbHandler = new DBHandler(this);
        List<Skill> heroSkills = dbHandler.getHeroSkills(item.getHeroName());

        Drawable drawable = new BitmapDrawable(getResources(), item.getHeroImage());

        CollapsingToolbarLayout nameText = findViewById(R.id.collapsing_toolbar);
        nameText.setTitle(item.getHeroName());
        nameText.setBackground(drawable);

        TextView heroTitle = findViewById(R.id.TitleTextView);
        heroTitle.setText(item.getHeroTitle());

        TextView storyText = findViewById(R.id.StoryTextView);
        storyText.setText(item.getHeroStory());

        ImageView UWImage = findViewById(R.id.UWImageVIew);
        UWImage.setImageBitmap(item.getHeroUW());

        ImageView UT2Image = findViewById(R.id.UT2ImageView);
        UT2Image.setImageBitmap(item.getUT2Image());

        ImageView UT3Image = findViewById(R.id.UT3ImageView);
        UT3Image.setImageBitmap(item.getUT3Image());

        // Get and set data
        skillView = findViewById(R.id.SkillsRecyclerView);
        HeroAdapter itemAdapter = new HeroAdapter(this, heroSkills);
        skillView.setAdapter(itemAdapter);

        layoutManager = new LinearLayoutManager(this);
        skillView.setLayoutManager(layoutManager);

        skillView.setFocusable(false);
        nameText.requestFocus();
    }
}
