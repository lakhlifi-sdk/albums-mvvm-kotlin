package com.lakhlifi.albums.view


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.lakhlifi.albums.R
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lakhlifi.albums.adapter.AlbumAdapter
import org.junit.After
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class IntentTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(AlbumActivity::class.java)


    @Before
    fun setUp() {

    }
    @After
    fun tearDown() {

    }


}