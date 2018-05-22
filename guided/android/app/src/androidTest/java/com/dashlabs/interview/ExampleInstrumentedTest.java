package com.dashlabs.interview;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.dashlabs.interview.activities.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private MainActivity mTestActivity;
    private TextView mTestEmptyText;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.dashlabs.interview", appContext.getPackageName());
    }

    public void testEmptyView_labelText() {
        String expected = mTestActivity.getString(R.id.movieTitle);
        String actual = mTestEmptyText.getText().toString();
        assertEquals("mTestEmptyText contains wrong text",
                expected, actual);
    }
}
