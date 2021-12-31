package leo.work.support.base.adapter;

import android.content.Context;
import android.view.View;

import androidx.databinding.ViewDataBinding;

/**
 * ---------------------------------------------------------------------------------------------
 * 功能描述: 通用的ViewHolder
 * ---------------------------------------------------------------------------------------------
 * 时　　间: 2021/12/31
 * ---------------------------------------------------------------------------------------------
 * 代码创建: 1613-3
 * ---------------------------------------------------------------------------------------------
 * 代码备注: 提供给CommonListViewAdapter使用的
 * ---------------------------------------------------------------------------------------------
 **/
public abstract class CommonViewHolder<M, B extends ViewDataBinding, C> {

    public B binding;
    public C callBack;
    public Context context;

    public CommonViewHolder(Context context, B binding, C callBack) {
        this.context = context;
        this.binding = binding;
        this.callBack = callBack;
    }

    protected abstract void initView( final int position, final M bean);

    protected abstract void initListener( final int position, final M bean);
}
