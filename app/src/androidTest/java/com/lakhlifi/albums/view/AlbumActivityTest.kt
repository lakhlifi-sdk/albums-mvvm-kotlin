package com.lakhlifi.albums.view

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lakhlifi.albums.R
import com.lakhlifi.albums.adapter.AlbumAdapter
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AlbumActivity::class.java)


    @Test
    fun image_Iiew_Test() {
        onView(

            withId(R.id.empty_img)

        ).check(matches(isDisplayed()))
    }

    @Test
    fun recycle_view_event_actionOnItemAtPositionTest() {

        Intents.init()
        val position = 1

        onView(withId(R.id.swip_refresh_layout)).perform(swipeDown())

        onView(withId(R.id.rv_album))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.ViewHolder>(
                    position,
                    //click()
                    clickItemWithId(R.id.album_row)
                )
            )
        //onView(withId(R.id.id_Album)).check(matches(withText("23")))
        //onView(withId(R.id.album_title)).check(matches(isDisplayed()));
        intended(hasComponent(AlbumInfo::class.java.name))
        Intents.release()
    }

    fun clickItemWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }
            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }
            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id) as View
                v.performClick()
            }
        }
    }

    //button test
    @Test
    fun button_ui_test() {
        onView(
            allOf(
                withText("Add item"),
                withId(R.id.btn_add_item)
            )
        ).check(matches(isDisplayed()))
    }


}

