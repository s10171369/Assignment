package sg.edu.np.s10171369.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Hero extends AppCompatActivity {

    TextView nameText;
    TextView storyText;
    ImageView heroImage;
    ImageView UWImage;
    ImageView UT3Image;

    RecyclerView skillView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        nameText = findViewById(R.id.NameTextView);
        storyText = findViewById(R.id.StoryTextView);
        heroImage = findViewById(R.id.HeroImageView);
        UWImage = findViewById(R.id.UWImageVIew);
        UT3Image = findViewById(R.id.UT3ImageView);
        skillView = findViewById(R.id.SkillsRecyclerView);

        HeroDataModel item = HeroGuide.data.get(HeroGuide.indexPosition);
        nameText.setText(item.getHeroTitle() + item.getHeroName());
        storyText.setText(item.getHeroStory());
        heroImage.setImageResource(item.getHeroImage());
        UWImage.setImageResource(item.getHeroUW());
        UT3Image.setImageResource(item.getUT3Image());

        HeroAdapter itemAdapter = new HeroAdapter(this, item.getSkillList());
        skillView.setAdapter(itemAdapter);

        layoutManager = new LinearLayoutManager(this);
        skillView.setLayoutManager(layoutManager);

        skillView.setFocusable(false);
        nameText.requestFocus();
    }
}
