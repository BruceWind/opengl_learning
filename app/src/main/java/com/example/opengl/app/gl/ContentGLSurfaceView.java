package com.example.opengl.app.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by wei on 16-7-6.
 */
public class ContentGLSurfaceView extends GLSurfaceView {

    private final GLSurfaceView.Renderer mRenderer;

    public ContentGLSurfaceView(Context context){
        super(context);
        // 创建 OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        //mRenderer = new BackgroundGLRenderer();
        mRenderer = new TriangleGLRenderer();
        // 给GLSurfaceview设置一个渲染器
        setRenderer(mRenderer);
        //关闭 GL循环绘制  只在onSurfaceChanged 之后或者 调用了requestRender()之后才发生绘制  减少多余的绘制的CPU消耗
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
