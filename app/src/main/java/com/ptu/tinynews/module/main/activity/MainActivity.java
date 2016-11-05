package com.ptu.tinynews.module.main.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ptu.tinynews.R;
import com.ptu.tinynews.app.Constant;
import com.ptu.tinynews.base.BaseActivity;
import com.ptu.tinynews.contract.MainContract;
import com.ptu.tinynews.module.main.presenter.MainPresenter;
import com.ptu.tinynews.module.yi.fragment.NewsFragment;
import com.ptu.tinynews.module.zhihu.fragment.ZhihuFragment;
import com.ptu.tinynews.util.SnackbarUtil;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

import static com.ptu.tinynews.R.id.drawerLayout;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View
{

    @BindView(drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    ActionBarDrawerToggle mDrawerToggle;
    ZhihuFragment mZhihuFragment;
    NewsFragment newsFragment;
    MenuItem mLastMenuItem;
    private int hideFragment = Constant.TYPE_WANGYI;
    private int showFragment = Constant.TYPE_WANGYI;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject()
    {
        getActivityComponent().inject(this);
    }


    @Override
    protected void initEventAndData()
    {
        setToolBar(mToolbar,"网易新闻");
        mZhihuFragment = new ZhihuFragment();
        newsFragment = new NewsFragment();
        loadMultipleRootFragment(R.id.fl_content, 0, newsFragment, mZhihuFragment);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mLastMenuItem = mNavigationView.getMenu().findItem(R.id.drawer_wangyi);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.drawer_wangyi:
                        showFragment = Constant.TYPE_WANGYI;
                        break;
                    case R.id.drawer_zhihu:
                        showFragment = Constant.TYPE_ZHIHU;
                        break;
                }
                //取消上一次选中项
                if(mLastMenuItem != null) {
                    mLastMenuItem.setChecked(false);
                }
                menuItem.setChecked(true);
                mToolbar.setTitle(menuItem.getTitle());
                mDrawerLayout.closeDrawers();
                showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
                hideFragment = showFragment;
                return true;
            }
        });
    }


    private SupportFragment getTargetFragment(int item)
    {
        switch (item)
        {
            case Constant.TYPE_WANGYI:
                return newsFragment;
            case Constant.TYPE_ZHIHU:
                return mZhihuFragment;
        }
        return newsFragment;
    }

    private int getCurrentItem(int item)
    {
        switch (item)
        {
            case Constant.TYPE_WANGYI:
                return R.id.drawer_wangyi;
            case Constant.TYPE_ZHIHU:
                return R.id.drawer_zhihu;

        }
        return R.id.drawer_wangyi;
    }

    private FragmentTransaction getTransition()
    {
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        return transition;
    }


    @Override
    public void showMessage(String msg)
    {
        SnackbarUtil.showShort(mToolbar, msg);
    }

}
