package com.taca.boombuy.ui.mainview.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taca.boombuy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class brandSelectfrag extends Fragment {

    public brandSelectfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_selectbrandsitem, container, false);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Fragment brandfrag = new brandfrag();
        Bundle bundle = new Bundle();
        bundle.putInt("bid", 1);
        brandfrag.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.container_Frag, brandfrag);
        //fragmentTransaction.replace(R.id.container_Frag, brandfrag);
        //fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }
}
