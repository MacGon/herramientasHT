package com.baz.continuidadoperativaletterassignment.almenu.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.baz.continuidadoperativaletterassignment.R
import com.baz.continuidadoperativaletterassignment.alutils.hideBottomBar
import com.baz.continuidadoperativaletterassignment.almenu.`interface`.IALetterPresenter
import com.baz.continuidadoperativaletterassignment.almenu.presenter.ALetterPresenter
import com.baz.continuidadoperativaletterassignment.almenu.`interface`.IALetterView
import com.baz.continuidadoperativaletterassignment.alasignature.view.ui.fragments.ALSignatureAcceptedFragment
import com.baz.continuidadoperativaletterassignment.alasignature.view.ui.fragments.ALContractFragment
import com.baz.continuidadoperativaletterassignment.alasignature.view.ui.fragments.ALLetterAssignmentSuccessful
import kotlinx.android.synthetic.main.activity_coletter.*

class ALetterActivity : AppCompatActivity(),
    IALetterView, ALContractFragment.showAsignatureAcceptedFragment, ALSignatureAcceptedFragment.showSuccessfulAsignmentFragment , ALLetterAssignmentSuccessful.goInitialView{

    val manager = supportFragmentManager
    private var mIALetterPresenter : IALetterPresenter.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coletter)

        mIALetterPresenter = ALetterPresenter(this)
        nav_view_la.setOnNavigationItemSelectedListener(mIALetterPresenter!!.navListener())

        if (savedInstanceState == null){
            manager.beginTransaction().replace(frameContainer.id, ALContractFragment.newInstance()).commit()
            hideBottomBar(window)
        }
    }

    override fun goToFragmentSignature() {
        val transaction = manager.beginTransaction()
        transaction.replace(frameContainer.id,
                ALSignatureAcceptedFragment.newInstance(intent?.getStringExtra("empNameJH").toString(),
                        intent?.getStringExtra("numEmpJH").toString() ))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun goToFragment(fragment: Fragment) {
        val transaction = manager.beginTransaction()
        transaction.replace(frameContainer.id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showAsignatureFragment() {
        mIALetterPresenter?.goAssignatureAccepted()
    }

    override fun showSuccessfullAsignment() {
        mIALetterPresenter?.goSuccessfulAsignment()
    }

    override fun showInitialView() {
        showDashboard()
    }

    override fun showDashboard() {
        startActivity(Intent(baseContext, ALetterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP))
        finish()
    }

    override fun backPressed() {
         super.onBackPressed()
    }

    override fun onResume() {
        hideBottomBar(window)
        super.onResume()
    }


}
