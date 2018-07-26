package sg.edu.np.s10171369.assignment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Fragment {

    RecyclerView skillView;
    RecyclerView.LayoutManager layoutManager;

    FragmentActivity listener;

    public Hero() {

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hero, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        HeroDataModel item = Guides.heroDataList.get(HeroGuide.indexPosition);
        DBHandler dbHandler = new DBHandler(getActivity());
        List<Skill> heroSkills = dbHandler.getHeroSkills(item.getHeroName());

        Drawable drawable = new BitmapDrawable(getResources(), item.getHeroImage());

        CollapsingToolbarLayout nameText = view.findViewById(R.id.collapsing_toolbar);
        nameText.setTitle(item.getHeroName());
        nameText.setBackground(drawable);

        TextView heroTitle = view.findViewById(R.id.TitleTextView);
        heroTitle.setText(item.getHeroTitle());

        TextView storyText = view.findViewById(R.id.StoryTextView);
        storyText.setText(item.getHeroStory());

        ImageView UWImage = view.findViewById(R.id.UWImageVIew);
        UWImage.setImageBitmap(item.getHeroUW());

        ImageView UT2Image = view.findViewById(R.id.UT2ImageView);
        UT2Image.setImageBitmap(item.getUT2Image());

        ImageView UT3Image = view.findViewById(R.id.UT3ImageView);
        UT3Image.setImageBitmap(item.getUT3Image());

        // Get and set data
        skillView = view.findViewById(R.id.SkillsRecyclerView);
        HeroAdapter itemAdapter = new HeroAdapter(getActivity(), heroSkills);
        skillView.setAdapter(itemAdapter);

        layoutManager = new LinearLayoutManager(getActivity());
        skillView.setLayoutManager(layoutManager);

        skillView.setFocusable(false);
        nameText.requestFocus();
    }

    //@Override
    //public void onBackPressed() {
    //
    //}

}
