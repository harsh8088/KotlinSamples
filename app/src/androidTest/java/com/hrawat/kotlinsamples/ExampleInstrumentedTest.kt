package com.hrawat.kotlinsamples

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testButtonWithListScroll() {

        onView(ViewMatchers.withId(R.id.btn_fetch)).perform(click())
        Thread.sleep(4000)
        onView(ViewMatchers.withId(R.id.recycler_view)).
                perform(RecyclerViewActions.
                        actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))

        Thread.sleep(2000)

    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.hrawat.kotlinsamples", appContext.packageName)
    }
}
