package com.dmt.faizanmaaz.learnquran.fragment;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dmt.faizanmaaz.learnquran.AllSurahs;

import com.dmt.faizanmaaz.learnquran.MainActivity;
import com.dmt.faizanmaaz.learnquran.R;
import com.dmt.faizanmaaz.learnquran.SurahReader;

import java.io.IOException;

/**
 * Created by Adnan Ejaz on 3/11/2018.
 */

public class reciteSurahAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recite_surah_item,parent,false);
        return new reciteSurahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((reciteSurahViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return AllSurahs.data.length;
    }

    private class reciteSurahViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView surahName;

        public reciteSurahViewHolder(View itemView){
            super(itemView);
            surahName = (TextView) itemView.findViewById(R.id.recite_surah_name);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            surahName.setText(AllSurahs.data[position]);
        }
        @Override
        public void onClick(View view) {
           int index = getAdapterPosition()+1;

            MediaPlayer player = new MediaPlayer();
            try {
                AssetFileDescriptor afd = view.getContext().getAssets().openFd(index+".mp3");
                player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            player.start();
        }
    }
}
