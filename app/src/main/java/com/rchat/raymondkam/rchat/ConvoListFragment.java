package com.rchat.raymondkam.rchat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ConvoListFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ConvoListAdapter mConvoListAdapter = new ConvoListAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_convo_list, container, false);

        // Setup for RecyclerView to prevent NullPointerException
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.convoList);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up adapter
        mRecyclerView.setAdapter(mConvoListAdapter);

        for (int i = 0; i < 40; i++){
            mConvoListAdapter.addItem("Item "+i);
        }
    }
}
