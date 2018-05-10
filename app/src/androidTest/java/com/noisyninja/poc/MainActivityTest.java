package com.noisyninja.poc;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.noisyninja.poc.view.main.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {

        sleepLong();

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerList)));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        sleepLong();
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recyclerListDetail),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView2.perform(actionOnItemAtPosition(13, click()));

        sleepMedium();
        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.recyclerListDetail),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView3.perform(actionOnItemAtPosition(8, click()));

        sleepMedium();
        ViewInteraction iconTextView = onView(
                allOf(withText("Done."),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        iconTextView.perform(click());


        sleepMedium();
        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.recyclerList),
                        childAtPosition(
                                withId(R.id.refresh_layout),
                                0)));
        recyclerView4.perform(actionOnItemAtPosition(0, click()));


        sleepMedium();
        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.recyclerListDetail),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView5.perform(actionOnItemAtPosition(13, click()));

        sleepMedium();
        ViewInteraction recyclerView6 = onView(
                allOf(withId(R.id.recyclerListDetail),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView6.perform(actionOnItemAtPosition(5, click()));

        sleepMedium();
        ViewInteraction iconTextView2 = onView(
                allOf(withText("Done."),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        iconTextView2.perform(click());

        sleepMedium();
        ViewInteraction recyclerView7 = onView(
                allOf(withId(R.id.recyclerList),
                        childAtPosition(
                                withId(R.id.refresh_layout),
                                0)));
        recyclerView7.perform(actionOnItemAtPosition(5, click()));

        sleepMedium();
        ViewInteraction recyclerView8 = onView(
                allOf(withId(R.id.recyclerListDetail),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView8.perform(actionOnItemAtPosition(22, click()));

        sleepMedium();
        ViewInteraction recyclerView9 = onView(
                allOf(withId(R.id.recyclerListDetail),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView9.perform(actionOnItemAtPosition(16, click()));

        sleepMedium();
        ViewInteraction iconTextView3 = onView(
                allOf(withText("Done."),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        iconTextView3.perform(click());

        sleepMedium();
        ViewInteraction recyclerView10 = onView(
                allOf(withId(R.id.recyclerList),
                        childAtPosition(
                                withId(R.id.refresh_layout),
                                0)));
        recyclerView10.perform(actionOnItemAtPosition(4, click()));

        sleepMedium();
        ViewInteraction recyclerView11 = onView(
                allOf(withId(R.id.recyclerListDetail),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView11.perform(actionOnItemAtPosition(5, click()));

        sleepMedium();
        ViewInteraction recyclerView12 = onView(
                allOf(withId(R.id.recyclerListDetail),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView12.perform(actionOnItemAtPosition(15, click()));

        sleepMedium();
        ViewInteraction recyclerView13 = onView(
                allOf(withId(R.id.recyclerListDetail),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView13.perform(actionOnItemAtPosition(9, click()));

        sleepMedium();
        ViewInteraction iconTextView4 = onView(
                allOf(withText("Done."),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        iconTextView4.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
