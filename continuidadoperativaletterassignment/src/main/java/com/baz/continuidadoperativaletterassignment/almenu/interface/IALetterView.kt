package com.baz.continuidadoperativaletterassignment.almenu.`interface`

import androidx.fragment.app.Fragment

interface IALetterView {
    fun goToFragment(selectedFragment: Fragment)
    fun showDashboard()
    fun backPressed()
}