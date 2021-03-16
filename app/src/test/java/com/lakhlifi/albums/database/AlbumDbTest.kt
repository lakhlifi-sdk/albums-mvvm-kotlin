package com.lakhlifi.albums.database

import android.content.Context
import android.os.Build
import android.util.Log
import android.util.Log.d
import androidx.room.Delete
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.lakhlifi.albums.dao.AlbumDao
import com.lakhlifi.albums.network.model.Album
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.checkerframework.checker.nullness.qual.Nullable
import org.junit.After
import org.junit.Assert
import java.io.IOException


@RunWith(RobolectricTestRunner::class)
//@Config(sdk = [Build.VERSION_CODES.P])
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class AlbumDbTest {
    private lateinit var db: AlbumDb
    private lateinit var albumDao: AlbumDao

    //Function marked with this annotation executes before each test.
    @Before
    fun create_Album_Db() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,AlbumDb::class.java
        ).allowMainThreadQueries().build()
        albumDao = db.albumDao()
    }
    //Function marked with this annotation executes after each test.
    @After
    @Throws(IOException::class)
    fun close_album_db() {
        db.close()
    }

    // to test insertion on database
    @Test
    fun insert_To_Album_Table_Test() = runBlocking {
        val  album=Album(1,"hello",3)
        val res= albumDao.insertAlbum(album)
        Assert.assertEquals(res,1)
    }

    // for delete function
    @Test
    fun delete_Album_From_Album_Bb_Test()= runBlocking{
        val album = Album(1, "album 1", 1)
        val id=albumDao.insertAlbum(album)
        assertThat(id).isAtMost(1)
        val res=albumDao.deleteAlbum(album)
        Assert.assertEquals(res,1)
    }

    //to test update function
    @Test
     fun test_Update_Album_Test() = runBlocking {
        val album = Album(1, "album 1", 1)
        albumDao.insertAlbum(album)
        val res=albumDao.updateAlbum(1,"this an album",2)
        Assert.assertEquals(res,1)
    }

    // to test searchAlbum function from db
    @Test
    fun serch_Album_By_Id_Test() = runBlocking {
        val album = Album(1, "album 1", 10)
        albumDao.insertAlbum(album)
        val res=albumDao.find(1)
        Assert.assertEquals(res,1)
    }

    // to test read function from db
    @Test
    fun read_From_Album_Table_Test()= runBlocking{
        val album = Album(1, "album 1", 1)
        albumDao.insertAlbum(album)
        val res=albumDao.read()
        assertThat(res).isAtMost(1)
    }

}