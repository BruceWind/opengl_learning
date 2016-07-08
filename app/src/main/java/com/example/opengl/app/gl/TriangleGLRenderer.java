package com.example.opengl.app.gl;

import android.opengl.GLSurfaceView;
import com.example.opengl.app.component.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by wei on 16-7-6.
 */
public class TriangleGLRenderer extends BackgroundGLRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        super.onSurfaceCreated(gl10,eglConfig);
        // 实例化这个三角形 triangle
        mTriangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

        super.onSurfaceChanged(gl10,i,i1);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {

        super.onDrawFrame(gl10);
        mTriangle.draw();
    }
}
