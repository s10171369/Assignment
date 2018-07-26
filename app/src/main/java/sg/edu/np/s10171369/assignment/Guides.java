package sg.edu.np.s10171369.assignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.firebase.database.Transaction;

import java.util.ArrayList;
import java.util.List;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_guides, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        myOnClickListener = new MyOnClickListener(getActivity());

        // add data into arraylist
        data.add(new MainPageDataModel(R.drawable.worryhugged, "Hero Guide"));
        data.add(new MainPageDataModel(R.drawable.worryhugged, "Raid Guide"));

        // Get hero data
        DBHandler dbHandler = new DBHandler(getActivity());
        heroDataList = dbHandler.getAllHeroes();
        raidBossDataList = dbHandler.getAllRaidBosses();

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
            fragmentTransaction.replace(R.id.flContent, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
