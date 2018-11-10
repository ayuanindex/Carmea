package com.ayuan.carmea;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button carmeaView = (Button) findViewById(R.id.btn_carmea);
        Button videoView = (Button) findViewById(R.id.btn_video);

        carmeaView.setOnClickListener(new MyCarmea());
        videoView.setOnClickListener(new MyVideo());
    }

    //点击拍照
    private class MyCarmea implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //这只保存图片的位置以及文件名
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "haha.png");
            //保存图片
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            //开启意图
            startActivityForResult(intent, 3);
        }
    }

    //点击按钮进行录像
    private class MyVideo implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "xixi.mp4");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(intent, 2);
        }
    }

    //当开启的Activity关闭的时候调用
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "这段方法执行了");
        if (data != null) {
            switch (requestCode) {
                case 3:
                    //拍照
                    Log.i(TAG, "刚刚拍照了");
                    break;
                case 2:
                    //录像
                    Log.i(TAG, "刚刚录像了");
                    break;
            }
        }
    }
}
