package com.example.opengl.app.gl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by wei on 16-7-6.
 * 一个只修改背景色的  渲染器
 */
public class BackgroundGLRenderer implements GLSurfaceView.Renderer {

    final long uColor=0xff55ffff; // #AARRGGBB format

    float fRed = (float)((uColor >> 16) & 0xFF) / 0xFF;
    float fGreen = (float)((uColor >> 8) & 0xFF) / 0xFF;
    float fBlue = (float)(uColor & 0xFF) / 0xFF;
    float fAlpha = (float)(uColor >> 24) / 0xFF;


    //系统呼吁各重绘此方法GLSurfaceView。使用此方法作为主要执行点绘制（并重新绘制）图形对象。
    public void onDrawFrame(GL10 unused) {
        // 重绘背景色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * 在创建时，系统调用这个方法一次，GLSurfaceView。
     * 使用此方法来执行只需要发生一次的操作,如设置OpenGL的环境参数或初始化的OpenGL图形对象。
     * @param gl10
     * @param eglConfig
     */
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //使用刚刚我们预定义的背景色 每个参数的value  0-1
        GLES20.glClearColor(
                fRed,
                fGreen,
                fBlue,
                fAlpha);
    }

    //系统会调用这个方法的时候GLSurfaceView几何变化，包括尺寸变化GLSurfaceView或设备屏幕的方向
    // 。例如，当设备从纵向变为横向系统调用此方法。
    // 使用此方法可以在变化的响应GLSurfaceView容器。
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }
}