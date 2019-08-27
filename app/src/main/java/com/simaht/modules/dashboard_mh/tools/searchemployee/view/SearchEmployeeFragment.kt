package com.simaht.modules.dashboard_mh.tools.searchemployee.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.baz.simaht.login.extensions.postDelayed
import com.baz.simaht.login.extensions.toast
import com.example.dashboard_mh.R
import com.simaht.modules.dashboard_mh.tools.FragmentCommunication
import com.simaht.modules.dashboard_mh.tools.base.BaseFragment
import com.simaht.modules.dashboard_mh.tools.searchemployee.contract.ISearchingEmployeeContract
import com.simaht.modules.dashboard_mh.tools.searchemployee.presenter.SearchingEmployeePresenter
import kotlinx.android.synthetic.main.dialog_notification.view.*
import kotlinx.android.synthetic.main.fragment_searching_employee.*
import kotlinx.android.synthetic.main.fragment_searching_employee.btnContinue


class SearchEmployeeFragment : BaseFragment(), ISearchingEmployeeContract.View {

    private lateinit var parentView : FragmentCommunication
    private lateinit var presenter: ISearchingEmployeeContract.Presenter
    private lateinit var alertDialog: AlertDialog
    private lateinit var dialogView: View
    private var employeeFound = false
    private var employeeName: String = ""
        set(value) {
            if (value.isNotEmpty()) {
                field = value
                //employeeFound = true
                postDelayed(10){
                    showEmployee(field)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }
        presenter = SearchingEmployeePresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_searching_employee, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        svEmployeeNumber.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                parentView.hideKeyboardEvent(svEmployeeNumber)
                if (svEmployeeNumber.text.toString().isNotEmpty()) {
                    presenter.getInfoEmployee(svEmployeeNumber.text.toString().toInt())
                    svEmployeeNumber.setText("")
                } else
                    showMessage("¡Agrega un número de socio para continuar!")
                return@OnKeyListener true
            }
            false
        })

        svEmployeeNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                btnContinue.isEnabled = !s.isEmpty()
            }
        })

        clSearching.setOnClickListener {
            parentView.hideKeyboardEvent(svEmployeeNumber)
        }

        btnContinue.setOnClickListener {
            parentView.hideKeyboardEvent(svEmployeeNumber)
            presenter.getInfoEmployee(svEmployeeNumber.text.toString().toInt())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(communication: FragmentCommunication) =
                SearchEmployeeFragment().apply {
                    parentView = communication
                    //arguments = Bundle().apply {
                        //putString(ARG_PARAM1, param1)
                    //}
                }
    }

    override fun showLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(msg: String) {
        toast(msg)
    }

    override fun validateEmployeeNumber() {
        presenter.getInfoEmployee(svEmployeeNumber.text.toString().toInt())
    }

    override fun changeView(newEmployee: Boolean, toolsFound: Boolean, employeeNum: Int) {
        parentView.nextFragment(newEmployee, toolsFound, employeeNum)
    }

    fun putEmployeeFound(employeeName: String) {
        this.employeeName = employeeName
    }

    private fun showEmployee(employee: String) {

        val dialogBuilder = AlertDialog.Builder(activity!!)
        dialogView = layoutInflater.inflate(R.layout.dialog_notification, null)
        dialogView.ivImageAlert.visibility = View.VISIBLE
        dialogView.tvNormalDialog.text = String.format(resources.getString(R.string.msg_employee_found_as), employee)
        dialogView.btnDialogOK.text = "SI"
        dialogView.btnDialogCancel.text = "NO"
        dialogBuilder.setView(dialogView)
                .setCancelable(false)
        alertDialog = dialogBuilder.create()
        alertDialog.show()

        dialogView.btnDialogOK.setOnClickListener {
            alertDialog.dismiss()
            parentView.initializeLetterAssignment()
            //parentView.processDone()
        }
        dialogView.btnDialogCancel.setOnClickListener {
            alertDialog.dismiss()
        }
    }

}
