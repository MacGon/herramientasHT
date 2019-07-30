package com.simaht.dashboard_mh.AssignTool.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.baz.simaht.login.extensions.addChildFragment
import com.baz.simaht.utils.CoConstants.Companion.COME_FROM_CAMERA
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.dashboard_mh.AssignTool.contracts.AssignToolContractI
import com.simaht.dashboard_mh.AssignTool.presenter.AssignToolPresenter
import com.simaht.modules.camara.view.FunCamaraView
import kotlinx.android.synthetic.main.fragment_assign_tool_manager.*


/**
 * A simple [Fragment] subclass.
 *
 */
class AssignToolManagerFragment : Fragment(), AssignToolContractI.View {

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

        childFragment = AssignToolChildFragment.getInstance {
            if (it && fromCamera) {
                presenter.openScanner()
                btnAssignTool.visibility = View.VISIBLE
            }
        }


        addChildFragment(childFragment, contentAT.id)
        btnAssignTool.isClickable = false

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
        etEmployeeNumberAT.setText(infoEmployee)
        etEmployeeNumberAT.isEnabled = false
    }

    @SuppressLint("RestrictedApi")
    override fun putToolsFound(tools: ArrayList<Tool>) {
        childFragment.addNewTools(tools)
        btnAssignTool.isClickable = true
        btnAssignTool.text = resources.getText(R.string.msg_confirm)
        viewTopBttn.visibility = View.GONE
        fbAddToolAT.visibility = View.INVISIBLE
        fbAddToolAT.isClickable = false
        assignTool = false
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
        //TODO Open Camera To Scan QR

        val intent = Intent(activity, FunCamaraView::class.java)
        startActivity(intent)


        val scannedTool = Tool("Heramienta", "PAX 4000", "TODAY", 124, 4213, "$$36", false, "www.myBill.com")
        presenter.addScanedElement(scannedTool)
    }

    override fun enableAssignationBtn(enable: Boolean) {
        btnAssignTool.isEnabled = enable
    }

    fun setOnclickListeners() {

        fbAddToolAT.setOnClickListener {
            presenter.addElement()
        }

        btnAssignTool.setOnClickListener {
            if (assignTool) {
                if (childFragment.haveElements())
                    presenter.validateEmployeeNumber(etEmployeeNumberAT.text.toString())
                else
                    showMessage("Necesitar asignar almenos una herramienta")
            } else {
                etEmployeeNumberAT.setText("Martin Alvarez")
                //TODO get all the selected items and sed comfirmation
                //presenter.toolsComfirmed(childFragment.getElementsComfirmerd)
            }
        }

        etEmployeeNumberAT.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s != "") {
                    btnAssignTool.isEnabled = true
                    viewTopBttn.visibility = View.GONE
                    btnAssignTool.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

    }


}
