package com.lakhlifi.albums.ui


import androidx.test.espresso.intent.rule.IntentsTestRule
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
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