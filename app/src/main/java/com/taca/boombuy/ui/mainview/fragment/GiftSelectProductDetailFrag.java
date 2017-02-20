package com.taca.boombuy.ui.mainview.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taca.boombuy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftSelectProductDetailFrag extends Fragment {


    public GiftSelectProductDetailFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gift_select_product_detail, container, false);
    }

}
