package com.baz.continuidadoperativaletterassignment.almenu.view.ui.fragments

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

import com.baz.continuidadoperativaletterassignment.R
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.ALetterActivity
import kotlinx.android.synthetic.main.activity_coletter.*
import kotlinx.android.synthetic.main.fragment_asignature_accepted.*
import kotlinx.android.synthetic.main.fragment_asignature_accepted.view.*

class ALAsignatureAcceptedFragment : Fragment() {

    companion object {
        fun newInstance(): ALAsignatureAcceptedFragment {
            val fragment = ALAsignatureAcceptedFragment()
            val arg = Bundle()
            //arg.putBoolean(true)
            return fragment
        }
    }

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

    interface showSuccessfulAsignmentFragment {
        fun showSuccessfullAsignment()
    }

}
