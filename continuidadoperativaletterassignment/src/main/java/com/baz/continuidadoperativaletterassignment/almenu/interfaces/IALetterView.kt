package com.baz.continuidadoperativaletterassignment.almenu.interfaces

import androidx.fragment.app.Fragment

interface IALetterView {
    fun goToFragment(selectedFragment: Fragment)
    fun showDashboard()
    fun backPressed()
}