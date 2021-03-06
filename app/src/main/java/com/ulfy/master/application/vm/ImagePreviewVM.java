package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.FileUtils;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.MainApplication;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.ImagePreviewCM;
import com.ulfy.master.infrastructure.AppConfig;
import com.ulfy.master.ui.view.ImagePreviewView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagePreviewVM extends BaseVM {
    public List<String> imageUrlWithoutContainerList;
    public List<String> imageUrlWithContainerList;
    public List<ImagePreviewCM> imageCMList = new ArrayList<>();
    public File downloadImageFile;

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    imageUrlWithoutContainerList = AppConfig.pictureUrlList.subList(4, 10);
                    imageUrlWithContainerList = AppConfig.pictureUrlList.subList(0, 4);
                    for (String imageUrl : imageUrlWithContainerList) {
                        imageCMList.add(new ImagePreviewCM(imageUrl));
                    }
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public LoadDataUiTask.OnExecute downloadImageOnExe(int position) {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在保存...");
                    downloadImageFile = File.createTempFile("preview", ".jpg", MainApplication.application.getFilesDir());
                    FileUtils.download(imageUrlWithoutContainerList.get(position), downloadImageFile, null);
                    task.notifySuccess("保存完成");
                } catch (Exception e) {
                    LogUtils.log("保存失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return ImagePreviewView.class;
    }
}