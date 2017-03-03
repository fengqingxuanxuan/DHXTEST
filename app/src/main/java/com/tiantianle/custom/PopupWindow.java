package com.tiantianle.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiantianle.R;
import com.tiantianle.view.AmountView;

/**
 * Created by PengBo  on 2017/2/7.
 */

public class PopupWindow extends android.widget.PopupWindow {



    private final View mInflate;
    protected View rootView;
    protected ImageView mImgPopuIndinaBuyCloss;
    protected TextView mTextPopuIndinanBuy;
    protected Button mBtnPopuIndinaBut5;
    protected Button mBtnPopuIndinaBut20;
    protected Button mBtnPopuIndinaBut50;
    protected Button mBtnPopuIndinaBut100;
    protected Button mBtnQuerenPopuIndina;


    public PopupWindow(final Activity context, int i) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflate = inflater.inflate(R.layout.item_popu_indinan_buy, null);
        mBtnQuerenPopuIndina = (Button) mInflate.findViewById(R.id.btn_queren_popu_indina);
        mImgPopuIndinaBuyCloss = (ImageView) mInflate.findViewById(R.id.img_popu_indina_buy_closs);
        mBtnPopuIndinaBut5 = (Button) mInflate.findViewById(R.id.btn_popu_indina_but_5);
        mBtnPopuIndinaBut20 = (Button) mInflate.findViewById(R.id.btn_popu_indina_but_20);
        mBtnPopuIndinaBut50 = (Button) mInflate.findViewById(R.id.btn_popu_indina_but_50);
        mBtnPopuIndinaBut100 = (Button) mInflate.findViewById(R.id.btn_popu_indina_but_100);



        //设置SelectPicPopupWindow的View
        this.setContentView(mInflate);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.isOutsideTouchable();
        this.setTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.update();
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
    /*    mInflate.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = mInflate.findViewById(R.id.populayout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });*/
        mBtnPopuIndinaBut5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        mBtnPopuIndinaBut20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"这是5元",Toast.LENGTH_SHORT).show();
              /*  mTextPopuIndinanBuy.setText(mBtnPopuIndinaBut20.getText().toString());*/
          // mAmountView.setMoney(mBtnPopuIndinaBut20.getText().toString());

            }
        });
        mBtnPopuIndinaBut50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"这是5元",Toast.LENGTH_SHORT).show();

            }
        });
        mBtnPopuIndinaBut100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"这是5元",Toast.LENGTH_SHORT).show();

            }
        });

        mImgPopuIndinaBuyCloss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mBtnQuerenPopuIndina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"购买成功",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }
}

