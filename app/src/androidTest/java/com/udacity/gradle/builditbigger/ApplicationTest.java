package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> implements FetchJokeTask.Receiver {

    private CountDownLatch latch;

    public ApplicationTest() {
        super(Application.class);
        latch = new CountDownLatch(1);
    }

    public void testAsyncTask() {
        FetchJokeTask task = new FetchJokeTask();
        task.execute(this);
        try {
            latch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Timer timed out.");
        }
    }

    @Override
    public void receiveJoke(String joke) {
        assertNotNull(joke);
        assertFalse(joke.length() == 0);
        latch.countDown();
    }
}