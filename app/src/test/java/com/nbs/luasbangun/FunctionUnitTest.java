package com.nbs.luasbangun;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FunctionUnitTest {
    @Test
    public void testCalculateRectangleArea() throws Exception {
        double actual = Formula.calculateRectangleArea(3, 6);

        double expected = 18;

        assertEquals("Test calculate rectangle area", expected, actual, 18);
    }
}