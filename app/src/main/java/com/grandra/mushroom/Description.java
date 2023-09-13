package com.grandra.mushroom;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Description extends AppCompatActivity {

    private static final String SERVICE_KEY = "SEyTD6N9tYDcENXw1Yd08q3Snfv%2BdPJOGaXAv74WZInaJlTQ3ZAGiEbcb4PbpVOwH7y5WEuEoTHmP1GmlT6%2B6w%3D%3D";

    private ImageView imageView;
    private String mushroom_num;
    private String des_imageUrl;
    private Context context;
    private TextView title;
    private TextView cont1;
    private TextView cont10;
    private TextView cont11;
    private TextView cont12;
    private TextView cont13;
    private TextView cont14;
    private TextView cont15;

    private TextView cont16;
    private TextView cont17;
    private TextView cont18;
    private TextView cont19;
    private TextView cont2;
    private TextView cont20;

    private TextView cont21;
    private TextView cont22;
    private TextView cont23;
    private TextView cont3;
    private TextView cont4;
    private TextView cont5;

    private TextView cont6;
    private TextView cont7;
    private TextView cont8;
    private TextView cont9;
    private TextView crpphMgntdDscrt;
    private TextView distrAraDscrt;

    private FungiApiClient fungiApiClient;
    private AdView mAdview; //애드뷰 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);

        this.Init();
        this.image();

        MobileAds.initialize(this, new OnInitializationCompleteListener() { //광고 초기화
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdview = findViewById(R.id.des_adView); //배너광고 레이아웃 가져오기
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER); //광고 사이즈는 배너 사이즈로 설정
        adView.setAdUnitId("\n" + "ca-app-pub-4268507364131475/4753482975");

        // Retrofit 및 API 클라이언트 초기화
        fungiApiClient = new FungiApiClient();

        // API 호출
        Call<FungiResponse> call = fungiApiClient.createService().getFungiInfo(SERVICE_KEY, mushroom_num);
        call.enqueue(new Callback<FungiResponse>() {
            @Override
            public void onResponse(@NonNull Call<FungiResponse> call, @NonNull Response<FungiResponse> response) {

                if (response.isSuccessful()) {
                    FungiResponse fungiResponse = response.body();
                    if (fungiResponse != null) {
                        FungiItem fungiItem = fungiResponse.getBody().getItem();
                        cont1.setText(fungiItem.getCont1());
                        cont10.setText(fungiItem.getCont10());
                          cont11.setText(fungiItem.getCont11());
                        cont12.setText(fungiItem.getCont12());

                        cont13.setText(fungiItem.getCont13());
                        cont14.setText(fungiItem.getCont14());
                        cont15.setText(fungiItem.getCont15());
                        cont16.setText(fungiItem.getCont16());
                        cont17.setText(fungiItem.getCont17());
                        cont18.setText(fungiItem.getCont18());
                        cont19.setText(fungiItem.getCont19());
                        cont2.setText(fungiItem.getCont2());
                        cont20.setText(fungiItem.getCont20());
                        cont21.setText(fungiItem.getCont21());
                        cont22.setText(fungiItem.getCont22());
                        cont23.setText(fungiItem.getCont23());
                        cont3.setText(fungiItem.getCont3());
                        cont4.setText(fungiItem.getCont4());
                        cont5.setText(fungiItem.getCont5());
                        cont6.setText(fungiItem.getCont6());
                        cont7.setText(fungiItem.getCont7());
                        cont8.setText(fungiItem.getCont8());
                        cont9.setText(fungiItem.getCont9());
                        crpphMgntdDscrt.setText(fungiItem.getCrpphMgntdDscrt());
                        distrAraDscrt.setText(fungiItem.getDistrAraDscrt());
                        title.setText(fungiItem.getFngsGnrlNm());
                    }
                } else {
                    // API 호출 실패 처리
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody != null) {
                        try {
                            String errorResponse = errorBody.string();
                            Log.e("API Error", "Error Response: " + errorResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("API Error", "Unknown error occurred.");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FungiResponse> call, @NonNull Throwable t) {
                // 네트워크 오류 처리
                Log.e("MainActivity", "Network error: " + t.getMessage());
            }
        });
    }

    private void Init() {
        imageView = findViewById(R.id.des_imageView);
        mushroom_num = getIntent().getStringExtra("mushroom_num");
        des_imageUrl = getIntent().getStringExtra("mushroom_image");

        title = findViewById(R.id.mushroom_title);
        cont1 = findViewById(R.id.cont1);
        cont10 = findViewById(R.id.cont10);
        cont11 = findViewById(R.id.cont11);
        cont12 = findViewById(R.id.cont12);
        cont13 = findViewById(R.id.cont13);
        cont14 = findViewById(R.id.cont14);
        cont15 = findViewById(R.id.cont15);

        cont16 = findViewById(R.id.cont16);
        cont17 = findViewById(R.id.cont17);
        cont18 = findViewById(R.id.cont18);
        cont19 = findViewById(R.id.cont19);
        cont2 = findViewById(R.id.cont2);
        cont20 = findViewById(R.id.cont20);
        cont21 = findViewById(R.id.cont21);
        cont22 = findViewById(R.id.cont22);
        cont23 = findViewById(R.id.cont23);
        cont3 = findViewById(R.id.cont3);
        cont4 = findViewById(R.id.cont4);
        cont5 = findViewById(R.id.cont5);
        cont6 = findViewById(R.id.cont6);
        cont7 = findViewById(R.id.cont7);
        cont8 = findViewById(R.id.cont8);
        cont9 = findViewById(R.id.cont9);
        crpphMgntdDscrt = findViewById(R.id.crpphMgntdDscrt);
        distrAraDscrt = findViewById(R.id.distrAraDscrt);
    }

    private void image(){
        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(20)) // 둥글게 처리를 위한 RoundedCorners 변환 적용
                .diskCacheStrategy(DiskCacheStrategy.ALL); // 디스크 캐싱 전략 설정

        Glide.with(this)
                .load(des_imageUrl)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade()) // 이미지 로딩 시 CrossFade 효과 적용
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 디스크 캐싱 전략 설정
                .into(imageView);
    }
}