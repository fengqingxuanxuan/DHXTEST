package com.tiantianle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dalong.flowlayout.Flow;
import com.dalong.flowlayout.FlowLayout;
import com.tiantianle.R;
import com.tiantianle.adapter.JingDongEKaListAdapter;
import com.tiantianle.custom.FlowEntity;

import java.util.ArrayList;
import java.util.List;

public class JingDongEka extends AppCompatActivity implements View.OnClickListener {

    protected ImageView mImgJingdongEkaBack;
    protected RadioButton mRadbtnJindongEka10;
    protected RadioButton mRadbtnJindongEka20;
    protected RadioButton mRadbtnJindongEka50;
    protected RadioButton mRadbtnJindongEka100;
    protected RadioButton mRadbtnJindongEka200;
    protected RadioButton mRadbtnJindongEka300;
    protected TextView mTextSuoXuModouJingdongeka;
    protected ListView mListviewJingdongeka;
    protected LinearLayout mActivityJingDongEka;
    protected EditText mEdittextJingdongeka;
    protected Button mBtnQuerenJingdongeka;
    protected FlowLayout mMFlowLayout;
    private JingDongEKaListAdapter mJingDongEKaListAdapter;
    private List<String> mList;
    private List<Flow> mFlowList;
    private String userid[] = {"1105", "1103", "1103", "1103", "1103", "1103", "1103", "1103"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_jing_dong_eka);
        initView();
        initdate();
        initDate();
    }

    private void initDate() {
        mFlowList = getPhoneList();
        mMFlowLayout.setFlowData(mFlowList);

    }

    public List<Flow> getPhoneList() {
        List<Flow> list = new ArrayList<>();
        Flow mFlow = new FlowEntity("1", "10元");
        Flow mFlow2 = new FlowEntity("2", "20元");
        Flow mFlow3 = new FlowEntity("3", "50元");
        Flow mFlow4 = new FlowEntity("4", "100元");
        Flow mFlow5 = new FlowEntity("5", "200元");
        Flow mFlow6 = new FlowEntity("6", "300元");
        list.add(mFlow);
        list.add(mFlow2);
        list.add(mFlow3);
        list.add(mFlow4);
        list.add(mFlow5);
        list.add(mFlow6);
        return list;
    }


    private void initdate() {
        mList = new ArrayList<>();
        mJingDongEKaListAdapter = new JingDongEKaListAdapter();
        for (int i = 0; i < userid.length; i++) {
            mList.add(userid[i]);
        }
        mJingDongEKaListAdapter.setList(mList);
        mListviewJingdongeka.setAdapter(mJingDongEKaListAdapter);
        mJingDongEKaListAdapter.notifyDataSetChanged();


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_jingdongEka_back) {
            finish();
        } else if (view.getId() == R.id.btn_queren_jingdongeka) {

        }
    }

    private void initView() {
        mImgJingdongEkaBack = (ImageView) findViewById(R.id.img_jingdongEka_back);
        mImgJingdongEkaBack.setOnClickListener(JingDongEka.this);
        mTextSuoXuModouJingdongeka = (TextView) findViewById(R.id.text_suoXu_modou_jingdongeka);
        mListviewJingdongeka = (ListView) findViewById(R.id.listview_jingdongeka);
        mActivityJingDongEka = (LinearLayout) findViewById(R.id.activity_jing_dong_eka);
        mEdittextJingdongeka = (EditText) findViewById(R.id.edittext_jingdongeka);
        mEdittextJingdongeka.setOnClickListener(JingDongEka.this);
        mBtnQuerenJingdongeka = (Button) findViewById(R.id.btn_queren_jingdongeka);
        mBtnQuerenJingdongeka.setOnClickListener(JingDongEka.this);
        mMFlowLayout = (FlowLayout) findViewById(R.id.mFlowLayout);
    }
}
