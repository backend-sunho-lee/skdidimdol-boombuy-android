package com.taca.boombuy.ui.mainview.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taca.boombuy.R;
import com.taca.boombuy.networkmodel.ItemDTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftSelectProductDetailFrag extends Fragment {


    public GiftSelectProductDetailFrag() {
        // Required empty public constructor
    }

    TextView selected_detail_textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift_select_product_detail, container, false);
        ItemDTO item = (ItemDTO) getActivity().getIntent().getSerializableExtra("item");
        Log.i("ITEM DATA", item.toString());

        selected_detail_textView = (TextView) view.findViewById(R.id.selected_detail_textView);
        selected_detail_textView.setText(item.getDetail());

        return view;
    }
}
