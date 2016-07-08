package com.example.opengl.app.component;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;

public class Triangle {
    // 顶点着色器的代码
    private final String vsCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    // 片段着色器源代码
    private final String fsCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private int program;
    private int vertexShader;
    private int fragmentShader;
    private FloatBuffer vertexBuffer;
    private int vertexCount = 3;

    // 每个顶点的坐标数在这个数组
    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = {   // 逆时针顺序
            0.0f,  0.9f, 0.0f, // top vertex
            -0.5f,  0.1f, 0.0f, // bottom left
            0.5f,  0.1f, 0.0f  // bottom right
    };

    //设置显示对象的颜色
    //用红、绿、蓝和  透明度值
    float color[] = { 0.9f, 0.1f, 0.9f, 1.0f };

    // 应该在 渲染器 onSurfaceCreated（）调用时执行这个构造方法
    public Triangle(){
        // 创建一个空的 GL程序
        program = GLES20.glCreateProgram();//注：编译的OpenGL ES着色器和链接程序 在CPU耗时方面非常的昂贵
        vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vsCode);
        fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fsCode);
        GLES20.glAttachShader ( program, vertexShader );// add the vertex shader to program
        GLES20.glAttachShader(program, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(program);                  // creates OpenGL ES program executables
        GLES20.glUseProgram( program);                  // use shader program

        // initialize vertex byte buffer for shape coordinates with parameters
        // (number of coordinate values * 4 bytes per float)
        // use the device hardware's native byte order
        ByteBuffer bb = ByteBuffer.allocateDirect( triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);
    } //Triangle Constructor

    public static int loadShader (int type, String shaderCode ) {

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // pass source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public void draw() {
        // 添加 program 到OpenGL ES环境
        GLES20.glUseProgram(program);

        // get handle to vertex shader's attribute variable vPosition
        int positionHandle = GLES20.glGetAttribLocation(program, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false, 0, vertexBuffer);

        // 得到处理对片段着色器的vColor成员
        int colorHandle = GLES20.glGetUniformLocation(program, "vColor");

        //设置绘制三角形的颜色
        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        // 开始绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
    }
}
