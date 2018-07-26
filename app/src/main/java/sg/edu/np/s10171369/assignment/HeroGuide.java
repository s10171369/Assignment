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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class HeroGuide extends Fragment {

    RecyclerView recyclerView;
    static View.OnClickListener myOnClickListener;
    static int indexPosition;

    FragmentActivity listener;

    public HeroGuide() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hero_guide, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        myOnClickListener = new MyOnClickListener(getActivity());

        CollapsingToolbarLayout fragmentTitle = view.findViewById(R.id.collapsing_toolbar);
        fragmentTitle.setTitle("List of Heroes");

        recyclerView = view.findViewById(R.id.recyclerView);

        HeroGuideAdapter itemAdapter = new HeroGuideAdapter(getActivity(), Guides.heroDataList);
        recyclerView.setAdapter(itemAdapter);

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(getActivity(), 300);
        recyclerView.setLayoutManager(layoutManager);
    }

    private class MyOnClickListener implements View.OnClickListener {
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
        //Intent intent = new Intent(getActivity(), Hero.class);
        //startActivity(intent);

        Fragment fragment = new Hero();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContent, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //@Override
    //public void onBackPressed() {
    //
    //}
}
