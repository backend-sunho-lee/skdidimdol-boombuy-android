package com.taca.boombuy.ui.mainview.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
<<<<<<< HEAD
import com.taca.boombuy.vo.VO_Gift_Total_SendernReceiver;
=======
import com.taca.boombuy.database.StorageHelper;
import com.taca.boombuy.net.Network;
import com.taca.boombuy.netmodel.FCMModel;
import com.taca.boombuy.netmodel.UpdateTokenModel;
>>>>>>> 4c0ae3d33b392b75d19e7c70e381e3e114492601
import com.taca.boombuy.vo.VO_from_friends_info;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // 기본 UI 틀
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    // 프로필 사진
    CircleImageView iv_profile;

    // 보낼 사람 리스트뷰
    ListView lv_from_name_list;
    // 보낼 사람 어댑터
    FromListAdapter fromListAdapter;

    // 헤더 푸터 뷰
    View header_content_main, footer_content_main;

    // 상품 선택 리싸이클 뷰
    RecyclerView recyclerview;

    RecycleAdapter recycleAdapter = new RecycleAdapter();
    LinearLayoutManager linearLayoutManager;

    // 받을 사람 이름 텍스트뷰
    TextView tv_to_friend_name;
    // 보낼 사람들 이름 텍스트뷰
    TextView tv_from_friends_name;

    // 내가 결제할 금액 텍스트뷰
    TextView tv_devided_master;

    // 총 결제 금액 텍스트 뷰
    TextView tv_total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Single_Value.getInstance().SenderNReceiver = new VO_Gift_Total_SendernReceiver();



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 헤더 푸터 뷰
        header_content_main = getLayoutInflater().inflate(R.layout.header_content_main, null);
        footer_content_main = getLayoutInflater().inflate(R.layout.footer_content_main, null);

        // 보낼 사람 리스트뷰 초기화
        lv_from_name_list = (ListView) findViewById(R.id.lv_from_name_list);
        fromListAdapter = new FromListAdapter();
        lv_from_name_list.setAdapter(fromListAdapter);

        lv_from_name_list.addHeaderView(header_content_main);
        lv_from_name_list.addFooterView(footer_content_main);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        tv_to_friend_name = (TextView) findViewById(R.id.tv_to_friend_name);
        tv_from_friends_name = (TextView) findViewById(R.id.tv_from_friends_name);

        // 내가 결제할 금액 텍스트뷰
        tv_devided_master = (TextView) findViewById(R.id.tv_devided_master);

        // 총 결제 금액 텍스트 뷰
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i("토큰 확인 / 전송 : ", token);
        Single_Value.getInstance().updateTokenModel = new UpdateTokenModel();
        Single_Value.getInstance().updateTokenModel.setPhone(StorageHelper.getInstance().getString(getApplicationContext(), "my_phone_number"));
        Single_Value.getInstance().updateTokenModel.setToken(token);
        Network.getInstance().bb_Update_token(getApplicationContext(), Single_Value.getInstance().updateTokenModel);
        // 토큰이 바뀌면 쉐어드프리퍼런스에 저장
        StorageHelper.getInstance().setString(getApplicationContext(), "my_token", token);

        //임시 나중엔 sharedpreference나 디비에 연동---------------------
        //iv_profile.setImageResource(R.mipmap.ic_launcher);
        //---------------------------------------------------------------

        /*UI 색깔 변경*/
        // 상단 바
        //toolbar.setBackgroundColor(getResources().getColor(R.color.basic_white));
        // 그냥 화면
        //drawer.setBackgroundColor(getResources().getColor(R.color.boderColor));
        // 네비게이션 하단 화면
        //navigationView.setBackgroundColor(getResources().getColor(R.color.boderColor));


        // 뷰의 스타일(매니저) 정의 , 선형, 그리드형, 높이가 불규칙한 그리드형
        // 선형
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerview.setLayoutManager(linearLayoutManager);

        /*// 고정크기 그리드
        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        //recyclerview.setLayoutManager(gridLayoutManager);

        // 가변 그리드
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        //recyclerview.setLayoutManager(staggeredGridLayoutManager);*/


        // 데이터 공급원 아답터 연결
        recyclerview.setAdapter(recycleAdapter);

    }

    public void onMovePaymentActivity(View view) {
        Intent intent = new Intent(MainActivity.this, GiftManageActivity.class);
        startActivity(intent);
        finish();

        Log.i("TOTAL TEST", Single_Value.getInstance().SenderNReceiver.toString());

        Single_Value.getInstance().vo_gift_total_member.add(Single_Value.getInstance().SenderNReceiver);
        Log.i("TOTAL TEST2", Single_Value.getInstance().vo_gift_total_member.toString());
        Log.i("TOTAL TEST2 SIZE", Single_Value.getInstance().vo_gift_total_member.size() + "");

        //Single_Value.getInstance().SenderNReceiver = new VO_Gift_Total_SendernReceiver();
        Log.i("After TEST", Single_Value.getInstance().SenderNReceiver.toString());


        /*ArrayList<FCMModel> fcmModels = new ArrayList<FCMModel>();
        FCMModel fcmModel = new FCMModel();
        fcmModel.setToken(FirebaseInstanceId.getInstance().getToken());
        //fcmModel.setToken("ccGKhTjloXU:APA91bHbJgKGr88hvP3_0uZ_-3xpaAyyLqWLcnro8ukQVu2FU3RVYpMEmV0wD5c934VbSjgqqLNegNgWVb3kmzYXM2F_KWgyfx5B0AhDdkNy3nZioD_mU-WqVt4FHNJTGcNYvSAJggbG");
        fcmModel.setContent("Send!!!!!!!!!!!!!!!!!");
        fcmModels.add(fcmModel);
        Network.getInstance().sendFcm(getApplicationContext(), fcmModels);*/
    }

    public void onAdd(View view) {
        Intent intent = new Intent(MainActivity.this, MainProduct.class);
        startActivity(intent);
    }

    // 아답터
    class RecycleAdapter extends RecyclerView.Adapter {
        // 데이터의 개수
        @Override
        public int getItemCount() {
            return Single_Value.getInstance().vo_giftitem_lists.size();
        }

        // ViewHolder 생성
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // xml -> view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_cardview_layout, parent, false);
            return new Main_PostHolder(itemView);
        }

        // ViewHolder에 데이터를  설정(바인딩)한다.
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            // 보이고자 하는 셀에 내용을 설정한다.
            if (position == Single_Value.getInstance().vo_giftitem_lists.size() - 1) { // 제일 마지막일때는 버튼을 씌움
                ((Main_PostHolder) holder).bindOnPost(
                        1,
                        Single_Value.getInstance().vo_giftitem_lists.get(position).getProduct_imageView_cell(),
                        "",
                        "");
            } else { // 아닐 때는 아이템 보여줌
                ((Main_PostHolder) holder).bindOnPost(
                        2,
                        Single_Value.getInstance().vo_giftitem_lists.get(position).getProduct_imageView_cell(),
                        Single_Value.getInstance().vo_giftitem_lists.get(position).getProduct_title_cell(),
                        Single_Value.getInstance().vo_giftitem_lists.get(position).getProduct_price_cell());
            }
        }
    }

    /*
     * 보낼 사람 리스트뷰 설정----------------------------------------------------------------------------------------------
     */

    // 반복되는 cell의 구성 콤포넌트를 최초 cell 생성시 획득하여 설정하는 클래스
    class ViewHolder {
        @BindView(R.id.iv_from_profile_cell)
        ImageView iv_from_profile_cell;
        @BindView(R.id.tv_from_name_cell)
        TextView tv_from_name_cell;
        @BindView(R.id.tv_divided_cell)
        TextView tv_divided_cell;

        public ViewHolder(View view) {
            // cell 뷰를 바인딩한다.
            ButterKnife.bind(this, view);
        }
    }

    // 리스트뷰에 세팅되는 view를 cell이라고 통상 지칭한다. (ios에서 나온 용어)
    // BaseAdapter는 리스트뷰에 데이터를 관리하고 cell 뷰를 컨트롤하는 클래스의 수퍼
    class FromListAdapter extends BaseAdapter {

        // 리스트뷰에 표현한 데이터의 총 수
        @Override
        public int getCount() {
            if (Single_Value.getInstance().vo_from_friends_infos == null) {
                return 0;
            }
            return Single_Value.getInstance().vo_from_friends_infos.size();
        }

        // cell에 대응되는 1개의 데이터를 획득하는 메소드
        @Override
        public VO_from_friends_info getItem(int position) {
            return Single_Value.getInstance().vo_from_friends_infos.get(position);
        }

        // 아이템의 아이디, 잘 사용안함!!
        @Override
        public long getItemId(int position) {
            return 0;
        }

        // cell 1개를 만드는 메소드 (cell의 개수만큼 호출)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                // 최초 화면을 구성할 때 최대로 필요한 수만큼 여기가 작동됨
                convertView = MainActivity.this.getLayoutInflater().inflate(
                        R.layout.custom_cell_with_friends,
                        parent,
                        false
                );
                // cell을 구성원을 담을 그릇 생성
                holder = new ViewHolder(convertView);
                //holder.cell_title = (TextView) convertView.findViewById(R.id.cell_title);
                // 그릇을 뷰에 설정
                convertView.setTag(holder);
                //Log.getInstance().log("셀생성 " + position);
            } else {
                // 이제 로테이션시킬 양이 모두 채워졌다. 로테이션 시작의 의미 (있으면 재사용)
                // 재사용시 cell의 구성을 담는 그릇을 획득
                holder = (ViewHolder) convertView.getTag();
            }

            // 데이터 설정
            //holder.iv_from_profile_cell.setImageBitmap();
            holder.tv_from_name_cell.setText(Single_Value.getInstance().vo_from_friends_infos.get(position).getName());
            holder.tv_divided_cell.setText(Single_Value.getInstance().devided_non_master() + "원");

            return convertView;
        }
    }
    /*
     * --------------------------------------------------------------------------------------------------------------------------------
     */

    /*
     * 네비게이션 프로필 사진 변경 작업----------------------------------------------------------------------------------------------
     */
    // 네비게이션의 프로필 이미지를 눌렀을 때 앨범으로 이동
    public void onChangeProfile(View view) {
        iv_profile = (CircleImageView) findViewById(R.id.iv_profile);
        //iv_profile.setImageResource(R.drawable.img_splash);
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        //requestCode 100
        startActivityForResult(photoPickerIntent, 100);
    }

    // 앨범에서 사진 하나 선택했을 때 result를 받아서 비트맵으로 변경 후 프로필에 적용
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = imageReturnedIntent.getData();
                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        Bitmap bitmapSelectedImage = BitmapFactory.decodeStream(imageStream);
                        iv_profile.setImageBitmap(bitmapSelectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
    /*
     * --------------------------------------------------------------------------------------------------------------------------------
     */

    // 네비게이션 백버튼 눌렀을 때 이벤트
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // 네비게이션 아이템 클릭했을 때 이벤트
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_boombuy_shop) {
            // Handle the camera action
        } else if (id == R.id.nav_gift_manage) {

        } else if (id == R.id.nav_giftbox) {

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onMoveToFriendListActivity(View view) {
        Intent intent = new Intent(MainActivity.this, ToFriendListActivity.class);
        startActivity(intent);
    }

    public void onMoveFromFriendsListActivity(View view) {
        Intent intent = new Intent(MainActivity.this, FromFriendsListActivity.class);
        startActivity(intent);
    }

    public class Main_PostHolder extends RecyclerView.ViewHolder {

        LinearLayout cell_basic;
        ImageButton btn_add_gift_list;

        TextView product_title_cell, product_price_cell;
        ImageView product_imageView_cell;
        Button btn_remove_gift;

        // 뷰로부터 컴포넌트를 획득
        public Main_PostHolder(View itemView) {
            super(itemView);
            cell_basic = (LinearLayout) itemView.findViewById(R.id.cell_basic);
            btn_add_gift_list = (ImageButton) itemView.findViewById(R.id.btn_add_gift_list);

            product_imageView_cell = (ImageView) itemView.findViewById(R.id.product_imageView_cell);
            product_title_cell = (TextView) itemView.findViewById(R.id.product_title_cell);
            btn_remove_gift = (Button) itemView.findViewById(R.id.btn_remove_gift);
            product_price_cell = (TextView) itemView.findViewById(R.id.product_price_cell);

        }

        public void bindOnPost(int type, Bitmap bitmap, String text, String text2) {
            if (type == 1) { // 제일 마지막 버튼 씌울 때
                cell_basic.setVisibility(View.INVISIBLE);
                btn_add_gift_list.setVisibility(View.VISIBLE);
            } else {
                cell_basic.setVisibility(View.VISIBLE);
                btn_add_gift_list.setVisibility(View.INVISIBLE);
            }
            product_imageView_cell.setImageBitmap(bitmap);
            product_title_cell.setText(text);
            product_price_cell.setText(text2);

            final String tmp_text = text;
            btn_remove_gift.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = 0;
                    for (i = 0; i < Single_Value.getInstance().vo_giftitem_lists.size(); i++) {
                        if (Single_Value.getInstance().vo_giftitem_lists.get(i).getProduct_title_cell() == tmp_text) {
                            break;
                        }
                    }
                    Single_Value.getInstance().vo_giftitem_lists.remove(i);

                    refreshMainView();
                }
            });
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshMainView();
    }

    public void refreshMainView() {
        if (Single_Value.getInstance().vo_to_friend_infos.size() != 0) {
            tv_to_friend_name.setText(Single_Value.getInstance().vo_to_friend_infos.get(0).getName());
        }
        String full_selected = "";
        if (Single_Value.getInstance().vo_from_friends_infos.size() != 0) {
            for (int i = 0; i < Single_Value.getInstance().vo_from_friends_infos.size(); i++) {
                if (i == 0) {
                    full_selected += Single_Value.getInstance().vo_from_friends_infos.get(i).getName();
                } else {
                    full_selected += ", " + Single_Value.getInstance().vo_from_friends_infos.get(i).getName();
                }
            }
            tv_from_friends_name.setText(full_selected);

            //////////////////////////////////////////////메인 리스트뷰
            lv_from_name_list.setAdapter(fromListAdapter);
        }
        // 내가 결제할 금액
        tv_devided_master.setText(Single_Value.getInstance().devided_master() + "원");

        // 총 결제 금액
        tv_total_price.setText(Single_Value.getInstance().getTotalPrice() + "원");

        // 리싸이클뷰
        recyclerview.setAdapter(recycleAdapter);
    }

}
