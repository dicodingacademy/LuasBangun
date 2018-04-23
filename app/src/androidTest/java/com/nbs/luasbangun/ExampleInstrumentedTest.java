package com.nbs.luasbangun;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private String expectedResult;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initExpectedValue(){
        expectedResult = "18 m2";
    }

    @Test
    public void testWhenAllFieldsAreEmpty(){
        onView(withId(R.id.edt_width))
                .perform(typeText(""),
                        closeSoftKeyboard());

        onView(withId(R.id.edt_height))
                .perform(typeText(""),
                        closeSoftKeyboard());

        onView(withId(R.id.btn_calculate))
                .perform(click());

        onView(withText(R.string.error_message_fields_empty))
                .inRoot(withDecorView(not(is(activityTestRule
                        .getActivity()
                        .getWindow().getDecorView()))));
    }

    @Test
    public void testWhenAllCorrectInput(){
        onView(withId(R.id.edt_width))
                .perform(typeText("6"), closeSoftKeyboard());

        onView(withId(R.id.edt_height))
                .perform(typeText("3"), closeSoftKeyboard());

        onView(withId(R.id.btn_calculate))
                .perform(click());

        onView(withId(R.id.tv_result))
                .check(matches(withText(expectedResult)));
    }

    @Test
    public void testWhenDeviceRotateLandscape(){
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}
