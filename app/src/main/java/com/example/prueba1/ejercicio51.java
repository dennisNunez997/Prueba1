package com.example.prueba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.VideoView;

import java.io.File;

public class ejercicio51 extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;
    private EditText et1;
    private VideoView vv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio51);

        et1=(EditText)findViewById(R.id.editText1);
        vv1=(VideoView)findViewById(R.id.videoView1);
    }


    public void dispatchTakeVideoIntent(View view) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File video = new File(getExternalFilesDir(null), et1.getText().toString());
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(video));
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
            et1.setText("");
        }
    }

    public void recuperarVideo(View v) {
        vv1.setVideoURI(Uri.parse(getExternalFilesDir(null)+"/"+et1.getText().toString()));
        vv1.start();
    }

    public void ver(View v) {
        Intent intento1=new Intent(this,Actividad2.class);
        startActivity(intento1);
    }

}
