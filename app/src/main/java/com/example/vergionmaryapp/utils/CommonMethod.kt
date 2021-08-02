package com.example.vergionmaryapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
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

    fun getDayNameFromDate(inputDate : String) : String
    {
        val inFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ar"))
        val date: Date = inFormat.parse(inputDate)
        val outFormat = SimpleDateFormat("EEEE", Locale("ar"))
        return outFormat.format(date)
    }

    fun separateString(dateStr : String) : String
    {
        val parts = dateStr.split("T")
        return parts[0]
    }

    fun showSnackBarFromResourceInfinity(root : View, resourceMsg : Int, context: Context)
    {
        Snackbar.make(root, context.resources.getString(resourceMsg), Snackbar.LENGTH_INDEFINITE).show()
    }

    fun showSnackBarFromResource(root : View, resourceMsg : Int, context: Context)
    {
        Snackbar.make(root, context.resources.getString(resourceMsg), Snackbar.LENGTH_LONG).show()
    }

    fun showSnackBarFromString(root : View, message : String)
    {
        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show()
    }

    fun checkPatternValidation(pattern: String, field: String): Boolean {
        val ptn = Pattern.compile(pattern)
        val matcher = ptn.matcher(field)
        return matcher.matches()
    }

    fun validateNationalID(nationalId : String, birthdayStr : String) : Boolean
    {
        if(nationalId.length == 14)
        {
            val birthdayFromID = nationalId.substring(1,7)

            val subBirthday = birthdayStr.substring(2,8)
            return birthdayFromID == subBirthday
        }
        return false
    }

    fun validateGender(nationalId : String, genderId : Int) : Boolean
    {
        if(nationalId.length == 14)
        {
            val genderFromID = nationalId.substring(12,13).toInt()

            val subGenderID = if(genderFromID%2 == 0) 2 else 1

            return genderId == subGenderID
        }
        return false
    }

    fun convert24Hto12H(oldTime : String) : String
    {
        try {
            val formatStr1 = SimpleDateFormat("HH:mm")
            val formatStr2 = SimpleDateFormat("hh:mm a")
            val formatStr3 = formatStr1.parse(oldTime)
            return formatStr2.format(formatStr3)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }


    fun convertStringDate(dateStr : String) : Date
    {
        var myDate = Date()
        val myDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val formattedStr = myDateFormat.format(myDate)

        try {
            myDate = myDateFormat.parse(dateStr)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
            return myDate
    }

}