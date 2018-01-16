package com.softgarden.catchdolls.base;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.softgarden.baselibrary.base.BaseActivity;
import com.softgarden.baselibrary.utils.ContextUtil;
import com.softgarden.baselibrary.widget.CommonToolbar;
import com.softgarden.catchdolls.R;

import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


/**
 */

public abstract class ToolbarActivity extends BaseActivity {
    private CommonToolbar mCommonToolbar;


    @Override
    protected void initContentView() {
        /*** 这里可以对Toolbar进行统一的预设置 */
        CommonToolbar.Builder builder = new CommonToolbar.Builder()
                .setBackButton(R.mipmap.back)//统一设置返回键
                .showStatusBar(Color.TRANSPARENT)//统一设置为透明
                .setBackgroundColor(ContextUtil.getColor(R.color.pink))
                .setAllTextColor(ContextCompat.getColor(this, R.color.white));

        builder = setToolbar(builder);
        if (builder != null) {
            mCommonToolbar = builder.build(this);
        }
        if (mCommonToolbar != null) {
            //添加Toolbar
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
            layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(mCommonToolbar);
            View mView = getLayoutInflater().inflate(getLayoutId(), layout, false);
            layout.addView(mView);
            setContentView(layout);
            //将toolbar设置为actionbar
            setSupportActionBar(mCommonToolbar);
        } else {
            setContentView(getLayoutId());
        }
        //ButterKnife
        unbinder = ButterKnife.bind(this);
    }


    public CommonToolbar getToolbar() {
        return mCommonToolbar;
    }


    public void showToolbar() {
        if (mCommonToolbar != null) mCommonToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        if (mCommonToolbar != null) mCommonToolbar.setVisibility(View.GONE);
    }


    /**
     * 不需要toolbar的 可以不用管
     *
     * @return
     */
    @Nullable
    protected abstract CommonToolbar.Builder setToolbar(@NonNull CommonToolbar.Builder builder);
}
