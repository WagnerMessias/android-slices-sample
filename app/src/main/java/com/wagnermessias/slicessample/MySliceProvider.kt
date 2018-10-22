package com.wagnermessias.slicessample

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.ListBuilder.INFINITY
import androidx.slice.builders.SliceAction


class MySliceProvider : SliceProvider() {

    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    /**
     * Construct the Slice and bind data if available.
     */
    override fun onBindSlice(sliceUri: Uri): Slice {
        return when (sliceUri.path) {
            "/basic" -> createBasicSlice(sliceUri)
            "/interactive" -> createInteractiveSlice(sliceUri)
            else -> uriNotRecognized(sliceUri)
        }
    }

    private fun createBasicSlice(sliceUri: Uri): Slice {
        return ListBuilder(context, sliceUri, INFINITY)
                .setHeader {
                    it.title = "Slice Básico"
                    it.subtitle = "Hello World"
                }.build()
    }

    private fun uriNotRecognized(sliceUri: Uri): Slice {
        return ListBuilder(context, sliceUri, INFINITY)
                .setHeader {
                    it.title = "Erro URI não identificada"
                }.build()
    }

    private fun createInteractiveSlice(sliceUri: Uri): Slice {

        val list = ListBuilder(context, sliceUri, INFINITY)

        val actionOpenApp = createActionOpenApp()
        val actionOpenHome = createActionOpenHome()
        val actionOpenHello = createActionOpenHelloWorld()

        val row = ListBuilder.RowBuilder()
                .setTitle("Toque para abrir Activity")
                .setPrimaryAction(actionOpenApp)
                .addEndItem(actionOpenHome)
                .addEndItem(actionOpenHello)

        return list.setHeader {
            it.title = "Slice Interativo"
        }
                .addRow(row)
                .build()

    }

    private fun createActionOpenApp(): SliceAction {

        val intent = Intent(context, HelloWorldActivity::class.java)
        return SliceAction(PendingIntent.getActivity(context, 0, intent, 0),
                IconCompat.createWithResource(context!!, R.drawable.abc_ic_star_black_36dp),
                "Toque para abrir o APP")
    }

    private fun createActionOpenHome(): SliceAction {

        val intent = Intent(context, MainActivity::class.java)
        return SliceAction(PendingIntent.getActivity(context, 0, intent, 0),
                IconCompat.createWithResource(context!!, R.drawable.ic_house),
                "Home")
    }

    private fun createActionOpenHelloWorld(): SliceAction {

        val intent = Intent(context, HelloWorldActivity::class.java)
        return SliceAction(PendingIntent.getActivity(context, 0, intent, 0),
                IconCompat.createWithResource(context!!, R.drawable.ic_globe),
                "World")
    }
}