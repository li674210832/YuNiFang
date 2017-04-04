package yunifn.bawe.com.yunifang.fragment;

import android.view.View;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.base.BaseFragment;

/**
 * Created by Lenovo on 2017/3/16.
 * author ：李宁
 * 类注释：
 */

public class Beautfulfragment extends BaseFragment {
    //类似于onCreatView
    @Override
    protected View initview() {
        View vv = View.inflate(mContext, R.layout.beautfulxml, null);

        return vv;
    }
//类似于onCreatActivity
    @Override
    protected void initData() {
        super.initData();
    }
}
