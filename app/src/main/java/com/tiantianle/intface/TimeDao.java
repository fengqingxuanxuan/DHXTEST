package com.tiantianle.intface;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import com.tiantianle.TimeCountDownTextView;

import java.util.Map;

/**
 * Created by PengBo  on 2017/2/22.
 */

public interface TimeDao {
    void DaoTime(long time, TimeCountDownTextView textView, Button btn);

}
