package com.dmt.attiya.learnquran.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dmt.attiya.learnquran.AllSurahs;

import com.dmt.attiya.learnquran.R;

import java.io.IOException;

/**
 * Created by Adnan Ejaz on 3/11/2018.
 */

public class reciteSurahAdapter extends RecyclerView.Adapter {
    MediaPlayer player;
    private boolean Playing = false;
    private int storingPosition;
    private AlertDialog.Builder builder;
    private Dialog dialog;
private Context context;

    public reciteSurahAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.recite_surah_item, parent, false );
        return new reciteSurahViewHolder( view );
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((reciteSurahViewHolder) holder).bindView( position );
        ((reciteSurahViewHolder) holder).surahName.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    playingAudio(position,view);
            }
        } );


        ((reciteSurahViewHolder) holder).pause_play.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playingAudio(position,view);
            }
        } );


    }

    private void playingAudio(Integer position,View view){
        int index = position + 1;
        Log.d( "indexValue", String.valueOf( index ) );
        dialog = new Dialog( context );
        builder = new AlertDialog.Builder( context );
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );


        //this is custom dialog
        //custom_popup_dialog contains textview only
        View customView = inflater.inflate( R.layout.seek_bar_android, null );


        // reference the textview of custom_popup_dialog
        builder.setCancelable( false );
        Button close = (Button) customView.findViewById( R.id.cancel );
        Log.d( "indexValue", String.valueOf( index ) );

        builder.setTitle( AllSurahs.data[index-1] );

        close.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();
                dialog.dismiss();


            }
        } );

        builder.setView( customView );
        dialog = builder.create();
        dialog.show();

        if (!Playing) {

            //      ((reciteSurahViewHolder) holder).pause_play.setImageResource( R.drawable.ic_pause_black_24dp );
            try {
                AssetFileDescriptor afd = view.getContext().getAssets().openFd( index + ".mp3" );
                player = new MediaPlayer();
                player.setDataSource( afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                player.prepare();
                // Playing = true;

            } catch (IOException e) {
                e.printStackTrace();
            }

            player.start();
        } else if (Playing) {
            Log.d( "SdfsdfsdfsfdONN", String.valueOf( player ) );
            stopPlaying();

//                    ((reciteSurahViewHolder) holder).pause_play.setImageResource( R.drawable.ic_play_arrow_black_24dp );
            try {
                AssetFileDescriptor afd = view.getContext().getAssets().openFd( index + ".mp3" );
                player = new MediaPlayer();
                player.setDataSource( afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                player.prepare();
                Playing = false;

            } catch (IOException e) {
                e.printStackTrace();
            }



        }

    }
    private void lifecycle(SeekBar seekBar){

    }


    @Override
    public int getItemCount() {
        return AllSurahs.data.length;
    }


    private void stopPlaying() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    private class reciteSurahViewHolder extends RecyclerView.ViewHolder{
        private TextView surahName;
        private ImageView pause_play;

        public reciteSurahViewHolder(View itemView) {
            super( itemView );
            surahName = (TextView) itemView.findViewById( R.id.recite_surah_name );
             pause_play = (ImageView)itemView.findViewById( R.id.recite_image_play_pause );


        }

        public void bindView(int position) {
            surahName.setText( AllSurahs.data[position] );
        }


    }
}
