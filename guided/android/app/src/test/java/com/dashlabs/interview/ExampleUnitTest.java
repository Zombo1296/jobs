package com.dashlabs.interview;

import android.content.res.AssetManager;

import org.junit.Test;
import com.dashlabs.interview.models.Poster;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void posterClass_isCorrect() throws Exception {
        String testMovieTitle = "Test Movie Title";

        Poster poster = new Poster();
        poster.setMovieTitle(testMovieTitle);
        assertEquals("Test Movie Title", poster.getMovieTitle());
    }
}