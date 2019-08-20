package com.baz.continuidadoperativaletterassignment.alasignature.view.ui.fragments

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.baz.continuidadoperativaletterassignment.R
import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.IAsignatureContractPresenter
import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.IAsignatureContractView
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.RequestAssignationToolLA
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.ResponseAssignationToolLA
import com.baz.continuidadoperativaletterassignment.alasignature.presenter.ALAsignaturePresenter
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.ALetterActivity
import com.jakewharton.rxbinding3.material.dismisses
import kotlinx.android.synthetic.main.fragment_asignature_accepted.*
import kotlinx.android.synthetic.main.progress_dialog_la.*

class ALAsignatureAcceptedFragment : Fragment(), IAsignatureContractView {


    private lateinit var  presenter: IAsignatureContractPresenter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ALAsignaturePresenter(this)
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
            presenter.loadData()
            //showSuccessfulAsignmentFragment.showSuccessfullAsignment()
        }

        btnDeletePaint.setOnClickListener {
            btnDeletePaint.isEnabled = false
            btnSuccefullAsignature.isEnabled = false
            actionButtonDraw(true)

            firmaView.clear()
        }

        firmaView.setOnTouchListener { view : View, motionEvent: MotionEvent ->

            val x: Float = motionEvent!!.x
            val y: Float = motionEvent.y

            actionButtonDraw(true)
            when(motionEvent!!.action){
                MotionEvent.ACTION_DOWN -> {firmaView.touchStar(x, y); view.invalidate()}
                MotionEvent.ACTION_MOVE -> {firmaView.touchMove(x, y); view.invalidate()}
                MotionEvent.ACTION_UP -> {firmaView.touchUp(); view.invalidate()}
            }

            true
        }
    }

    fun actionButtonDraw(enabled: Boolean){
        btnDeletePaint.isEnabled = enabled
        btnSuccefullAsignature.isEnabled = enabled
    }


    override fun showErrorMessage(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()

    }

    override fun showProgress(show: Boolean) {
        if (show){
            progressDialogShow(requireContext())
        }else{
            progressDialog.dismiss()
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

    fun progressDialogShow(context: Context) {
        progressDialog = ProgressDialog(context)
        progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.show()
        progressDialog.setContentView(R.layout.progress_dialog_la)
        progressDialog.setCancelable(false)
    }



}
