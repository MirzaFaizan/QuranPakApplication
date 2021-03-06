package com.dmt.attiya.learnquran.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmt.attiya.learnquran.AllSurahs;

import com.dmt.attiya.learnquran.R;
import com.dmt.attiya.learnquran.SurahReader;

/**
 * Created by Adnan Ejaz on 3/11/2018.
 */

public class surahAdapter extends RecyclerView.Adapter {
    @
            Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_item,parent,false);
        return new surahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((surahViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return AllSurahs.data.length;
    }


    private class surahViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView surahName;

        public surahViewHolder(View itemView){
            super(itemView);
            surahName = (TextView) itemView.findViewById(R.id.surah_name);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            surahName.setText(AllSurahs.data[position]);
        }
        @Override
        public void onClick(View view) {
            Log.d("dfgdfgdf", String.valueOf( getAdapterPosition() ) );
            Intent surahSelecter = new Intent(view.getContext(), SurahReader.class);
            surahSelecter.putExtra("index",getAdapterPosition()+1);
            view.getContext().startActivity(surahSelecter);

        }
    }
}
