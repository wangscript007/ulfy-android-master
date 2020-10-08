package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dueeeke.videoplayer.player.VideoView;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.BuildConfig;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.DouyinCM;
import com.ulfy.master.ui.base.BaseCell;
import com.ulfy.master.ui.custom_dkplayer.TikTokController;
import com.ulfy.master.ui.custom_dkplayer.VideoViewRepository;
import com.ulfy.master.ui.custom_dkplayer.cache.PreloadManager;

@Layout(id = R.layout.cell_douyin)
public class DouyinCell extends BaseCell {
    @ViewById(id = R.id.containerFL) private FrameLayout containerFL;
    @ViewById(id = R.id.douyinVV) private VideoView douyinVV;
    private TikTokController tikTokController;
    private DouyinCM cm;

    public DouyinCell(Context context) {
        super(context);
        init(context, null);
    }

    public DouyinCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
//        videoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        tikTokController = new TikTokController(getContext());
        tikTokController.getThumbIV().setScaleType(ImageView.ScaleType.FIT_CENTER);
        douyinVV.setVideoController(tikTokController);
        VideoViewRepository.getInstance().addVideoView(douyinVV);
    }

    /*
    滑动时图片闪动是因为视频中的缩略图被后加载时导致的，这里需要想个办法处理。
     */

    @Override public void bind(IViewModel model) {
        cm = (DouyinCM) model;
        ImageUtils.loadImage(cm.imageUrl, android.R.color.white, tikTokController.getThumbIV());
        if (BuildConfig.VIDEO_PRE_LOAD) {
            douyinVV.setUrl(PreloadManager.getInstance(getContext()).getPlayUrl(cm.videoUrl));
        } else {
            douyinVV.setUrl(cm.videoUrl);
        }
    }

    public void onItemSelected() {
        VideoViewRepository.getInstance().releaseVideoView(getContext(), false);
        douyinVV.start();
    }

    public int getIndex() {
        return cm.index;
    }
}