package com.simaht.modules.dashboard_mh.tools.custody.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.FragmentCommunication
import com.simaht.modules.dashboard_mh.tools.custody.contract.ICustodyContract
import com.simaht.modules.dashboard_mh.tools.custody.presenter.ToolCustodyPresenter
import com.simaht.modules.dashboard_mh.tools.custody.view.adapter.ToolCustodyAdapter
import com.simaht.utils.SelectableItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_notification.view.*
import kotlinx.android.synthetic.main.fragment_employee_found.*

class ToolCustodyFragment : Fragment(), ICustodyContract.View {

    private lateinit var parentView: FragmentCommunication
    private lateinit var toolListFound: ArrayList<SelectableItem<Tool>>
    private lateinit var adapter : ToolCustodyAdapter
    private lateinit var presenter : ICustodyContract.Presenter
    private lateinit var alertDialog: AlertDialog
    private lateinit var dialogView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        presenter = ToolCustodyPresenter(this)
        return inflater.inflate(R.layout.fragment_employee_found, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvContentTool.layoutManager = LinearLayoutManager(activity)
        rvContentTool.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        adapter = ToolCustodyAdapter(toolListFound) { item, selected ->
            if (selected) {
                presenter.addToolToCustody(item)
            } else {
                presenter.removeToolToCustody(item)
            }

            btnContinue.isEnabled = presenter.haveItemsToCustody()
        }
        rvContentTool.adapter = adapter
        presenter.employee()

        Picasso.get()
                .load("//url.com.mx")
                .placeholder(R.drawable.avatar_employee)
                .error(R.drawable.avatar_employee)
                .into(employeeFace)

        btnContinue.setOnClickListener {
            notifyAction()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(custodyTools : ArrayList<SelectableItem<Tool>>, communication: FragmentCommunication) =
                ToolCustodyFragment().apply {
                    toolListFound = custodyTools
                    parentView = communication
                    //arguments = Bundle().apply {
                        //putString(ARG_PARAM1, param1)
                    //}
                }
    }

    override fun showLoader() {

    }

    override fun hideLoader() {
    }

    override fun custodyDone() {
        parentView.nextFragment()
    }

    override fun setEmployee() {
        val number = 46244
        employeeAbstract.text = "${number} - GCR"
        employeeName.text = "Banco Azteca Test JH"

    }


    private fun notifyAction() {

        val dialogBuilder = AlertDialog.Builder(activity!!)
        dialogView = layoutInflater.inflate(R.layout.dialog_notification, null)
        dialogView.ivImageAlert.visibility = View.VISIBLE
        dialogView.tvNormalDialog.text = String.format(resources.getString(R.string.msg_custody_tool), employeeName.text.toString())
        dialogBuilder.setView(dialogView)
                .setCancelable(false)
        alertDialog = dialogBuilder.create()
        alertDialog.show()

        /*val builder = AlertDialog.Builder(context, R.style.DialogStyle)//Context is activity context
        viewAlert = activity!!.layoutInflater.inflate(R.layout.dialog_notification, null, false)*/
        /*
        builder.setView(viewAlert)
                .setCancelable(false)
                .setMessage("")
                .setTitle(null)
                .setMessage(null)
        val lp = /*LinearLayout.LayoutParams(500,
                LinearLayout.LayoutParams.MATCH_PARENT)*/
        ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
        viewAlert.layoutParams = lp
        builder.setView(viewAlert)
        dialog = builder.create()
        setDimentionsDialog()*/
        //dialog.show()

        dialogView.btnDialogOK.setOnClickListener {
            alertDialog.dismiss()
            presenter.applyCustody()
        }
        dialogView.btnDialogCancel.setOnClickListener {
            alertDialog.dismiss()
            goBack()
        }
    }

    private fun goBack() {
        parentView.removeFragment()
    }

}
