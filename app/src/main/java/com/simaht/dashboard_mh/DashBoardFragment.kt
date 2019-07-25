package com.simaht.dashboard_mh


import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dashboard_mh.R
import kotlinx.android.synthetic.main.fragment_dash_board.*


class DashBoardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(
            R.layout.fragment_dash_board,
            container, false
        )

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.title = (Html.fromHtml("<font color=\"#6A6A6A\">" + "" + "</font>"))

        val resultInteface: resultInterface = activity as MainActivity
        btnResultados.setOnClickListener {
            resultInteface.showResultsFragment()
        }

        val chargeToolInterface: chargeToolInterface = activity as MainActivity
        cvOne.setOnClickListener {
            chargeToolInterface.showChargeToolFragment()
        }

        val assignToolInterface: assignToolInterface = activity as MainActivity
        cvTwo.setOnClickListener {
            assignToolInterface.showAssignToolFragment()
        }

        val liftInventoryInterface: liftInventoryInterface = activity as MainActivity
        cvThree.setOnClickListener {
            liftInventoryInterface.showLiftInventoryFragment()
        }

        val unsubscribeToolInterface: unsubscribeTool = activity as MainActivity
        cvFour.setOnClickListener {
            unsubscribeToolInterface.showUnsubscribeToolFragment()
        }

        val integrateFileInterface: integrateFile = activity as MainActivity
        cvFive.setOnClickListener {
            integrateFileInterface.showIntegrateFileFragment()
        }
    }

    interface resultInterface {
        fun showResultsFragment()
    }

    interface chargeToolInterface {
        fun showChargeToolFragment()
    }

    interface assignToolInterface {
        fun showAssignToolFragment()
    }

    interface liftInventoryInterface {
        fun showLiftInventoryFragment()
    }

    interface unsubscribeTool {
        fun showUnsubscribeToolFragment()
    }

    interface  integrateFile {
        fun showIntegrateFileFragment()
    }
}
