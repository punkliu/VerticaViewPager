package com.example.macintoshhd.verticaviewpager;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int NUMBER_OF_PAGES = 2;

    MyAdapter mAdapter;
    VerticalViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);

    }


    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return FragmentOne.newInstance(0, Color.WHITE);
                case 1:
                    // return a different Fragment class here
                    // if you want want a completely different layout
                    return FragmentOne.newInstance(1, Color.CYAN);
                default:
                    return null;
            }
        }
    }

    public static class FragmentOne extends Fragment {

        private static final String MY_NUM_KEY = "num";
        private static final String MY_COLOR_KEY = "color";

        private int mNum;
        private int mColor;

        // You can modify the parameters to pass in whatever you want
        static FragmentOne newInstance(int num, int color) {
            FragmentOne f = new FragmentOne();
            Bundle args = new Bundle();
            args.putInt(MY_NUM_KEY, num);
            args.putInt(MY_COLOR_KEY, color);
            f.setArguments(args);
            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt(MY_NUM_KEY) : 0;
            mColor = getArguments() != null ? getArguments().getInt(MY_COLOR_KEY) : Color.BLACK;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_one, container, false);
            v.setBackgroundColor(mColor);
            TextView textView = v.findViewById(R.id.textview);
            textView.setText("Page " + mNum);
            return v;
        }
    }
}
