package yunifn.bawe.com.yunifang.listenner;

import android.content.Context;

/**
 * Created by Lenovo on 2017/3/24.
 * author ：李宁
 * 类注释：
 */

public interface OnRecyclerViewItemClickListener {
    public  void onItmeClick(int position, Context context);
    public  void onInterfaceClick(int position);
    public  void onItmeLongClick(int position);
}
