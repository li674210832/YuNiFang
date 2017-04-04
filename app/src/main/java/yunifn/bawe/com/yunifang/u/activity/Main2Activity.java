package yunifn.bawe.com.yunifang.u.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.ArrayList;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.base.BaseFragment;
import yunifn.bawe.com.yunifang.fragment.Beautfulfragment;
import yunifn.bawe.com.yunifang.fragment.Classifyfragment;
import yunifn.bawe.com.yunifang.fragment.Homefragment;
import yunifn.bawe.com.yunifang.fragment.Shoppingfragment;
import yunifn.bawe.com.yunifang.fragment.Userfragment;

public class Main2Activity extends AppCompatActivity {
    private int position;
    private ArrayList<BaseFragment> mbaseFragments;
    private RadioGroup rg_main;
    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //初始化Fragment
        initFragment();
        initview();
        //设置RadioGroup的监听
        setListener();
    }

    //添加资源
    private void initview() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);


    }


    /**
     * 根据位置得到对应的Fragment
     *
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mbaseFragments.get(position);
        return fragment;
    }

    private void initFragment() {
        mbaseFragments = new ArrayList<>();
        mbaseFragments.add(new Homefragment());
        mbaseFragments.add(new Classifyfragment());
       // mbaseFragments.add(new Beautfulfragment());
        mbaseFragments.add(new Shoppingfragment());
        mbaseFragments.add(new Userfragment());
    }

    private void setListener() {
        rg_main.setOnCheckedChangeListener(new MyOncheck());
        //设置默认选中常用框架
        rg_main.check(R.id.b_home);
    }

    class MyOncheck implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.b_home:
                    position = 0;
                    break;
                case R.id.b_cassify:
                    position = 1;
                    break;

                case R.id.b_shopp:
                    position = 2;
                    break;
                case R.id.b_user:
                    position = 3;
                    break;
            }
            //根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFragment(mContent, to);
        }
    }

    private void switchFragment(Fragment from, Fragment to) {
        if (from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.fl_content, to).commit();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }

    }

}

