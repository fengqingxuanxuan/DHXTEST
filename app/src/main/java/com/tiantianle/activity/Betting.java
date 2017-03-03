package com.tiantianle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiantianle.R;

/*
* 自动投注
* */
public class Betting extends AppCompatActivity implements View.OnClickListener {

    protected ImageView mBighMenu;
    protected TextView mBighText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_betting);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bigh_menu) {
           finish();
        }
    }

    private void initView() {
        mBighMenu = (ImageView) findViewById(R.id.bigh_menu);
        mBighMenu.setOnClickListener(Betting.this);
        mBighText = (TextView) findViewById(R.id.bigh_text);
    }
}
