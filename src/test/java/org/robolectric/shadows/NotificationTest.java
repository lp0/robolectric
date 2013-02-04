package org.robolectric.shadows;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricConfig;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestConfigs;

import static org.junit.Assert.assertSame;

@RunWith(RobolectricTestRunner.class) @RobolectricConfig(TestConfigs.WithDefaults.class)
public class NotificationTest {
    @Test
    public void setLatestEventInfo__shouldCaptureContentIntent() throws Exception {
        PendingIntent pendingIntent = PendingIntent.getActivity(new Activity(), 0, new Intent(), 0);
        Notification notification = new Notification();
        notification.setLatestEventInfo(new Activity(), "title", "content", pendingIntent);
        assertSame(pendingIntent, notification.contentIntent);
    }
}
