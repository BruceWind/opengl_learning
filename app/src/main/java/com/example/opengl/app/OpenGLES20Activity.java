package com.example.opengl.app;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import com.example.opengl.app.gl.ContentGLSurfaceView;

/**
 * Created by wei on 16-7-6.
 */
public class OpenGLES20Activity extends Activity {

    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 把一个GLSurafaceView设置到 contentview上去
        mGLView = new ContentGLSurfaceView(this);
        setContentView(mGLView);
    }
}