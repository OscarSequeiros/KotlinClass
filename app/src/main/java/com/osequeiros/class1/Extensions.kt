package com.osequeiros.class1

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

/**
 * Created by osequeiros on 8/13/17.
 * Clase extensiones
 */

fun Context.toast(mensaje: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, mensaje, length).show()
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadUrl(url: String) {
    Picasso.with(context).load(url).into(this)
}

/*inline fun<reified T : View> View.find(idRes: Int): View {
    return findViewById(idRes) as T
}*/

fun RecyclerView.ViewHolder.toast(mensaje: String) = itemView.context.toast(mensaje)
