package com.softgarden.catchdolls.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.softgarden.baselibrary.widget.CommonToolbar;
import com.softgarden.catchdolls.R;

import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


/**
 */

public abstract class ToolbarFragment extends RefreshFragment {
    private CommonToolbar mCommonToolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(getLayoutId(), container, false);
        mView = setSupportToolbar(mView);
        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    private View setSupportToolbar(View mView) {
        /*** 这里可以对Toolbar进行统一的预设置 */
        CommonToolbar.Builder builder = new CommonToolbar.Builder()
                .showStatusBar(Color.TRANSPARENT)//统一设置为透明
                .setAllTextColor(ContextCompat.getColor(getContext(), R.color.white));

        builder = setToolbar(builder);
        if (builder != null) {
            mCommonToolbar = builder.build(getContext());
        }
        if (mCommonToolbar != null) {
            //添加Toolbar
            LinearLayout layout = new LinearLayout(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
            layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(mCommonToolbar);
            layout.addView(mView);
            return layout;
        }
        return mView;
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
