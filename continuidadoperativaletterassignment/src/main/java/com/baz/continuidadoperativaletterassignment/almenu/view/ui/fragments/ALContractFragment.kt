package com.baz.continuidadoperativaletterassignment.almenu.view.ui.fragments


import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.baz.continuidadoperativaletterassignment.R
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.ALetterActivity
import kotlinx.android.synthetic.main.fragment_contract.*

class ALContractFragment : Fragment() {

    companion object {
        fun newInstance(): ALContractFragment {
            val fragment = ALContractFragment()
            val arg = Bundle()
            //arg.putBoolean(true)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //if (arguments != null)
            //arguments.get("destino", false)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contract,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title:String = getString(R.string.title_assignment)
        val description:String = getString(R.string.description_assignment)
        tvTitleLetter.setText(Html.fromHtml(title,1))
        tvDescription.setText(Html.fromHtml(description,2))
        super.onViewCreated(view, savedInstanceState)

        val showAsignatureAcceptedFragment : showAsignatureAcceptedFragment  = activity as ALetterActivity

        buttonLetter.setOnClickListener {
            showAsignatureAcceptedFragment.showAsignatureFragment()
        }
    }

    interface showAsignatureAcceptedFragment {
        fun showAsignatureFragment()
    }

}
