package com.tiantianle.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import com.tiantianle.adapter.ShiYouKaMiAdapter;
import com.tiantianle.custom.FlowEntity;

import java.util.ArrayList;
import java.util.List;

public class ShiYouKami extends Activity implements View.OnClickListener {

    protected ImageView mImgShiyoukamiBack;
    protected RadioButton mRadbtnShiyoukami10;
    protected RadioButton mRadbtnShiyoukami20;
    protected RadioButton mRadbtnShiyoukami50;
    protected RadioButton mRadbtnShiyoukami100;
    protected RadioButton mRadbtnShiyoukami200;
    protected RadioButton mRadbtnShiyoukami300;
    protected TextView mTextSuoXuModouShiyoukami;
    protected EditText mEdittextShiyoukami;
    protected Button mBtnQuerenShiyoukami;
    protected ListView mListviewShiyoukami;
    protected LinearLayout mActivityShiYouKami;
    protected FlowLayout mMFlowLayout;
    protected TextView mTextTatilname;
    private List<String> mList;
    private List<Flow> mFlowList;
    private String userid[] = {"1105", "1103", "1103", "1103", "1103", "1103", "1103", "1103"};
    private ShiYouKaMiAdapter mShiYouKaMiAdapter;
    private String warename;
    private String warecode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_shi_you_kami);
        initView();
        initdate();
        initDate();
        Intent intent = getIntent();
        warename=intent.getStringExtra("warename");
        warecode=intent.getStringExtra("warecode");
      mTextTatilname.setText(warename);

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
        mShiYouKaMiAdapter = new ShiYouKaMiAdapter();
        mList = new ArrayList<>();
        for (int i = 0; i < userid.length; i++) {
            mList.add(userid[i]);

        }
        mShiYouKaMiAdapter.setList(mList);
        mListviewShiyoukami.setAdapter(mShiYouKaMiAdapter);
        mShiYouKaMiAdapter.notifyDataSetChanged();


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_shiyoukami_back) {
            finish();
        } else if (view.getId() == R.id.btn_queren_shiyoukami) {

        }
    }

    private void initView() {
        mImgShiyoukamiBack = (ImageView) findViewById(R.id.img_shiyoukami_back);
        mImgShiyoukamiBack.setOnClickListener(ShiYouKami.this);
        mTextSuoXuModouShiyoukami = (TextView) findViewById(R.id.text_suoXu_modou_shiyoukami);
        mEdittextShiyoukami = (EditText) findViewById(R.id.edittext_shiyoukami);
        mBtnQuerenShiyoukami = (Button) findViewById(R.id.btn_queren_shiyoukami);
        mBtnQuerenShiyoukami.setOnClickListener(ShiYouKami.this);
        mListviewShiyoukami = (ListView) findViewById(R.id.listview_shiyoukami);
        mActivityShiYouKami = (LinearLayout) findViewById(R.id.activity_shi_you_kami);
        mMFlowLayout = (FlowLayout) findViewById(R.id.mFlowLayout);
        mTextTatilname = (TextView) findViewById(R.id.text_tatilname);
    }
}
