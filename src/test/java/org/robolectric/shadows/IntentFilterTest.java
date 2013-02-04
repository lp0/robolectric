package org.robolectric.shadows;

import android.content.IntentFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricConfig;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestConfigs;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class) @RobolectricConfig(TestConfigs.WithDefaults.class)
public class IntentFilterTest {
    @Test
    public void addDataScheme_shouldAddTheDataScheme() throws Exception {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addDataScheme("http");
        intentFilter.addDataScheme("ftp");

        assertThat(intentFilter.getDataScheme(0), equalTo("http"));
        assertThat(intentFilter.getDataScheme(1), equalTo("ftp"));
    }
    
    @Test
    public void addDataAuthority_shouldAddTheDataAuthority() throws Exception {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addDataAuthority("test.com", "8080");
        intentFilter.addDataAuthority("example.com", "42");

        assertThat(intentFilter.getDataAuthority(0).getHost(), equalTo("test.com"));
        assertThat(intentFilter.getDataAuthority(0).getPort(), equalTo(8080));
        assertThat(intentFilter.getDataAuthority(1).getHost(), equalTo("example.com"));
        assertThat(intentFilter.getDataAuthority(1).getPort(), equalTo(42));
    }
}
