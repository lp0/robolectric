package org.robolectric.shadows;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.TestIntentSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricConfig;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestConfigs;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class) @RobolectricConfig(TestConfigs.WithDefaults.class)
public class PendingIntentTest {
    @Test
    public void shouldGetIntentSender() {
        Intent expectedIntent = new Intent();
        PendingIntent service = PendingIntent.getService(null, 0, expectedIntent, 0);

        IntentSender intentSender = service.getIntentSender();
        assertThat(expectedIntent, equalTo(((TestIntentSender) intentSender).intent));
    }

    @Test
    public void getBroadcast__shouldCreateIntentForBroadcast() throws Exception {
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Robolectric.application, 99, intent, 100);
        ShadowPendingIntent shadow = shadowOf(pendingIntent);
        assertThat(shadow.isActivityIntent(), is(false));
        assertThat(shadow.isBroadcastIntent(), is(true));
        assertThat(shadow.isServiceIntent(), is(false));
        assertThat(intent, equalTo(shadow.getSavedIntent()));
        assertThat(Robolectric.application, equalTo(shadow.getSavedContext()));
    }

    @Test
    public void getActivity__shouldCreateIntentForBroadcast() throws Exception {
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(Robolectric.application, 99, intent, 100);
        ShadowPendingIntent shadow = shadowOf(pendingIntent);
        assertThat(shadow.isActivityIntent(), is(true));
        assertThat(shadow.isBroadcastIntent(), is(false));
        assertThat(shadow.isServiceIntent(), is(false));
        assertThat(intent, equalTo(shadow.getSavedIntent()));
        assertThat(Robolectric.application, equalTo(shadow.getSavedContext()));
    }
    
    @Test
    public void getService__shouldCreateIntentForBroadcast() throws Exception {
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getService(Robolectric.application, 99, intent, 100);
        ShadowPendingIntent shadow = shadowOf(pendingIntent);
        assertThat(shadow.isActivityIntent(), is(false));
        assertThat(shadow.isBroadcastIntent(), is(false));
        assertThat(shadow.isServiceIntent(), is(true));
        assertThat(intent, equalTo(shadow.getSavedIntent()));
        assertThat(Robolectric.application, equalTo(shadow.getSavedContext()));
    }
    
    @Test
    public void send__shouldFillInIntentData() throws Exception {
        Intent intent = new Intent();
        Activity context = new Activity();
        PendingIntent forActivity = PendingIntent.getActivity(context, 99, intent, 100);
        
        Activity otherContext = new Activity();
        Intent fillIntent = new Intent();
        fillIntent.putExtra("TEST", 23);
        forActivity.send(otherContext, 0, fillIntent);
        
        Intent i = shadowOf(otherContext).getNextStartedActivity();
        assertThat(i, notNullValue());
        assertThat(i, sameInstance(intent));
        assertThat(i.getIntExtra("TEST", -1), equalTo(23));
    }
}
