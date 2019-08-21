package com.baz.continuidadoperativaletterassignment.alasignature.view.ui.fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.baz.continuidadoperativaletterassignment.R
import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.ISignatureContractPresenter
import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.IAsignatureContractView
import com.baz.continuidadoperativaletterassignment.alasignature.presenter.ALSignaturePresenter
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.ALetterActivity
import com.baz.continuidadoperativaletterassignment.alutils.hideBottomBar
import kotlinx.android.synthetic.main.fragment_asignature_accepted.*

class ALSignatureAcceptedFragment : Fragment(), IAsignatureContractView {

    private lateinit var presenter: ISignatureContractPresenter
    private lateinit var progressDialog: Dialog
    private lateinit var employeeName: String

    companion object {
        fun newInstance(empNameJH :String)  = ALSignatureAcceptedFragment().apply{
            employeeName = empNameJH
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ALSignaturePresenter(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_asignature_accepted, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headerDescriptionSignature()

        btnSuccefullAsignature.setOnClickListener { presenter.sendDataAssignment() }

        btnDeletePaint.setOnClickListener { presenter.goActionButtonDeletePaint() }

        signatureView.setOnTouchListener { view : View, motionEvent: MotionEvent -> presenter.goDrawSignatureView(view, motionEvent)
            true
        }
    }

    override fun showErrorMessage(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progressDialogShow(requireContext())
    }

    override fun hideProgress() {
        progressDialog.dismiss()
    }

    override fun showSuccessFullAsignment(){
        val showSuccessfullSignmentFragment: showSuccessfulAsignmentFragment = activity as ALetterActivity
        showSuccessfullSignmentFragment.showSuccessfullAsignment()
    }

    override fun cleanSignature() {
        signatureView.clear()
    }

    override fun hideBottomBar() {
        hideBottomBar(activity!!.window)
    }

    override fun actionButtonDraw(enabled: Boolean){
        btnDeletePaint.isEnabled = enabled
        btnSuccefullAsignature.isEnabled = enabled
    }

   override fun drawSignatureView(view: View, motionEvent: MotionEvent){
        val x: Float = motionEvent!!.x
        val y: Float = motionEvent.y

        when(motionEvent!!.action){
            MotionEvent.ACTION_DOWN -> {signatureView.touchStar(x, y); view.invalidate()}
            MotionEvent.ACTION_MOVE -> {signatureView.touchMove(x, y); view.invalidate()}
            MotionEvent.ACTION_UP -> {signatureView.touchUp(); view.invalidate()}
        }
    }

    interface showSuccessfulAsignmentFragment {
        fun showSuccessfullAsignment()
    }

    private fun headerDescriptionSignature(){
        val employeeNameJH:String = String.format(resources.getString(R.string.name_signature_accepted), employeeName)

        tvTitleLetterAsignature.setText(Html.fromHtml(getString(R.string.title_signature_accepted), 1))
        tvDescriptionAsignatureAccepted.setText(Html.fromHtml(employeeNameJH + (getString(R.string.description_signature_accepted)) , 2))
    }

    private fun progressDialogShow(context: Context) {
        progressDialog = Dialog(context)
        progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.show()
        progressDialog.setContentView(R.layout.progress_dialog_la)
        progressDialog.setCancelable(false)
        progressDialog.window?.let { hideBottomBar(it) }
    }


}
