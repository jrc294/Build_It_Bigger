package com.jonathan.jokemodule;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        TextView txtView = (TextView) rootView.findViewById(R.id.txtJoke);
        Bundle args = getArguments();
        String joke = args.getString(JokeActivity.JOKE);
        joke = joke.equals("") ? getString(R.string.generic_error_msg) : joke;
        txtView.setText(joke);

        return rootView;
    }
}
