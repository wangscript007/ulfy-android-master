package com.ulfy.master.application.vm;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.infrastructure.AppConfig;
import com.ulfy.master.ui.view.ImageProcessView;

import java.util.List;

public class ImageProcessVM extends BaseVM {
    public List<String> imageUrlList;

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    imageUrlList = AppConfig.pictureUrlList.subList(0, 4);
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public LoadDataUiTask.OnExecute clearCacheOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在清除...");
                    ImageUtils.clearDiskCache();
                    task.notifySuccess("清除完成");
                } catch (Exception e) {
                    LogUtils.log("清除失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return ImageProcessView.class;
    }
}