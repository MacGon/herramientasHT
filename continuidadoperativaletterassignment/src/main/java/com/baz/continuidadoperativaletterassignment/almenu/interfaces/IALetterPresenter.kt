package com.baz.continuidadoperativaletterassignment.almenu.interfaces

import com.google.android.material.bottomnavigation.BottomNavigationView

interface IALetterPresenter {

    interface Presenter{
        fun navListener() : BottomNavigationView.OnNavigationItemSelectedListener
        fun goAssignatureAccepted()
        fun goSuccessfulAsignment()
        fun goDashboard()
    }
}