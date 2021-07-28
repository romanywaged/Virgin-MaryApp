package com.example.vergionmaryapp.base.baseView

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.snackbar.Snackbar


@Suppress("DEPRECATION")
abstract class MyBaseFragment :  Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanseState: Bundle?): View? {
        return provideYourFragmentView(inflater, container, savedInstanseState)
    }

    abstract fun provideYourFragmentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View


    fun addNewFragment(fragment: Fragment, bundle: Bundle) {
//        val transaction = requireActivity().supportFragmentManager.beginTransaction()
//        fragment.arguments = bundle
//
//        transaction.replace(R.id.main_content, fragment, fragment.javaClass.simpleName)
//                .addToBackStack(null)
//                .commit()
    }

    fun removeFragment(fragment: Fragment) {
        requireFragmentManager().beginTransaction().remove(fragment).commit()
        this.fragmentManager?.popBackStack()
    }

    fun checkNetworkConnection(): Boolean {
        val connectivityManager = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun showSnackBarFromResource(root: View, resourceMsg: Int, type: Int) {
        Snackbar.make(root, resources.getString(resourceMsg), type).show()
    }

    fun showSnackBarFromString(root: View, stringMsg: String) {
        Snackbar.make(root, stringMsg, Snackbar.LENGTH_LONG).show()
    }
}