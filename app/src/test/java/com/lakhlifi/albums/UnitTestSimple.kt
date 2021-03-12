package com.lakhlifi.albums

import android.content.Context
import com.google.common.base.CharMatcher.`is`
import com.google.common.truth.ExpectFailure.assertThat
import com.lakhlifi.albums.dao.AlbumDao
import com.lakhlifi.albums.database.AlbumDb
import com.lakhlifi.albums.repository.AlbumRepository
import com.lakhlifi.albums.viewModel.AlbumViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UnitTestSimple {

    private  lateinit var mockContext : Context

    @Test
    fun readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        `when`(mockContext.getString(R.string.app_name))
            .thenReturn("Albums")


        // ...when the string is returned from the object under test...
        //val result: String = myObjectUnderTest.getHelloWorldString()

        // ...then the result should be the expected one.
        //assertThat(result, `is`("FAKE_STRING"))
    }

}