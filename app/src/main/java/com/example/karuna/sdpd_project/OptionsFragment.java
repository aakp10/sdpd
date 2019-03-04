package com.example.karuna.sdpd_project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsFragment extends Fragment {

    private static final String TAG = "OptionsFragment";
    public OptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_options, container, false);
        Button calendarButton = (Button) view.findViewById(R.id.calendarButton);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "calendar clicked");
                Intent calendarIntent = new Intent(getActivity(), Calendar.class);
                startActivity(calendarIntent);
            }
        });
        Button settingsButton = (Button) view.findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "settings button clicked");
                Intent settingsIntent = new Intent(getActivity(), Settings.class);
                startActivity(settingsIntent);
            }
        });
        Button homeButton = (Button) view.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "home button clicked");
                Intent homeIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(homeIntent);
            }
        });
        return view;
    }

}
