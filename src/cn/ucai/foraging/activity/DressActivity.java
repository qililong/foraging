package cn.ucai.foraging.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.ucai.foraging.R;

public class DressActivity extends Activity {

    ImageView ivChangeCity;
    TextView tvCityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress);
        initView();
        setListener();
    }


    private void setListener() {
        setCityChangeListener();
    }

    private void setCityChangeListener() {
        ivChangeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DressActivity.this, CheckoutDressActivity.class));
            }
        });
    }

    private void initView() {
        ivChangeCity = (ImageView) findViewById(R.id.iv_change_city);
        tvCityName = (TextView) findViewById(R.id.dress_city_name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = getIntent().getStringExtra("dress");
        if (name != null) {
            tvCityName.setText(name);
        }
    }

    public void back(View view) {
        finish();
    }
}
