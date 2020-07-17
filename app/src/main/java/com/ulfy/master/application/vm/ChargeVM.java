package com.ulfy.master.application.vm;

import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.ui.view.ChargeView;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;

public class ChargeVM extends BaseVM {

    /**
     * 加载数据：
     */
    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");

                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    e.printStackTrace();
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override
    public Class<? extends IView> getViewClass() {
        return ChargeView.class;
    }
}