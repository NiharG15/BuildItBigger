package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> implements FetchJokeTask.Receiver {

    private CountDownLatch latch;
    private String joke;

    public ApplicationTest() {
        super(Application.class);
        latch = new CountDownLatch(1);
    }

    public void testAsyncTask() {
        FetchJokeTask task = new FetchJokeTask();
        task.execute(this);
        try {
            latch.await(30, TimeUnit.SECONDS);
            Log.d("Unit Test", String.valueOf(latch.getCount()));
            assertNotNull(joke);
            assertFalse(joke.length() == 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Timer timed out.");
        }
    }

    @Override
    public void receiveJoke(String joke) {
        this.joke = joke;
        latch.countDown();
    }
}