package com.lakhlifi.albums.view
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.lakhlifi.albums.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumActivityTest{

    @get:Rule
    var activityRule: ActivityScenarioRule<AlbumActivity>
            = ActivityScenarioRule(AlbumActivity::class.java)


    @Before
     fun setUp() {
    }
    @After
     fun tearDown() {

    }

    @Test
    fun listGoesOverTheFold() {
        onView(allOf(withText("Add item"), withId(R.id.btn_add_item))).check(matches(isDisplayed()))
    }


    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        onView(ViewMatchers.withId(R.id.rv_album))
            .perform(
                // scrollTo will fail the test if no item matches.
                //Scrolls to the matched View, if it exists.
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(not(withText("not in the list")))
                )
            )
    }
/*
    @Test fun scrollToItemBelowFold_checkItsText() {
        // First, scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.rv_album))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    ITEM_BELOW_THE_FOLD,
                    click()
                )
            )

        // Match the text in an item below the fold and check that it's displayed.
        val itemElementText = "${activityRule.activity.resources
            .getString(R.string.item_element_text)} ${ITEM_BELOW_THE_FOLD.toString()}"
        onView(withText(itemElementText)).check(matches(isDisplayed()))
    }

    @Test fun itemInMiddleOfList_hasSpecialText() {
        // First, scroll to the view holder using the isInTheMiddle() matcher.
        onView(ViewMatchers.withId(R.id.rv_album))
            .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()))

        // Check that the item has the special text.
        val middleElementText = activityRule.activity.resources
            .getString("R.string.middle")
        onView(withText(middleElementText)).check(matches(isDisplayed()))
    }
*/

}





/**
 * fun testClickActionModeItem() {
// Make sure we show the contextual action bar.
onView(withId(R.id.show_contextual_action_bar))
.perform(click())

// Click on the icon.
onView((withId(R.id.action_lock)))
.perform(click())

// Verify that we have really clicked on the icon
// by checking the TextView content.
onView(withId(R.id.text_action_bar_result))
.check(matches(withText("Lock")))
}
 ------------------
fun testActionBarOverflow() {
// Make sure we hide the contextual action bar.
onView(withId(R.id.hide_contextual_action_bar))
.perform(click())

// Open the options menu OR open the overflow menu, depending on whether
// the device has a hardware or software overflow menu button.
openActionBarOverflowOrOptionsMenu(
ApplicationProvider.getApplicationContext<Context>())

// Click the item.
onView(withText("World"))
.perform(click())

// Verify that we have really clicked on the icon by checking
// the TextView content.
onView(withId(R.id.text_action_bar_result))
.check(matches(withText("World")))
}
private fun withAdaptedData(dataMatcher: Matcher<Any>): Matcher<View> {
return object : TypeSafeMatcher<View>() {

override fun describeTo(description: Description) {
description.appendText("with class name: ")
dataMatcher.describeTo(description)
}

public override fun matchesSafely(view: View) : Boolean {
if (view !is AdapterView<*>) {
return false
}

val adapter = view.adapter
for (i in 0 until adapter.count) {
if (dataMatcher.matches(adapter.getItem(i))) {
return true
}
}

return false
}
}
}

fun testDataItemNotInAdapter() {
onView(withId(R.id.rv_album))
.check(matches(not(withAdaptedData(withItemContent("item: 168")))))
}
 */
