package cn.ucai.foraging.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.ucai.foraging.R;
import cn.ucai.foraging.activity.DressActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EatFragment extends Fragment {
    /*目前显示的Fragment的标注数*/
    private int mCurrentlyShowingFragment = 0;

    private Fragment[] fragments;


    SanckFragment mSanckFragment;
    SelectedFragment mSelectedFragment;

    TextView tvSelected;
    TextView tvSanck;

    int index = 0;
    int currentTabIndex = 1;

    TextView[] mTabs;
    Listener listener;
    private SelectedFragment visble;

    TextView tv_dress;

    public EatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_eat, null);
        initView(layout);
        init();
        setListener();
        return layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.fragment_chiled, mSelectedFragment)
                    .commit();
            mCurrentlyShowingFragment = 0;
        } else {
            mCurrentlyShowingFragment = savedInstanceState.getInt("currently_showing_fragment");
        }
        super.onViewCreated(view, savedInstanceState);
    }

    private void setListener() {
        tvSelected.setOnClickListener(listener);
        tvSanck.setOnClickListener(listener);
        setDressListener();
    }

    private void setDressListener() {
        tv_dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DressActivity.class));
            }
        });
    }

    private void init() {
        mSanckFragment = new SanckFragment();
        mSelectedFragment = new SelectedFragment();
        listener = new Listener();
    }

    private void initView(View layout) {
        tvSelected = (TextView) layout.findViewById(R.id.tv_selected);
        tvSanck = (TextView) layout.findViewById(R.id.tv_sanck);
        tvSelected.setSelected(true);
        tv_dress = (TextView) layout.findViewById(R.id.tv_dress);

    }

    public void setVisble(int visble) {
        if (visble == 0) {
            tvSelected.setSelected(true);
            tvSanck.setSelected(false);
        } else {
            tvSelected.setSelected(false);
            tvSanck.setSelected(true);
        }
    }


    class Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_selected:
                    Log.e("main", "onClick");
                    mCurrentlyShowingFragment = 0;
                    setVisble(mCurrentlyShowingFragment);
                    showPreviousScreen();
                    break;
                case R.id.tv_sanck:
                    mCurrentlyShowingFragment = 1;
                    setVisble(mCurrentlyShowingFragment);
                    showNextScreen();
                    break;
            }
        }
    }

    private void showNextScreen() {
        mCurrentlyShowingFragment = 0;
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_chiled, mSanckFragment)
                .addToBackStack(null)
                .commit();
    }

    private void showPreviousScreen() {
        mCurrentlyShowingFragment = 1;
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_chiled, mSelectedFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("currently_showing_fragment", mCurrentlyShowingFragment);
        super.onSaveInstanceState(outState);
    }
}

