package com.tiantianle.fragment;

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;

        import com.google.gson.Gson;
        import com.handmark.pulltorefresh.library.PullToRefreshListView;
        import com.tiantianle.Bean.WinBean;
        import com.tiantianle.R;
        import com.tiantianle.adapter.WinningNameAdapter;
        import com.tiantianle.utils.Constant;
        import com.tiantianle.utils.HttpApi;
        import com.tiantianle.utils.ToastUtils;

        import org.xutils.common.Callback;
        import org.xutils.http.RequestParams;
        import org.xutils.x;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by PengBo  on 2017/2/23.
 * 中奖名单
 */

public class WinningName extends Fragment {
    protected View rootView;
    protected PullToRefreshListView mPullRefreshListFramWinningname;
   private  String   expect;
   private  String  type;
    private List<WinBean.BizContentBean> mBizContentBeen;
    private WinningNameAdapter mWinningNameAdapter;

    public static WinningName newInstance(String expect,String type) {

        Bundle args = new Bundle();
        args.putString("expect",expect);
        args.putString("type",type);
        WinningName fragment = new WinningName();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_winningname, null, false);
        initView(rootView);
        Bundle arguments = getArguments();
        expect = arguments.getString("expect");
        type = arguments.getString("type");
        HttpData();
        return rootView;
    }

    private void HttpData(){
        RequestParams entity = new RequestParams(HttpApi.WINNINGNAME);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("type",type);
        entity.addParameter("expect",expect);
        entity.addParameter("imei", Constant.Config.imei);

        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                WinBean winBean = gson.fromJson(result, WinBean.class);
                if(winBean.getState().equals("success")){
                    mBizContentBeen=new ArrayList<WinBean.BizContentBean>();
                    mBizContentBeen=winBean.getBiz_content();
                    mBizContentBeen.add(0,null);
                    mWinningNameAdapter=new WinningNameAdapter();
                    mWinningNameAdapter.setList(mBizContentBeen);
                    mPullRefreshListFramWinningname.setAdapter(mWinningNameAdapter);
                    mWinningNameAdapter.notifyDataSetChanged();
                }else if(winBean.getState().equals("error")){
                    Toast.makeText(getContext(),"暂无数据",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("onError中奖名单",ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {
            }
        });
    }

    private void initView(View rootView) {
        mPullRefreshListFramWinningname = (PullToRefreshListView) rootView.findViewById(R.id.pull_refresh_list_fram_winningname);
    }
}
