package com.doordash.kitty.doordash;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class ListTest {

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void checkListSize() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        Espresso.onView(ViewMatchers.withId(R.id.restaurant_list_rv)).check(new RecyclerViewItemCountAssertion(48));
    }

}
