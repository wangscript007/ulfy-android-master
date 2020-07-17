package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.master.application.vm.List1VM;
import com.ulfy.master.ui.base.TitleContentActivity;
import com.ulfy.master.ui.view.List1View;

public class List1Activity extends TitleContentActivity {
    private List1VM vm;
    private List1View view;

    /**
     * 启动Activity
     */
    public static void startActivity() {
        ActivityUtils.startActivity(List1Activity.class);
    }

    /**
     * 初始化方法
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initModel(savedInstanceState);
        initContent(savedInstanceState);
        initActivity(savedInstanceState);
    }

    /**
     * 初始化模型和界面
     */
    private void initModel(Bundle savedInstanceState) {
        vm = new List1VM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.contentTaskInfo, vm.loadContentDataPerPageOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreatView(ContentDataLoader loader, View createdView) {
                        view = (List1View) createdView;
                    }
                }.setOnReloadListener(new OnReloadListener() {
                    @Override public void onReload() {
                        initContent(savedInstanceState);
                    }
                })
        );
    }

    /**
     * 初始化Activity的数据
     */
    private void initActivity(Bundle savedInstanceState) {
        titleTV.setText("列表一");
    }
}