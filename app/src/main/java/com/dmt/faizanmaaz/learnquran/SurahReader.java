package com.dmt.faizanmaaz.learnquran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SurahReader extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_reader);
        surah_loader(getIntent().getExtras().getInt("index"));
    }
    //===========================================================================


    public String surah_loader(int index){
        textView=(TextView)findViewById(R.id.textViewer);
        String arabicText = "Failed to access the surah text at index";
        try {
            AssetManager assetManager = getAssets();
            InputStream ims = assetManager.open(index+".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(ims);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String reader=bufferedReader.readLine();

            while(reader != null) {
                textView.setText(reader);
                reader=bufferedReader.readLine();

            }

        }
        catch(Exception exptn) {
            textView.setText(exptn.getMessage());
        }
        return arabicText;
    }

}
