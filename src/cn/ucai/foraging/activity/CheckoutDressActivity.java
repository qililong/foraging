package cn.ucai.foraging.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import cn.ucai.foraging.R;
import cn.ucai.foraging.bean.City;
import cn.ucai.foraging.bean.Province;

public class CheckoutDressActivity extends Activity {

    ArrayList<Province> arrayList = new ArrayList();
    City[] city;
    Province province;
    RecyclerView rvCity;
    ImageView back;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_dress);
        init();
        parseData();
        initView();
    }



    private void init() {
        context = this;
    }

    private void initView() {
        rvCity = (RecyclerView) findViewById(R.id.rv_city);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        Log.e("main", "initView:" + arrayList.toString());
        myAdapter mAdapter = new myAdapter(context, arrayList);
        rvCity.setLayoutManager(linearLayoutManager);
        rvCity.setAdapter(mAdapter);
    }

    private void parseData() {
        String res = null;
        InputStream in = getResources().openRawResource(R.raw.citys);
        try {
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            res = EncodingUtils.getString(buffer, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray json = new JSONArray(res);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonArray = new JSONObject(json.get(i).toString());
                JSONArray citys = jsonArray.getJSONArray("citys");
                city = new City[citys.length()];
                for (int t = 0; t < citys.length(); t++) {
                    JSONObject js = new JSONObject(citys.get(t).toString());
                    City city1 = new City(js.getString("id"), js.getString("city"));
                    city[t] = city1;
                }
                province = new Province(jsonArray.getString("name"), city);
                arrayList.add(province);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class myHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        ImageView iv1;
        LinearLayout dress;
        public myHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv_city_name);
            iv1 = (ImageView) itemView.findViewById(R.id.iv_city_next);
            dress = (LinearLayout) itemView.findViewById(R.id.dress);
        }
    }

    class myAdapter extends RecyclerView.Adapter<myHolder> {

        Context mContext;
        ArrayList<Province> mProvinceList;

        public myAdapter(Context mContext, ArrayList<Province> mProvinceList) {
            this.mContext = mContext;
            this.mProvinceList = mProvinceList;
        }

        @Override
        public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_dress, null);
            return new myHolder(view);
        }

        @Override
        public void onBindViewHolder(myHolder holder, final int position) {
            final Province province = mProvinceList.get(position);

            holder.tv1.setText(province.getName());
            holder.iv1.setImageResource(R.drawable.right_arrow_icon);
            holder.dress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (province.getCitys().length > 1) {
                        startActivity(new Intent(CheckoutDressActivity.this, CityActivity.class)
                                .putExtra("city", province.getCitys()));
                    } else {
                        startActivity(new Intent(CheckoutDressActivity.this, DressActivity.class)
                                .putExtra("dress", province.getCitys()[0].getCity()));
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mProvinceList == null ? 0 : mProvinceList.size();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public void back(View view) {
        finish();
    }
}
