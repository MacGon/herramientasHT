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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ALSignaturePresenter(this)
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

        btnSuccefullAsignature.setOnClickListener { presenter.loadData() }

        btnDeletePaint.setOnClickListener {
            actionButtonDraw(false)
            cleanSignature()
        }

        firmaView.setOnTouchListener { view : View, motionEvent: MotionEvent ->  actionButtonDraw(true)

            val x: Float = motionEvent!!.x
            val y: Float = motionEvent.y

            when(motionEvent!!.action){
                MotionEvent.ACTION_DOWN -> {firmaView.touchStar(x, y); view.invalidate()}
                MotionEvent.ACTION_MOVE -> {firmaView.touchMove(x, y); view.invalidate()}
                MotionEvent.ACTION_UP -> {firmaView.touchUp(); view.invalidate()}
            }

            true
        }
    }

    override fun showErrorMessage(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        hideBottomBar()
        cleanSignature()
        actionButtonDraw(false)
    }

    override fun showProgress(show: Boolean) {
        if (show){
            progressDialogShow(requireContext())
        }else{
            progressDialog.dismiss()
        }
    }

    fun hideBottomBar() {
        hideBottomBar(activity!!.window)
    }

    fun cleanSignature() {
        firmaView.clear()
    }

    fun actionButtonDraw(enabled: Boolean){
        btnDeletePaint.isEnabled = enabled
        btnSuccefullAsignature.isEnabled = enabled
    }

    companion object {
        fun newInstance(): ALSignatureAcceptedFragment {
            val fragment = ALSignatureAcceptedFragment()
            val arg = Bundle()
            //arg.putBoolean(true)
            return fragment
        }
    }

    override fun showSuccessfullSignmentFragment(){
        val showSuccessfullSignmentFragment: showSuccessfulAsignmentFragment = activity as ALetterActivity
        showSuccessfullSignmentFragment.showSuccessfullAsignment()
    }


    interface showSuccessfulAsignmentFragment {
        fun showSuccessfullAsignment()
    }

    fun progressDialogShow(context: Context) {
        progressDialog = Dialog(context)
        progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.show()
        progressDialog.setContentView(R.layout.progress_dialog_la)
        progressDialog.setCancelable(false)
        progressDialog.window?.let { hideBottomBar(it) }
    }


}
