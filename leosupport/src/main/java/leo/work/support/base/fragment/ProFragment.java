package leo.work.support.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import leo.work.support.support.common.LogUtil;

/**
 * ---------------------------------------------------------------------------------------------
 * 功能描述:
 * ---------------------------------------------------------------------------------------------
 * 时　　间: 2018/4/19.
 * ---------------------------------------------------------------------------------------------
 * 代码创建: 刘桂安
 * ---------------------------------------------------------------------------------------------
 * 代码备注:
 * ---------------------------------------------------------------------------------------------
 **/
public abstract class ProFragment extends Fragment {
    public View view;

    public Context context;
    public Activity activity;

    //数据
    public boolean isLoading = false;//是否正在加载
    //需要改名字
    private boolean hasResume = false;//是否前台（不保证正在显示该页面）
    public boolean hasFront = false;//当前页面是否在前台
    public boolean hidden = false;//Fragment显示/隐藏状态
    public final String onResume = "onResume", onPause = "onPause", onHiddenChanged = "onHiddenChanged";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.e("=======================>" + this.getClass().getName());
        view = inflater.inflate(setLayout(), container, false);
        context = getContext();
        activity = getActivity();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(savedInstanceState);

        initViews(savedInstanceState);

        loadData(true, false);
        initListener();
    }

    protected abstract int setLayout();

    /**
     * 初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 加载View
     *
     * @param savedInstanceState
     */
    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * 加载数据，如：网络请求
     */
    protected void loadData(final boolean isShowState, final boolean isSaveLastData) {

    }

    /**
     * 初始化监听器
     */
    protected void initListener() {

    }


    /**
     * 使用时应该写在这上面
     * ....
     * super.onResume();
     * 不应该写在super下面
     */
    @Override
    public void onResume() {
        super.onResume();
        hasResume = true;
        hasFront = !hidden;
    }

    @Override
    public void onPause() {
        super.onPause();
        hasResume = false;
        hasFront = false;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.hidden = hidden;
        if (hasResume)
            this.hasFront = !hidden;
        else
            this.hasFront = false;
    }

}