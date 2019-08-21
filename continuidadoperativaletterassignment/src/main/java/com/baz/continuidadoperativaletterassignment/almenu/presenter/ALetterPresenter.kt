package com.baz.continuidadoperativaletterassignment.almenu.presenter

import androidx.fragment.app.Fragment
import com.baz.continuidadoperativaletterassignment.R
import com.baz.continuidadoperativaletterassignment.almenu.`interface`.IALetterPresenter
import com.baz.continuidadoperativaletterassignment.almenu.`interface`.IALetterView
import com.baz.continuidadoperativaletterassignment.alasignature.view.ui.fragments.ALSignatureAcceptedFragment
import com.baz.continuidadoperativaletterassignment.alasignature.view.ui.fragments.ALLetterAsignmentSuccessful
import com.google.android.material.bottomnavigation.BottomNavigationView


class ALetterPresenter(private val iaLetterView: IALetterView): IALetterPresenter.Presenter{

    override fun navListener(): BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        menuItem ->

        when(menuItem.itemId){
            R.id.navigation_home -> {
                goDashboard()
            }
            R.id.navigation_back -> {
                iaLetterView.backPressed()
            }
        }
        false
    }

    override fun goAssignatureAccepted() {
        val selectedFragment: Fragment
        selectedFragment = ALSignatureAcceptedFragment()
        iaLetterView.goToFragment(selectedFragment)
    }

    override fun goSuccessfulAsignment() {
        val selectedFragment: Fragment
        selectedFragment = ALLetterAsignmentSuccessful()
        iaLetterView.goToFragment(selectedFragment)
    }

    override fun goDashboard() {
        iaLetterView.showDashboard()
    }


}