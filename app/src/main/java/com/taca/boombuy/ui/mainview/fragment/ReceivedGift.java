package com.taca.boombuy.ui.mainview.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.ui.mainview.activity.GiftDetailInfoActivity;
import com.taca.boombuy.ui.mainview.activity.MainActivity;
import com.taca.boombuy.vo.VO_Gift_Total_SendernReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceivedGift extends Fragment {



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReceivedGift() {
    }

    public static ReceivedGift newInstance(String param1, String param2) {
        ReceivedGift fragment = new ReceivedGift();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    LayoutInflater inflater;
    MyListAdapter mylistAdapter;
    ListView listView;
    Button restartBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_received_gift, container, false);

        restartBtn = (Button) view.findViewById(R.id.restartBtn);

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

                getActivity().finish();
            }
        });


        Log.i("MYTEST", Single_Value.getInstance().vo_gift_total_member.toString());

        listView = (ListView) view.findViewById(R.id.received_gift_listview);

        mylistAdapter = new MyListAdapter();
        listView.setAdapter(mylistAdapter);

        return view;
    }


    class ListViewHolder {

        @BindView(R.id.received_gift_cell_tv_date)
        TextView received_gift_cell_tv_date;

        @BindView(R.id.received_gift_cell_payment_state)
        ImageButton received_gift_cell_payment_state;

        @BindView(R.id.gift_sendPeople)
        TextView gift_sendPeople;

        @BindView(R.id.gift_receivedPerson)
        TextView gift_receivedPerson;

        // 여기까지가 리스트에 해당하는 view 들
        public ListViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    class MyListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Single_Value.getInstance().vo_gift_total_member.size();
        }

        @Override
        public VO_Gift_Total_SendernReceiver getItem(int position) {
            return Single_Value.getInstance().vo_gift_total_member.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ListViewHolder holder;

            if(convertView == null){
                convertView = inflater.inflate(R.layout.custom_receivedgift_cell, parent, false);
                holder = new ListViewHolder(convertView);
                convertView.setTag(holder);

            }else{
                holder = (ListViewHolder) convertView.getTag();
            }

            holder.received_gift_cell_tv_date.setText("2017. 02. 17");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_progress);
            holder.received_gift_cell_payment_state.setImageBitmap(bitmap);

            Log.i("SEND PEOPLE", Single_Value.getInstance().toString(position));

            String temp ="";
            for(int i =0; i<getItem(position).getVo_from_friends_local_list().size(); i++)
            {
                if(i == getItem(position).getVo_from_friends_local_list().size()-1){
                    temp += getItem(position).getVo_from_friends_local_list().get(i).toString();
                }else{
                    temp += getItem(position).getVo_from_friends_local_list().get(i).getName()+ ",";
                }
            }

            holder.gift_sendPeople.setText(temp);
            holder.gift_receivedPerson.setText(getItem(position).getVo_to_friend_info().getName());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), GiftDetailInfoActivity.class);
                    startActivity(intent);
                }
            });

            return convertView;
        }

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




}
