package com.zhaoyuanjie.instagramfilter.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class InstagramFilterView extends GLSurfaceView {

    private GLLayer render;

    public InstagramFilterView(Context context) {
        super(context);
        init();
    }

    public InstagramFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        render = new GLLayer(getContext());
        setRenderer(render);
    }

    public void setBitmap(Bitmap bitmap) {
        render.setBitmap(bitmap);
        requestRender();
    }

    /**
     * Set filter
     * @param filter filter
     * @see com.zhaoyuanjie.instagramfilter.library.GLLayer#setFilter(int)
     */
    public void setFilter(int filter) {
        render.setFilter(filter);
        requestRender();
    }
}
