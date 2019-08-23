package com.simaht.modules.dashboard_mh.tools.toollist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.baz.simaht.login.extensions.toast
import com.baz.simaht.utils.CoConstants.Companion.COME_FROM_CAMERA
import com.baz.simaht.utils.CoConstants.Companion.SCANNER
import com.example.dashboard_mh.R
import com.google.zxing.Result
import com.jakewharton.rxbinding3.material.select
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.scanner.IScanner
import com.simaht.modules.dashboard_mh.scanner.ScannerFragment
import com.simaht.modules.dashboard_mh.tools.FragmentCommunication
import com.simaht.modules.dashboard_mh.tools.ToolTransferActivity
import com.simaht.modules.dashboard_mh.tools.toollist.contract.AddingToolContract
import com.simaht.modules.dashboard_mh.tools.toollist.presenter.AddingToolPresenter
import com.simaht.modules.dashboard_mh.tools.toollist.view.adapters.ToolListAdapter
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.activity_tool_transfer.*
import kotlinx.android.synthetic.main.fragment_addig_tools.*


class AddingToolsFragment : Fragment(), AddingToolContract.View, IScanner {

    private var openCamera: Boolean? = null
    private lateinit var toolListAdapter: ToolListAdapter
    private lateinit var presenter: AddingToolContract.Presenter
    private lateinit var parentView: FragmentCommunication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        parentView.addFragment(ScannerFragment.newIntance(this), SCANNER)
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
        (activity as ToolTransferActivity).nav_view.visibility = View.VISIBLE
        presenter.addScanedElement(presenter.addTool())
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
