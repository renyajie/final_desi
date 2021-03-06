package com.renyajie.yuyue;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mine.activity.order_card.adapter.CardKindListAdapter;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, CardKindListAdapter.FinishActivity {

    private Toolbar toolbar;
    private Fragment main_fragment, news_fragment, mine_fragment;
    private LinearLayout main_layout, news_layout, mine_layout;
    private ImageView main_logo, news_logo, mine_logo;
    private TextView main_text, news_text, mine_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        initView();
        setSelect(0);
    }

    private void initView() {
        toolbar = findViewById(R.id.my_toolBar);
        setSupportActionBar(toolbar);

        main_layout = findViewById(R.id.main_layout);
        news_layout = findViewById(R.id.news_layout);
        mine_layout = findViewById(R.id.mine_layout);

        main_logo = findViewById(R.id.main_logo);
        news_logo = findViewById(R.id.news_logo);
        mine_logo = findViewById(R.id.mine_logo);

        main_text = findViewById(R.id.main_text);
        news_text = findViewById(R.id.news_text);
        mine_text = findViewById(R.id.mine_text);

        main_layout.setOnClickListener(this);
        news_layout.setOnClickListener(this);
        mine_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_layout:
                setSelect(0);
                break;
            case R.id.news_layout:
                setSelect(1);
                break;
            case R.id.mine_layout:
                setSelect(2);
                break;
        }
    }

    /**
     * 添加Fragment
     * @param fragment
     */
    public void addFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment);
        ft.commit();
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();

        if (main_fragment != null) {
            ft.hide(main_fragment);
        }
        if (news_fragment != null) {
            ft.hide(news_fragment);
        }
        if (mine_fragment != null) {
            ft.hide(mine_fragment);
        }

        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        resetImgs();

        switch(i) {
            case 0:
                if (main_fragment == null) {
                    main_fragment = new MainFragment();
                    addFragment(main_fragment);
                    showFragment(main_fragment);
                } else {
                    if (main_fragment.isHidden()) {
                        showFragment(main_fragment);
                    }
                }
                main_logo.setImageResource(R.mipmap.icon_main_on);
                main_text.setTextColor(getResources().getColor(R.color.icon_bottom_text_on));
                break;
            case 1:
                if (news_fragment == null) {
                    news_fragment = new NewsFragment();
                    addFragment(news_fragment);
                    showFragment(news_fragment);
                } else {
                    if (news_fragment.isHidden()) {
                        showFragment(news_fragment);
                    }
                }
                news_logo.setImageResource(R.mipmap.icon_news_on);
                news_text.setTextColor(getResources().getColor(R.color.icon_bottom_text_on));
                break;
            case 2:
                if (mine_fragment == null) {
                    mine_fragment = new MineFragment();
                    addFragment(mine_fragment);
                    showFragment(mine_fragment);
                } else {
                    if (mine_fragment.isHidden()) {
                        showFragment(mine_fragment);
                    }
                }
                mine_logo.setImageResource(R.mipmap.icon_mine_on);
                mine_text.setTextColor(getResources().getColor(R.color.icon_bottom_text_on));
                break;
        }
    }

    private void resetImgs() {
        main_logo.setImageResource(R.mipmap.icon_main_off);
        news_logo.setImageResource(R.mipmap.icon_news_off);
        mine_logo.setImageResource(R.mipmap.icon_mine_off);

        main_text.setTextColor(getResources().getColor(R.color.icon_bottom_text_off));
        news_text.setTextColor(getResources().getColor(R.color.icon_bottom_text_off));
        mine_text.setTextColor(getResources().getColor(R.color.icon_bottom_text_off));

        /*
        main_layout.setBackground(getResources().getDrawable(R.color.black));
        news_layout.setBackground(getResources().getDrawable(R.color.black));
        mine_layout.setBackground(getResources().getDrawable(R.color.black));
        */
    }

    @Override
    public void finishActivity() {
        this.finish();
    }
}
