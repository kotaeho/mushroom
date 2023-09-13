package com.grandra.mushroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {
    private RecyclerView favorite_recyclerView;
    private ArrayList<String> imageUrl = new ArrayList<>();
    private ArrayList<String> mushroom_name = new ArrayList<>();
    private ArrayList<String> mushrrom_num = new ArrayList<>();


    DBHelper dbHelper;

    private AdView mAdview; //애드뷰 변수 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        this.Init();
        this.dbSelect();
        this.recycleview_set();

        MobileAds.initialize(this, new OnInitializationCompleteListener() { //광고 초기화
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdview = findViewById(R.id.favorite_adView); //배너광고 레이아웃 가져오기
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER); //광고 사이즈는 배너 사이즈로 설정
        adView.setAdUnitId("\n" + "ca-app-pub-4268507364131475/4753482975");
    }

    private void Init(){
        favorite_recyclerView = findViewById(R.id.favorite_recyclerView);
        dbHelper = new DBHelper(this,1);

    }

    private void dbSelect(){
        Log.v("태그","값:"+dbHelper.getResult());
    }

    private void recycleview_set(){
        //--------------------------------------------------------

        for (Favroite_mushroom mushroom : dbHelper.getFavroiteMushrooms()) {
            Log.v("태그", "이름: " + mushroom.getName() + ", 번호: " + mushroom.getNum() + ", 이미지주소: " + mushroom.getImageUrl());
            mushroom_name.add(mushroom.getName());
            imageUrl.add(mushroom.getImageUrl());
            mushrrom_num.add(mushroom.getNum());
        }


        //--- LayoutManager는 아래 3가지중 하나를 선택하여 사용 ----
        // 1) LinearLayoutManager()
        // 2) GridLayoutManager()
        // 3) StaggeredGridLayoutManager()
        //---------------------------------------------------------
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        favorite_recyclerView.setLayoutManager(linearLayoutManager);  // LayoutManager 설정

        CustomAdapter customAdapter = new CustomAdapter(this,imageUrl,mushroom_name,mushrrom_num);
        //===== [Click 이벤트 구현을 위해 추가된 코드] ==============
        customAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {


            @Override
            public void onItemClicked(int position, String data,String image_data) {
                Intent intent = new Intent(getApplicationContext(),Description.class);
                intent.putExtra("mushroom_num", data);
                intent.putExtra("mushroom_image",image_data);
                startActivity(intent);
            }

            @Override
            public void onItemClicked(int position, String data) {

            }
        });

        favorite_recyclerView.setAdapter(customAdapter); // 어댑터 설정

        // Favorite 액티비티인 경우에만 CheckBox 레이아웃을 숨김
        if (this instanceof Favorite) {
            customAdapter.setHideCheckBoxLayout(true);
        } else {
            customAdapter.setHideCheckBoxLayout(false);
        }
    }
}