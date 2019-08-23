package com.simaht.modules.dashboard_mh.tools.toollist.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.baz.simaht.login.extensions.toast
import com.baz.simaht.utils.CoConstants.Companion.COME_FROM_CAMERA
import com.example.dashboard_mh.R
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.zxing.Result
import com.jakewharton.rxbinding3.material.select
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.scanner.IScanner
import com.simaht.modules.dashboard_mh.tools.FragmentCommunication
import com.simaht.modules.dashboard_mh.tools.ToolTransferActivity
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.presenter.ToolAssign
import com.simaht.modules.dashboard_mh.tools.toollist.contract.AddingToolContract
import com.simaht.modules.dashboard_mh.tools.toollist.presenter.AddingToolPresenter
import com.simaht.modules.dashboard_mh.tools.toollist.view.adapters.ToolListAdapter
import com.simaht.modules.test_camera.view.TestCamera
import com.simaht.utils.SelectableItem
import com.simaht.utils.Utils
import kotlinx.android.synthetic.main.activity_tool_transfer.*
import kotlinx.android.synthetic.main.fragment_addig_tools.*


class AddingToolsFragment : Fragment(), AddingToolContract.View, IScanner {

    private var openCamera: Boolean? = null
    private lateinit var toolListAdapter: ToolListAdapter
    private lateinit var presenter: AddingToolContract.Presenter
    private lateinit var parentView: FragmentCommunication
    private val codeScanner: Int = 1
    private val KEY_DATA: String = "DATA"
    private lateinit var gson: Gson
    private var flagScaner: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gson = Gson()
        arguments?.let {
            openCamera = it.getBoolean(COME_FROM_CAMERA)
        }
        presenter = AddingToolPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_addig_tools, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvContentTool.layoutManager = LinearLayoutManager(activity)
        rvContentTool.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        val toolsAssign = ToolAssign()

        toolListAdapter = ToolListAdapter(arrayListOf()) { select, emptyList ->
            btnContinue.isEnabled = if (select) select else true  //is selected
            if (emptyList) {
                btnContinue.isEnabled = false
            }
        }

        rvContentTool.adapter = toolListAdapter

        openCamera.let {
            (activity as ToolTransferActivity).nav_view.visibility = View.GONE
            presenter.openScanner()
        }

        setListeners()
        for (tool in toolsAssign.toolsArray) {
            addItem(Tool(tool.controlID!!, tool.numSerie!!, tool.numPlaca!!, tool.idCategoria!!, tool.descCategoria!!, tool.idTipo!!, tool.descTipo!!, tool.numSim!!))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(comeFromCamera: Boolean, communication: FragmentCommunication) =
                AddingToolsFragment().apply {
                    parentView = communication
                    arguments = Bundle().apply {
                        putBoolean(COME_FROM_CAMERA, comeFromCamera)
                    }
                }
    }

    override fun readQR() {
        val intent = Intent(activity, TestCamera::class.java)
        startActivityForResult(intent, codeScanner)
        //parentView.addFragment(ScannerFragment.newIntance(this), SCANNER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == codeScanner) {
            if (resultCode == Activity.RESULT_OK) {
                val code: String = data?.extras!!.getString(KEY_DATA, "")
                println("HOLAAAAA $code")
                try {
                    val obj: JsonObject = gson.fromJson(code, JsonObject::class.java)
                    presenter.getToolInfo(obj.get("numControl").asString)
                    flagScaner = true
                } catch (e: java.lang.Exception) {
                    flagScaner = false
                    onMessageError("Error al obtener información del servicio")
                    println("Error al obtener información del servicio")
                }
            } else {
                //presenter.onBackPressed()
            }
        }
    }

    override fun onMessageError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }

    override fun progressDialogShow() {
        Utils.progressDialogShow(context!!)
    }

    override fun progressDialogHide() {
        Utils.progressDialogDismiss()
    }

    override fun addItem(scannedTool: Tool) {
        btnContinue.isEnabled = true
        toolListAdapter.addNuewTool(SelectableItem(scannedTool))
    }

    override fun showMessage(msgInt: Int?, msgStr: String?) {
        when {
            msgInt != null ->
                toast(resources.getString(msgInt))
            msgStr != null ->
                toast(msgStr)
        }
    }

    override fun enableAssignationBtn(enable: Boolean) {
        //btnContinue.setTextAppearance(context, R.style.ButtonContinue)
        //btnAssignTools.setTextColor(R.color.colorWhite)
    }

    override fun returnValue(rawResult: Result?) {
        println("PRUEBA: $rawResult")
        presenter.getToolInfo(rawResult?.text!!)
        //(activity as ToolTransferActivity).nav_view.visibility = View.VISIBLE
        //presenter.addScanedElement(presenter.addTool())
    }

    private fun setListeners() {
        btnContinue.setOnClickListener {
            if (toolListAdapter.itemCount > 0) {
                parentView.putScannedTools(toolListAdapter.tools)
                parentView.nextFragment()
            }
        }

        btnAddTool.setOnClickListener {
            presenter.openScanner()
        }
    }

}
