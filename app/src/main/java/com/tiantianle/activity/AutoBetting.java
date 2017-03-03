package com.tiantianle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiantianle.R;

public class AutoBetting extends AppCompatActivity implements View.OnClickListener {

    protected ImageView mImgAutoBetting;
    protected TextView mTextAutoBetting;
    protected LinearLayout mActivityAutoBetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_auto_betting);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_autoBetting) {
            finish();

        }
    }

    private void initView() {
        mImgAutoBetting = (ImageView) findViewById(R.id.img_autoBetting);
        mImgAutoBetting.setOnClickListener(AutoBetting.this);
        mTextAutoBetting = (TextView) findViewById(R.id.text_autoBetting);
        mActivityAutoBetting = (LinearLayout) findViewById(R.id.activity_auto_betting);
    }
}
