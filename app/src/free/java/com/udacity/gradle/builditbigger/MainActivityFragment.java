package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.jonathan.jokemodule.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements CallbackComplete{

    final static String JOKE= "joke";
    Button btnGetJoke;
    private ProgressBar spinner;

    private InterstitialAd mInterstitial;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        spinner = (ProgressBar) root.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        btnGetJoke = (Button) root.findViewById(R.id.btnTellJoke);

        btnGetJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                showInterstitial();
            }
        });
        return root;
    }

    public void showInterstitial(){
        mInterstitial = new InterstitialAd(getActivity());
        mInterstitial.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mInterstitial.isLoaded()) {
                    mInterstitial.show();
                }
            }

            @Override
            public void onAdClosed() {
                tellJoke();
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                tellJoke();
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitial.loadAd(adRequest);

    }

    @Override
    public void onCallbackComplete(String result) {
        Intent i = new Intent(getActivity(), JokeActivity.class);
        i.putExtra(JOKE, result);
        startActivity(i);
        spinner.setVisibility(View.GONE);
    }

    public void tellJoke(){
        new EndPointsAsyncTask(this).execute();
    }
}
