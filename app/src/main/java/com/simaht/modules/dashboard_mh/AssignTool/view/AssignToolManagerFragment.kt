package com.simaht.dashboard_mh.AssignTool.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.baz.simaht.login.extensions.addChildFragment
import com.baz.simaht.utils.CoConstants.Companion.COME_FROM_CAMERA
import com.example.dashboard_mh.R
import com.google.zxing.Result
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.dashboard_mh.AssignTool.contracts.AssignToolContractI
import com.simaht.dashboard_mh.AssignTool.presenter.AssignToolPresenter
import com.simaht.modules.dashboard_mh.AssignTool.view.AddChildCommunication
import com.simaht.modules.dashboard_mh.MainActivity
import com.simaht.modules.dashboard_mh.scanner.IScanner
import com.simaht.modules.dashboard_mh.scanner.ScannerFragment
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.fragment_assign_tool_manager.*


/**
 * A simple [Fragment] subclass.
 *
 */
class AssignToolManagerFragment : Fragment(), AssignToolContractI.View, AddChildCommunication, IScanner {


    private lateinit var presenter: AssignToolContractI.Presenter
    private var fromCamera: Boolean = false
    private var assignTool: Boolean = true
    private lateinit var childFragment: AssignToolChildFragment

    companion object {
        fun getInstance(comeFromCamera: Boolean): AssignToolManagerFragment {
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
            fromCamera = arguments!!.getBoolean(COME_FROM_CAMERA)
        }
        presenter = AssignToolPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assign_tool_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragment = AssignToolChildFragment.getInstance({
            if (it && fromCamera) {
                presenter.openScanner()
            }
        }, this)

        addChildFragment(childFragment, containerToolList.id)
        //Fixme ask in the service to get Tools (until Employee will be validate!)
        //presenter.validateInformationExistent()
        setOnclickListeners()

    }

    override fun showLoader() {
        //Loader.show
    }

    override fun hideLoader() {
        //Hide Loader
    }

    override fun putEmployeeData(infoEmployee: String) {
//        etEmployeeNumberAT.isEnabled = false
    }

    @SuppressLint("RestrictedApi")
    override fun putToolsFound(tools: ArrayList<SelectableItem<Tool>>) {
        childFragment.addNewTools(tools)
    }

    override fun addItem(scannedTool: Tool) {
        childFragment.addNewTool(scannedTool)
    }

    private fun showUserMessage(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(msg: Int) {
        showUserMessage(resources.getString(msg))
    }

    override fun showMessage(msg: String) {
        showUserMessage(msg)
    }

    override fun readQR() {
        //val intent = Intent(activity, FunCamaraView::class.java)
        //startActivity(intent)
        (activity as MainActivity).addToBackStack(ScannerFragment.newIntance(this), "FragmentScanner")
    }

    override fun enableAssignationBtn(enable: Boolean) {
    }

    override fun onClickListen() {
        Log.i("TAG", "OMG ..... ")
        presenter.openScanner()
    }

    override fun onLongClickListener(itemSelected: Boolean) {
        if (itemSelected) {
            btnAssignTools.isEnabled = true
            btnAssignTools.setTextAppearance(context, R.style.ButtonComfirmToolAvailable)
        }
    }

    override fun returnValue(rawResult: Result?) {
        val scannedTool = Tool("0104000000274", "0821086542", "GS1613202", 1, "MOVILES", 4, "PAX SIM", "0000000000000000000F")
        presenter.addScanedElement(scannedTool)
    }

    fun setOnclickListeners() {
        btnAssignTools.setOnClickListener {
            if (assignTool) {
                if (childFragment.haveElements()) // do a collector of selected tools
                    Toast.makeText(activity, "Herramientas asignadas, falta la conexcion con el escaner!", Toast.LENGTH_SHORT).show()
                else
                    showMessage("Necesitar asignar almenos una herramienta")
            } else {
                //TODO get all the selected items and sed comfirmation
                //presenter.toolsComfirmed(childFragment.getElementsComfirmerd)
            }
        }

    }
}
