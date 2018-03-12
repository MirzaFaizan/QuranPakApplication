package com.dmt.faizanmaaz.learnquran.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmt.faizanmaaz.learnquran.R;

/**
 * Created by Adnan Ejaz on 3/11/2018.
 */

public class SurahFragment extends android.support.v4.app.Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_surah_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvSurahs);
        surahAdapter listAdapter = new surahAdapter();
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
