package cn.ucai.foraging.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ucai.foraging.R;
import cn.ucai.foraging.bean.City;
import cn.ucai.foraging.utils.Utils;

public class CityActivity extends Activity {

    Context mContext;
    ArrayList<City> mCityList;

    RecyclerView city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        mContext = this;
        init();
        initView();
    }

    private void init() {
        City[] cities = (City[]) getIntent().getSerializableExtra("city");
        mCityList = (ArrayList<City>) Utils.array2List(cities);
    }

    private void initView() {
        city = (RecyclerView) findViewById(R.id.rv_city_item);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        cityAdapter mAdapter = new cityAdapter(mContext, mCityList);
        city.setLayoutManager(linearLayoutManager);
        city.setAdapter(mAdapter);
    }


    class cityHolder extends RecyclerView.ViewHolder {
        TextView cityName;
        LinearLayout ll;

        public cityHolder(View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.tv_city_name_item);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_city);
        }
    }

    class cityAdapter extends RecyclerView.Adapter<cityHolder> {

        Context mContext;
        ArrayList<City> cityList;

        public cityAdapter(Context mContext, ArrayList<City> cityList) {
            this.mContext = mContext;
            this.cityList = cityList;
        }

        @Override
        public cityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.city_item, null);
            return new cityHolder(view);
        }

        @Override
        public void onBindViewHolder(cityHolder holder, int position) {
            final City city = cityList.get(position);
            holder.cityName.setText(city.getCity());
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CityActivity.this, DressActivity.class)
                    .putExtra("dress", city.getCity()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return cityList == null ? 0 : cityList.size();
        }
    }

    public void back(View view) {
        finish();
    }
}
