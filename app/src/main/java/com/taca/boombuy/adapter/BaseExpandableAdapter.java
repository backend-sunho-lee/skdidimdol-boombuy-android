package com.taca.boombuy.adapter;

/**
 * Created by Tacademy on 2017-02-09.
 */

public class BaseExpandableAdapter {
/*
    Activity context;

    ArrayList<VO_giftitem_group_info> parent;
    HashMap<VO_giftitem_group_info, ArrayList<VO_giftitem_list>> child;

    ParentViewHolder parentViewHolder;
    ChildViewHolder childViewHolder;

    class ParentViewHolder {

        @BindView(R.id.received_gift_cell_tv_date)
        TextView received_gift_cell_tv_date;
        @BindView(R.id.received_gift_cell_open)
        ImageButton received_gift_cell_open;
        @BindView(R.id.received_gift_cell_payment_state)
        ImageButton received_gift_cell_payment_state;

        @BindView(R.id.received_gift_cell_sendMemberProfile)
        ImageView received_gift_cell_sendMemberProfile;
        @BindView(R.id.received_gift_cell_receivedMemberProfile)
        ImageView received_gift_cell_receivedMemberProfile;

        @BindView(R.id.gift_sendPeople)
        TextView gift_sendPeople;
        @BindView(R.id.gift_receivedPerson)
        TextView gift_receivedPerson;

        @BindView(R.id.received_gift_cell_detail_gift_info_layout)
        LinearLayout received_gift_cell_detail_gift_info_layout;

        public ParentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ChildViewHolder {
        @BindView(R.id.received_gift_cell_child_product_name)
        TextView received_gift_cell_child_product_name;

        @BindView(R.id.received_gift_cell_child_product_price)
        TextView received_gift_cell_child_product_price;

        public ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public BaseExpandableAdapter(Activity context, ArrayList<VO_giftitem_group_info> parent, HashMap<VO_giftitem_group_info, ArrayList<VO_giftitem_list>> child) {
        this.context = context;
        this.parent = parent;
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.size();
    }

    @Override
    public VO_giftitem_group_info getGroup(int groupPosition) {
        return parent.get(groupPosition);
    }

    @Override
    public VO_giftitem_list getChild(int groupPosition, int childPosition) {
        return child.get(parent.get(groupPosition)).get(childPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.custom_receivedgift_cell, null);
            parentViewHolder = new ParentViewHolder(convertView);
        }

        parentViewHolder.gift_sendPeople.setText(getGroup(groupPosition).getSendPeople().toString());
        parentViewHolder.gift_receivedPerson.setText(getGroup(groupPosition).getReceivedPerson());

        parentViewHolder.received_gift_cell_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_receivedgift_cell_productinfo_cell, null);
            childViewHolder = new ChildViewHolder(convertView);
        }

        childViewHolder.received_gift_cell_child_product_name.setText(getChild(groupPosition, childPosition).getProduct_title_cell());
        childViewHolder.received_gift_cell_child_product_price.setText(getChild(groupPosition, childPosition).getProduct_price_cell());
*//*
        childViewHolder.received_gift_cell_child_sendName.setText(getChild(groupPosition, childPosition).getProduct_title_cell());
        childViewHolder.received_gift_cell_child_sendPrice.setText(getChild(groupPosition, childPosition).get());
        childViewHolder.received_gift_cell_child_sendPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "개별 결제 모듈로 이동", Toast.LENGTH_SHORT).show();

            }
        });*//*

        return null;
    }


    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }*/
}
