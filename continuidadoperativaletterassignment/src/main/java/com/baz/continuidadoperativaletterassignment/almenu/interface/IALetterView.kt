package com.baz.continuidadoperativaletterassignment.almenu.`interface`

import androidx.fragment.app.Fragment


interface IALetterView {
    fun goToFragmentSignature()
    fun goToFragment(fragment: Fragment)
    fun showDashboard()
    fun backPressed()
}