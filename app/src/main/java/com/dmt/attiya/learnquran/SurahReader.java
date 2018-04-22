package com.dmt.attiya.learnquran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.res.AssetManager;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SurahReader extends AppCompatActivity {
    TextView textView;
    String surahtext;
    int size;
    byte[] buffer;
    InputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_surah_reader );
        surah_loader( getIntent().getExtras().getInt( "index" ) );
    }
    //===========================================================================


    public String surah_loader(int index) {
        textView = (TextView) findViewById( R.id.textViewer );
        Log.d( "index", String.valueOf( index ) );

        surahtext = "";

        String arabicText = "Failed to access the surah text at index";

        surahtext = "";
        try{
            is = getAssets().open(index+".txt");
            size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            surahtext = new String(buffer);

            Log.d( "eerwe",surahtext );
        }
        catch (IOException exception){
                Log.d( "sdfs",exception.getMessage() );
            exception.printStackTrace();
        }
            textView.setText( surahtext );
        return arabicText;
    }

}
