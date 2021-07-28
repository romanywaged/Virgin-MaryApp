package com.example.vergionmaryapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.util.regex.Pattern

@Suppress("DEPRECATION")
class CommonMethod
{
    fun <T> convertListToString(list : List<T>) : String
    {
        val jsonStr = Gson()
        return jsonStr.toJson(list)
    }

    fun checkNetworkConnection(context: Context): Boolean
    {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun showSnackBarFromResourceInfinity(root : View, resourceMsg : Int, context: Context)
    {
        Snackbar.make(root, context.resources.getString(resourceMsg), Snackbar.LENGTH_INDEFINITE).show()
    }

    fun showSnackBarFromResource(root : View, resourceMsg : Int, context: Context)
    {
        Snackbar.make(root, context.resources.getString(resourceMsg), Snackbar.LENGTH_LONG).show()
    }

    fun checkPatternValidation(pattern: String, field: String): Boolean {
        val ptn = Pattern.compile(pattern)
        val matcher = ptn.matcher(field)
        return matcher.matches()
    }


}