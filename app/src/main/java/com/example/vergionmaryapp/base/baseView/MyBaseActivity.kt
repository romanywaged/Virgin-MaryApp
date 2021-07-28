package com.example.vergionmaryapp.base.baseView

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class MyBaseActivity : AppCompatActivity()
{
    private var isAttached : Boolean = false

    override fun setContentView(layoutResID: Int) {
//        super.setContentView(R.layout.my_app_tool_bar)
//        layoutInflater.inflate(layoutResID, findViewById<View>(R.id.mainContent) as RelativeLayout)

    }

    override fun onStart() {
        super.onStart()
        isAttached = true
    }

    fun showSnackBarFromResource(root : View, resourceMsg : Int, type : Int)
    {
        Snackbar.make(root, resources.getString(resourceMsg), type).show()
    }

    fun showSnackBarFromString(root : View, stringMsg : String)
    {
        Snackbar.make(root, stringMsg, Snackbar.LENGTH_LONG).show()
    }

    fun checkNetworkConnection(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun onDestroy() {
        super.onDestroy()
        isAttached = false
    }
}
