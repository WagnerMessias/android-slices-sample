package com.wagnermessias.slicessample

import android.content.pm.ProviderInfo
import android.net.Uri
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MySliceProviderTest {

    private lateinit var provider: MySliceProvider

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        provider = MySliceProvider()
        provider.attachInfo(context, ProviderInfo())
    }

    @Test
    fun withSliceBasic_shouldGetBasicUri() {
        val uri = Uri.parse("content://com.wagnermessias.slicessample/basic")
        val slice = provider.onBindSlice(uri)
        assert(slice.uri == uri)
    }

    @Test
    fun withSliceBasic_shouldGetInteractiveUri() {
        val uri = Uri.parse("content://com.wagnermessias.slicessample/interactive")
        val slice = provider.onBindSlice(uri)
        assert(slice.uri == uri)
    }

}