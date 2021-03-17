package com.lakhlifi.albums.view

import androidx.test.espresso.intent.rule.IntentsTestRule
import org.junit.Rule

class IntentTest {
    @get:Rule
    val intentsTestRule = IntentsTestRule(AlbumActivity::class.java)



    /*assertThat(intent).hasAction(Intent.ACTION_VIEW)
assertThat(intent).categories().containsExactly(Intent.CATEGORY_BROWSABLE)
assertThat(intent).hasData(Uri.parse("www.google.com"))
assertThat(intent).extras().containsKey("key1")
assertThat(intent).extras().string("key1").isEqualTo("value1")
assertThat(intent).extras().containsKey("key2")
assertThat(intent).extras().string("key2").isEqualTo("value2")*/
}