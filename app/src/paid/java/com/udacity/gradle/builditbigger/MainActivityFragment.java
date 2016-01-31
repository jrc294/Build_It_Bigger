package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.jonathan.jokemodule.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements CallbackComplete {

    final static String JOKE= "joke";
    Button btnGetJoke;
    private ProgressBar spinner;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        btnGetJoke = (Button) root.findViewById(R.id.btnGetJoke);
        spinner = (ProgressBar) root.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        btnGetJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJoke();
            }
        });

        return root;
    }

    private void getJoke(){
        spinner.setVisibility(View.VISIBLE);
        new EndPointsAsyncTask(this).execute();
    }


    @Override
    public void onCallbackComplete(String result) {
        Intent i = new Intent(getActivity(), JokeActivity.class);
        i.putExtra(JOKE, result);
        startActivity(i);
        spinner.setVisibility(View.GONE);
    }
}
