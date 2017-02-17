package com.taca.boombuy.ui.mainview.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.taca.boombuy.R;
import com.taca.boombuy.Single_Value;
import com.taca.boombuy.util.U;
import com.taca.boombuy.vo.VO_from_friends_info;
import com.taca.boombuy.vo.VO_from_friends_local_list;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FromFriendsListActivity extends AppCompatActivity {


    // 선물 받을 사람 전화번호부 리스트뷰
    ListView lv_from_friends_local_list;
    // 선물 받을 사람 전화번호부 어댑터
    FromFriendsListActivity.FromFriendsLocalListAdapter fromFriendsLocalListAdapter;

    // 검색 에디트텍스트
    EditText et_search_from_friends_name;

    //뷰 홀더
    FromFriendsListActivity.ViewHolder holder;

    // 선택된 함께 보낼 사람 리싸이클 뷰
    RecyclerView rv_selected_friends;
    FromFriendsListActivity.RecycleAdapter recycleAdapter = new FromFriendsListActivity.RecycleAdapter();
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_friends_list);

        request_read_contacts();

        et_search_from_friends_name = (EditText) findViewById(R.id.et_search_from_friends_name);
        et_search_from_friends_name.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력되는 텍스트에 변화가 있을 때
                refreshList();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에
            }
        });

        //// 리싸이클 뷰 관련
        rv_selected_friends = (RecyclerView) findViewById(R.id.rv_selected_friends);
        // 뷰의 스타일(매니저) 정의 , 선형, 그리드형, 높이가 불규칙한 그리드형
        // 선형
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rv_selected_friends.setLayoutManager(linearLayoutManager);

        // 데이터 공급원 아답터 연결
        rv_selected_friends.setAdapter(recycleAdapter);
    }

    // 리싸이클뷰 아답터
    class RecycleAdapter extends RecyclerView.Adapter {
        // 데이터의 개수
        @Override
        public int getItemCount() {
            return Single_Value.getInstance().vo_from_friends_infos.size();
        }

        // ViewHolder 생성
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // xml -> view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_cardview_selected_layout, parent, false);
            return new Selected_PostHolder(itemView);
        }

        // ViewHolder에 데이터를  설정(바인딩)한다.
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            // 보이고자 하는 셀에 내용을 설정한다.(밑에 클래스 있음)
            Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.img_basic_profile);
            ((Selected_PostHolder) holder).bindOnPost(bitmap, Single_Value.getInstance().vo_from_friends_infos.get(position).getName());
        }
    }

    // 전화번호부에서 특정 키워드를 포함하는 리스트를 다시 불러와서 뿌려줌
    public void refreshList() {
        Single_Value.getInstance().vo_from_friends_local_lists.clear();
        U.getInstance().getPhoneNumber(this, et_search_from_friends_name.getText().toString());

        lv_from_friends_local_list = (ListView) findViewById(R.id.lv_from_friends_local_list);
        lv_from_friends_local_list.setOnItemClickListener(itemClickListenerOfFromFriendsList);
        fromFriendsLocalListAdapter = new FromFriendsListActivity.FromFriendsLocalListAdapter();
        lv_from_friends_local_list.setAdapter(fromFriendsLocalListAdapter);
    }

    public void onSearchFromFrineds(View view) {
        refreshList();
    }

    /*
     * 리스트뷰 클릭 리스터
     */
    private AdapterView.OnItemClickListener itemClickListenerOfFromFriendsList = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View clickedView, int pos, long id) {
            //Single_Value.getInstance().vo_from_friends_infos.clear();
            Single_Value.getInstance().vo_from_friends_info = new VO_from_friends_info();
            Single_Value.getInstance().vo_from_friends_info.setName(Single_Value.getInstance().vo_from_friends_local_lists.get(pos).getTv_from_friends_local_name().toString());
            Single_Value.getInstance().vo_from_friends_info.setPhone_num(Single_Value.getInstance().vo_from_friends_local_lists.get(pos).getTv_from_friends_local_number().toString());
            Single_Value.getInstance().vo_from_friends_infos.add(Single_Value.getInstance().vo_from_friends_info);

            //MainActivity.tv_from_friends_name.setText(Single_Value.getInstance().vo_from_friends_infos.get(0).getName());

            // 보내는 사람들 모아서 한틀에 틀에담음
            Single_Value.getInstance().SenderNReceiver.setVo_from_friends_local_list(Single_Value.getInstance().vo_from_friends_infos);

            // 데이터 공급원 아답터 연결
            rv_selected_friends.setAdapter(recycleAdapter);
        }
    };
    /*
     *
     */

    /*
     * 받는 사람 전화번호부 리스트뷰 설정----------------------------------------------------------------------------------------------
     */

    // 반복되는 cell의 구성 콤포넌트를 최초 cell 생성시 획득하여 설정하는 클래스
    class ViewHolder {
        @BindView(R.id.iv_to_friend_profile)
        ImageView iv_to_friend_profile;
        @BindView(R.id.tv_to_friend_local_name)
        TextView tv_to_friend_local_name;
        @BindView(R.id.tv_to_friend_local_number)
        TextView tv_to_friend_local_number;

        public ViewHolder(View view) {
            // cell 뷰를 바인딩한다.
            ButterKnife.bind(this, view);
        }
    }

    // 리스트뷰에 세팅되는 view를 cell이라고 통상 지칭한다. (ios에서 나온 용어)
    // BaseAdapter는 리스트뷰에 데이터를 관리하고 cell 뷰를 컨트롤하는 클래스의 수퍼
    class FromFriendsLocalListAdapter extends BaseAdapter {

        // 리스트뷰에 표현한 데이터의 총 수
        @Override
        public int getCount() {
            if (Single_Value.getInstance().vo_from_friends_local_lists == null) {
                return 0;
            }
            return Single_Value.getInstance().vo_from_friends_local_lists.size();
        }

        // cell에 대응되는 1개의 데이터를 획득하는 메소드
        @Override
        public VO_from_friends_local_list getItem(int position) {
            return Single_Value.getInstance().vo_from_friends_local_lists.get(position);
        }

        // 아이템의 아이디, 잘 사용안함!!
        @Override
        public long getItemId(int position) {
            return 0;
        }

        // cell 1개를 만드는 메소드 (cell의 개수만큼 호출)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                // 최초 화면을 구성할 때 최대로 필요한 수만큼 여기가 작동됨
                convertView = FromFriendsListActivity.this.getLayoutInflater().inflate(
                        R.layout.custom_cell_to_friend_local_list,
                        parent,
                        false
                );
                // cell을 구성원을 담을 그릇 생성
                holder = new FromFriendsListActivity.ViewHolder(convertView);
                //holder.cell_title = (TextView) convertView.findViewById(R.id.cell_title);
                // 그릇을 뷰에 설정
                convertView.setTag(holder);
                //Log.getInstance().log("셀생성 " + position);
            } else {
                // 이제 로테이션시킬 양이 모두 채워졌다. 로테이션 시작의 의미 (있으면 재사용)
                // 재사용시 cell의 구성을 담는 그릇을 획득
                holder = (FromFriendsListActivity.ViewHolder) convertView.getTag();
            }

            // 데이터 설정
            //holder.iv_from_profile_cell.setImageBitmap();
            holder.tv_to_friend_local_name.setText(getItem(position).getTv_from_friends_local_name());
            holder.tv_to_friend_local_number.setText(getItem(position).getTv_from_friends_local_number());


            return convertView;
        }
    }

    ////////////////////////내부 전화번호 권환
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    request_read_contacts();
                } else {
                    // 사용자가 권한 동의를 안하므로 종료
                    finish();
                }
            }
        }
    }

    public void request_read_contacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //권한이 없을 경우

            //최초 권한 요청인지 혹은 사용자에 의한 재요청인지 확인
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                //사용자가 임의로 권한을 취소시킨 경우
                //권한 재요청
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            } else {
                //최초로 권한을 요청하는 경우(첫실행)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            }
        } else {
        }

    }

    public class Selected_PostHolder extends RecyclerView.ViewHolder {

        CircleImageView iv_selected_profile;
        TextView tv_selected_name;
        ImageButton btn_deselect;

        // 뷰로부터 컴포넌트를 획득
        public Selected_PostHolder(View itemView) {
            super(itemView);
            iv_selected_profile = (CircleImageView) itemView.findViewById(R.id.iv_selected_profile);
            tv_selected_name = (TextView) itemView.findViewById(R.id.tv_selected_name);
            btn_deselect = (ImageButton) itemView.findViewById(R.id.btn_deselect);
        }

        // 데이터 설정
        public void bindOnPost(Bitmap bitmap, String name) {
            iv_selected_profile.setImageBitmap(bitmap);
            tv_selected_name.setText(name);
            final String tmp_name = name;
            btn_deselect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = 0;
                    for (i = 0; i < Single_Value.getInstance().vo_from_friends_infos.size(); i++) {
                        if (Single_Value.getInstance().vo_from_friends_infos.get(i).getName() == tmp_name) {
                            break;
                        }
                    }
                    Single_Value.getInstance().vo_from_friends_infos.remove(i);
                    rv_selected_friends.setAdapter(recycleAdapter);
                }
            });
        }
    }

}
