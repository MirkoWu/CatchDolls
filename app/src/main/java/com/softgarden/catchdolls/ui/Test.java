package com.softgarden.catchdolls.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.softgarden.baselibrary.base.BaseRVAdapter;
import com.softgarden.baselibrary.base.BaseRVHolder;
import com.softgarden.baselibrary.utils.ToastUtil;
import com.softgarden.baselibrary.widget.BottomListDialog;
import com.softgarden.baselibrary.widget.CommonToolbar;
import com.softgarden.catchdolls.R;
import com.softgarden.catchdolls.base.ToolbarActivity;
import com.softgarden.catchdolls.bean.CatchRecordBean;
import com.softgarden.catchdolls.network.LocalTransformer;
import com.softgarden.catchdolls.widget.PromptDialog;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author by DELL
 * @date on 2017/12/23
 * @describe
 */

public class Test extends ToolbarActivity {

    private BaseRVAdapter<CatchRecordBean> mAdapter;

    @Nullable
    @Override
    protected CommonToolbar.Builder setToolbar(@NonNull CommonToolbar.Builder builder) {
        return builder.showTextRight("ss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.s("");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initialize() {
        mAdapter = new BaseRVAdapter<CatchRecordBean>(R.layout.layout_recyclerview_refresh) {

            @Override
            public void onBindVH(BaseRVHolder holder, CatchRecordBean data, int position) {
                holder.setText(0, "");
            }
        };
        mAdapter.setNewData(null);

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onNext("");
            }
        }).compose(new LocalTransformer(this));

    }


    public static void start(Context context, int type) {
        new BottomListDialog().setOnItemClickListener(new BottomListDialog.OnItemClickListener<String>() {
            @Override
            public boolean onItemClick(String data, int position) {
                if (position == 1) {
                    ToastUtil.s("");
                } else if (position == 2) {
                    ToastUtil.s("");
                }
                return false;
            }
        });

        new PromptDialog().setTitle("")
                .setOnButtonClickListener(new PromptDialog.OnDialogClickListener() {
                    @Override
                    public void onDialogClick(PromptDialog dialog,boolean isPositiveClick) {

                    }

                });

    }
}
