package org.robolectric.shadows;

import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricConfig;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestConfigs;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(RobolectricTestRunner.class) @RobolectricConfig(TestConfigs.WithDefaults.class)
public class AbsoluteLayoutTest {
    @Test
    public void getLayoutParams_shouldReturnAbsoluteLayoutParams() throws Exception {
        ViewGroup.LayoutParams layoutParams = new AbsoluteLayout(null).getLayoutParams();

        assertThat(layoutParams, instanceOf(AbsoluteLayout.LayoutParams.class));
    }
}
