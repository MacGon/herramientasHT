package com.baz.continuidadoperativaletterassignment.almenu.view.ui.fragments

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.baz.continuidadoperativaletterassignment.R
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.ALetterActivity
import kotlinx.android.synthetic.main.fragment_asignature_accepted.*

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
        val title:String = getString(R.string.title_asignature_accepted)
        val descriptionAsignature: String = getString(R.string.description_asignature_accepted)
        tvTitleLetterAsignature.setText(Html.fromHtml(title,1))
        tvDescriptionAsignatureAccepted.setText(Html.fromHtml(descriptionAsignature,2))
        super.onViewCreated(view, savedInstanceState)

        val showSuccessfulAsignmentFragment: showSuccessfulAsignmentFragment = activity as ALetterActivity

        btnSuccefullAsignature.setOnClickListener {
            showSuccessfulAsignmentFragment.showSuccessfullAsignment()
        }

        btnDeleteImage.setOnClickListener { firmaView.clear()  }
    }

    interface showSuccessfulAsignmentFragment {
        fun showSuccessfullAsignment()
    }


}
