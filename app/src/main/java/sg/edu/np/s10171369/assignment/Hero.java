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

        /*List<Skill> demiaSkills = new ArrayList<>();
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
*/
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
