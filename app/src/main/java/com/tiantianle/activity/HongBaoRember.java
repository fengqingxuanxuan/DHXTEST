package com.tiantianle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.tiantianle.R;
import com.tiantianle.adapter.HongBaoDuiaHuanAdapter;

import java.util.ArrayList;
import java.util.List;

public class HongBaoRember extends AppCompatActivity implements View.OnClickListener {

    protected ImageView mImgHongbaoRemberBack;
    protected ListView mListviewHongbaoDuiHuan;
    private String str[] = {"100", "200", "300", "400", "500", "600", "700"};
    private List<String> mList;
    private HongBaoDuiaHuanAdapter mHongBaoDuiaHuanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_hong_bao_rember);
        initView();
        intidate();
    }

    private void intidate() {
        mList = new ArrayList<>();
        mHongBaoDuiaHuanAdapter = new HongBaoDuiaHuanAdapter();
        for (int i = 1; i < str.length; i++) {
            mList.add(str[i]);
        }
        mHongBaoDuiaHuanAdapter.setList(mList);
        mListviewHongbaoDuiHuan.setAdapter(mHongBaoDuiaHuanAdapter);
        mHongBaoDuiaHuanAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_hongbaoRember_back) {
            finish();
        }
    }

    private void initView() {
        mImgHongbaoRemberBack = (ImageView) findViewById(R.id.img_hongbaoRember_back);
        mImgHongbaoRemberBack.setOnClickListener(HongBaoRember.this);
        mListviewHongbaoDuiHuan = (ListView) findViewById(R.id.listview_hongbao_DuiHuan);
    }
}
