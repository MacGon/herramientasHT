package com.baz.continuidadoperativaletterassignment.alasignature.view.ui.fragments

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

import com.baz.continuidadoperativaletterassignment.R
import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.IAsignatureContractPresenter
import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.IAsignatureContractView
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.AssignationToolLA
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.DetailViewModel
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.ALetterActivity
import kotlinx.android.synthetic.main.fragment_asignature_accepted.*

class ALAsignatureAcceptedFragment : Fragment(), IAsignatureContractView {
    override fun loadDataSuccess(list: List<AssignationToolLA>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }


    private lateinit var  presenter: IAsignatureContractPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_asignature_accepted, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val title: String = getString(R.string.title_asignature_accepted)
        val descriptionAsignature: String = getString(R.string.description_asignature_accepted)
        tvTitleLetterAsignature.setText(Html.fromHtml(title, 1))
        tvDescriptionAsignatureAccepted.setText(Html.fromHtml(descriptionAsignature, 2))
        super.onViewCreated(view, savedInstanceState)

        val showSuccessfulAsignmentFragment: showSuccessfulAsignmentFragment = activity as ALetterActivity

        btnSuccefullAsignature.setOnClickListener {
            showSuccessfulAsignmentFragment.showSuccessfullAsignment()
        }

        btnDeletePaint.setOnClickListener {
            btnDeletePaint.isEnabled = false
            btnSuccefullAsignature.isEnabled = false
            firmaView.clear()
        }

        firmaView.setOnTouchListener { view : View, motionEvent: MotionEvent ->

            val x: Float = motionEvent!!.x
            val y: Float = motionEvent.y

            btnDeletePaint.isEnabled = true
            btnSuccefullAsignature.isEnabled = true

            when(motionEvent!!.action){
                MotionEvent.ACTION_DOWN -> {firmaView.touchStar(x, y); view.invalidate()}
                MotionEvent.ACTION_MOVE -> {firmaView.touchMove(x, y); view.invalidate()}
                MotionEvent.ACTION_UP -> {firmaView.touchUp(); view.invalidate()}
            }

            true
        }
    }




    companion object {
        fun newInstance(): ALAsignatureAcceptedFragment {
            val fragment = ALAsignatureAcceptedFragment()
            val arg = Bundle()
            //arg.putBoolean(true)
            return fragment
        }
    }

    interface showSuccessfulAsignmentFragment {
        fun showSuccessfullAsignment()
    }

}
