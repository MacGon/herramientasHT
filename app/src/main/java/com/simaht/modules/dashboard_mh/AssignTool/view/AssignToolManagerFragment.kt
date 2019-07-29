package com.simaht.dashboard_mh.AssignTool.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baz.simaht.login.extensions.addChildFragment
import com.baz.simaht.utils.CoConstants.ASSIGN
import com.baz.simaht.utils.CoConstants.Companion.COME_FROM_CAMERA
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.dashboard_mh.AssignTool.contracts.AssignToolContractI
import com.simaht.dashboard_mh.AssignTool.presenter.AssignToolPresenter
import kotlinx.android.synthetic.main.fragment_assign_tool.*
import kotlinx.android.synthetic.main.fragment_assign_tool.btnAssignTool
import kotlinx.android.synthetic.main.fragment_assign_tool.fbAddTool
import kotlinx.android.synthetic.main.fragment_assign_tool_manager.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AssignToolManagerFragment : Fragment() , AssignToolContractI.View {

    private lateinit var presenter : AssignToolContractI.Presenter
    private var normalFlow : Boolean = false
    private lateinit var childFragment: AssignToolChildFragment

    companion object {
        fun newInstance(comeFromCamera: Boolean): AssignToolManagerFragment {
            val fragment = AssignToolManagerFragment()
            val args = Bundle()
            args.putBoolean(COME_FROM_CAMERA, comeFromCamera)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            normalFlow = arguments!!.getBoolean(COME_FROM_CAMERA) //TODO CHANGE VARIABLE NAME
        }
        presenter = AssignToolPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assign_tool_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragment = AssignToolChildFragment.newInstance()

        addChildFragment(childFragment, contentAT.id)

        if (!normalFlow) { //open from camera (SCANNER) button
            //presenter.openScanner()
        }

        //Fixme ask in the service to get Tools (until Employee will be validate!)
        //presenter.askForTools()
        setOnclickListeners()

    }

    override fun showLoader() {
        //Loader.show
    }

    override fun hideLoader() {
        //Hide Loader
    }

    override fun putEmployeeData() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun putToolsFound(tools: ArrayList<Tool>) {
        //TODO child UpdateToolList
    }

    override fun addItem(scannedTool: Tool) {
       childFragment.addNewTool(scannedTool)
    }

    override fun readQR() {
        //TODO Open Camera To Scan QR
        val scannedTool = Tool("Heramienta 1","PAX 4000", "TODAY", 124, 4213, "$$36",false,"www.myBill.com")
        presenter.addScanedElement(scannedTool)
    }

    fun setOnclickListeners() {

        fbAddTool.setOnClickListener { v ->
            presenter.addElement()
        }

        btnAssignTool.setOnClickListener{ v ->
            //presenter.
        }
    }


}
