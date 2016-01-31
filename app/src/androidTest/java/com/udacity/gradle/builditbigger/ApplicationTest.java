package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase {

    public void testVerifyNonNullResponse() {
        String joke = "";
        EndPointsAsyncTask task = new EndPointsAsyncTask(new CallbackComplete() {
            @Override
            public void onCallbackComplete(String result) {
                // Do nothing
            }
        });
        task.execute("");
        try {
            joke = task.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        assertFalse(joke.equals(""));
    }

}