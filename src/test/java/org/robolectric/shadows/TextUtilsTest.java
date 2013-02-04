package org.robolectric.shadows;

import android.text.TextPaint;
import android.text.TextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricConfig;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestConfigs;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class) @RobolectricConfig(TestConfigs.WithDefaults.class)
public class TextUtilsTest {
    @Test
    public void testExpandTemplate() throws Exception {
        assertThat(
                (String) TextUtils.expandTemplate("a^1b^2c^3d", "A", "B", "C", "D"),
                equalTo("aAbBcCd"));
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertThat(TextUtils.isEmpty(null), equalTo(true));
        assertThat(TextUtils.isEmpty(""), equalTo(true));
        assertThat(TextUtils.isEmpty(" "), equalTo(false));
        assertThat(TextUtils.isEmpty("123"), equalTo(false));
    }

    @Test public void testJoin() {
      assertThat(TextUtils.join(",", new String[] { "1" }), equalTo("1"));
      assertThat(TextUtils.join(",", new String[] { "1", "2", "3" }), equalTo("1,2,3"));
      assertThat(TextUtils.join(",", Arrays.asList("1", "2", "3")), equalTo("1,2,3"));
    }

    @Test
    public void testIsDigitsOnly() throws Exception {
        assertThat(TextUtils.isDigitsOnly("123456"), equalTo(true));
        assertThat(TextUtils.isDigitsOnly("124a56"), equalTo(false));
    }
    
    @Test
    public void testSplit() {
    	//empty
    	assertThat(TextUtils.split("", ",").length, equalTo(0));
    	
    	//one value
    	assertArrayEquals(TextUtils.split("abc", ","), new String[]{"abc"});
    	
    	//two values
    	assertArrayEquals(TextUtils.split("abc,def", ","), new String[]{"abc", "def"});
    	
    	//two values with space
    	assertArrayEquals(TextUtils.split("abc, def", ","), new String[]{"abc", " def"});
   }

    @Test
    public void testEquals() {
        assertThat(TextUtils.equals(null, null), equalTo(true));
        assertThat(TextUtils.equals("", ""), equalTo(true));
        assertThat(TextUtils.equals("a", "a"), equalTo(true));
        assertThat(TextUtils.equals("ab", "ab"), equalTo(true));

        assertThat(TextUtils.equals(null, ""), equalTo(false));
        assertThat(TextUtils.equals("", null), equalTo(false));

        assertThat(TextUtils.equals(null, "a"), equalTo(false));
        assertThat(TextUtils.equals("a", null), equalTo(false));

        assertThat(TextUtils.equals(null, "ab"), equalTo(false));
        assertThat(TextUtils.equals("ab", null), equalTo(false));

        assertThat(TextUtils.equals("", "a"), equalTo(false));
        assertThat(TextUtils.equals("a", ""), equalTo(false));

        assertThat(TextUtils.equals("", "ab"), equalTo(false));
        assertThat(TextUtils.equals("ab", ""), equalTo(false));

        assertThat(TextUtils.equals("a", "ab"), equalTo(false));
        assertThat(TextUtils.equals("ab", "a"), equalTo(false));
    }

    @Test public void testEllipsize() {
        TextPaint p = new TextPaint();
        assertThat(TextUtils.ellipsize("apples", p, 100, TextUtils.TruncateAt.END).toString(), equalTo("apples"));
        assertThat(TextUtils.ellipsize("", p, 100, TextUtils.TruncateAt.END).toString(), equalTo(""));
    }
}
