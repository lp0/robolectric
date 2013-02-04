package org.robolectric.shadows;

import android.media.MediaPlayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricConfig;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestConfigs;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class) @RobolectricConfig(TestConfigs.WithDefaults.class)
public class MediaPlayerTest {

    private MediaPlayer mediaPlayer;
    private ShadowMediaPlayer shadowMediaPlayer;

    @Before
    public void setUp() throws Exception {
        mediaPlayer = Robolectric.newInstanceOf(MediaPlayer.class);
        shadowMediaPlayer = Robolectric.shadowOf(mediaPlayer);
    }

    @Test
    public void testCurrentPosition() {
        int[] positions = {0, 1, 2, 1024};

        for (int position : positions) {
            shadowMediaPlayer.setCurrentPosition(position);
            assertThat(mediaPlayer.getCurrentPosition(), equalTo(position));
        }
    }
}
