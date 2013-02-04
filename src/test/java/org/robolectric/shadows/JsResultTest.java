package org.robolectric.shadows;

import android.webkit.JsResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricConfig;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestConfigs;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class) @RobolectricConfig(TestConfigs.WithDefaults.class)
public class JsResultTest {

    @Test
    public void shouldRecordCanceled() throws Exception {
        JsResult jsResult = Robolectric.newInstanceOf(JsResult.class);

        assertFalse(shadowOf(jsResult).wasCancelled());

        jsResult.cancel();
        assertTrue(shadowOf(jsResult).wasCancelled());

    }

}
