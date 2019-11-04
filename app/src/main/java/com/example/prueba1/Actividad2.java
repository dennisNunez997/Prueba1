package com.example.prueba1;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Actividad2 extends AppCompatActivity {

    private ListView lv1;
    private VideoView vv1;
    private String[] archivos;
    private ArrayAdapter<String> adaptador1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        File dir=getExternalFilesDir(null);
        assert dir != null;
        archivos=dir.list();
        assert archivos != null;
        adaptador1= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, archivos);
        lv1= findViewById(R.id.listView1);
        lv1.setAdapter(adaptador1);

        vv1= findViewById(R.id.videoView1);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                vv1.setVideoURI(Uri.parse(getExternalFilesDir(null)+"/"+archivos[arg2]));
                vv1.start();
            }});

    }
}