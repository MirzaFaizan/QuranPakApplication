package com.dmt.attiya.learnquran.fragment;

import android.content.res.AssetFileDescriptor;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.recite_surah_item, parent, false );
        return new reciteSurahViewHolder( view );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((reciteSurahViewHolder) holder).bindView( position );
    }

    @Override
    public int getItemCount() {
        return AllSurahs.data.length;
    }

    private class reciteSurahViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView surahName;

        public reciteSurahViewHolder(View itemView) {
            super( itemView );
            surahName = (TextView) itemView.findViewById( R.id.recite_surah_name );
            itemView.setOnClickListener( this );
        }

        public void bindView(int position) {
            surahName.setText( AllSurahs.data[position] );
        }

        @Override
        public void onClick(View view) {
            Log.d( "SDfsdfsd", String.valueOf( Playing ) );
            ImageView pause_play = view.findViewById( R.id.recite_image_play_pause );
            int index = getAdapterPosition() + 1;

            if (!Playing) {

                pause_play.setImageResource( R.drawable.ic_pause_black_24dp );
                try {
                    AssetFileDescriptor afd = view.getContext().getAssets().openFd( index + ".mp3" );
                    player = new MediaPlayer();
                    player.setDataSource( afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    player.prepare();
                    Playing = true;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.start();
            } else if (Playing) {
                Log.d( "SdfsdfsdfsfdONN", String.valueOf( player ) );
                stopPlaying();

                pause_play.setImageResource( R.drawable.ic_play_arrow_black_24dp );
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


        private void stopPlaying(){
            if (player != null) {
                player.stop();
                player.release();
                player = null;
            }
        }
    }
}
