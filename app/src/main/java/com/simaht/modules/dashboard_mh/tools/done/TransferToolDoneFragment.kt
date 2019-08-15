package com.simaht.modules.dashboard_mh.tools.done


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dashboard_mh.R
import com.simaht.modules.dashboard_mh.tools.FragmentCommunication
import kotlinx.android.synthetic.main.fragment_transfer_tool_done.*

class TransferToolDoneFragment : Fragment() {

    private val FINISH_OK = "FINISH_OK"
    private var finishOk: Boolean? = null
    private lateinit var parentView : FragmentCommunication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            finishOk = it.getBoolean(FINISH_OK)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transfer_tool_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnContinue.setOnClickListener {
            parentView.processDone()
            parentView.nextFragment()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(allDone: Boolean, communication : FragmentCommunication) =
                TransferToolDoneFragment().apply {
                    parentView = communication
                    arguments = Bundle().apply {
                        putBoolean(FINISH_OK, allDone)
                    }
                }
    }

}
