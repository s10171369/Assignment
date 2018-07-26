package sg.edu.np.s10171369.assignment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Guides extends Fragment {

    static List<HeroDataModel> heroDataList;
    static List<RaidBossDataModel> raidBossDataList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter itemAdapter;
    static View.OnClickListener myOnClickListener;

    List<MainPageDataModel> data = new ArrayList<>();

    FragmentActivity listener;

    public Guides() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get data
        DBHandler dbHandler = new DBHandler(getActivity());
        heroDataList = dbHandler.getAllHeroes();
        raidBossDataList = dbHandler.getAllRaidBosses();

        // add data into Page arraylist
        Random r = new Random();
        int ranIndex = r.nextInt(heroDataList.size());

        Bitmap raidImage = BitmapFactory.decodeResource(getResources(), R.drawable.boss_guide_header_image);

        data.add(new MainPageDataModel(heroDataList.get(ranIndex).getHeroImage(), "Hero Guide"));
        data.add(new MainPageDataModel(raidImage, "Raid Guide"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_guides, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        myOnClickListener = new MyOnClickListener(getActivity());



        recyclerView = view.findViewById(R.id.recyclerView);
        itemAdapter = new MainPageCustomAdapter(data);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setOnClickListener(myOnClickListener);
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
            //Intent intent = new Intent(getActivity(), HeroGuide.class);
            //startActivity(intent);

            Fragment fragment = new HeroGuide();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.flContent, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (index == 1){
            //Intent intent = new Intent(getActivity(), RaidGuide.class);
            //startActivity(intent);

            Fragment fragment = new RaidGuide();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.flContent, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
