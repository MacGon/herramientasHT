package com.simaht.dashboard_mh.AssignTool.presenter

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.baz.simaht.login.extensions.postDelayed
import com.example.dashboard_mh.BuildConfig
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.contracts.AssignToolContractI
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.presenter.ToolAssign
import com.simaht.modules.login.presenter.Employee
import com.simaht.network.data.OutModelTool
import com.simaht.network.remote.RestAPI
import com.simaht.utils.SelectableItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AssignToolPresenter2(val view: AssignToolContractI.View) : AssignToolContractI.Presenter {


    private val TAG: String = "AssignToolPresenter2"
    private lateinit var activity: AppCompatActivity

    override fun getToolInfo(controlNum: String) {
        val api = RestAPI()
        val disposable: Disposable = api.consultTool(controlNum).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Log.e(TAG, response.message!!)
                println("PERRRROOOOOOOTE $controlNum")
                if (response.code != 200) {
                    //Toast.makeText(activity, "Error: ${response.message}", Toast.LENGTH_LONG).show()
                    Log.e(TAG, "Error: " + response.message)
                } else {
                    addScanedElement(addTool())
                    val tool = ToolAssign()
                    tool.toolsArray.add(response.info)
                    tool.update()

                }
            }, { error ->
                //Toast.makeText(activity, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                Log.e(TAG, "Error del servidor: " + error.message)
            })
    }



    override fun setEmployeeNumber() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun validateInformationExistent(employeeNumber: Int) {
        view.showLoader()
        if (BuildConfig.DEBUG) {
            postDelayed(2000) {
                view.putEmployeeData("$employeeNumber - Test Employee")
                view.putToolsFound(arrayListOf(SelectableItem(Tool("PAX Test", "PAX 4000", "TODAY", 124, 4213, "$$36", false, "www.myBill.com")),
                        SelectableItem(Tool("Italika Test", "Moto Italika 220", "TODAY", 421, 21423, "$$2624", true, "www.myITALIK.com"))))
            }
        } else {
            //TODO Implement the service response
        }
    }

    override fun addElement() {
        view.readQR()
    }

    override fun validateEmployeeNumber(employeeNumber: String) {
        if (employeeNumber.isNotEmpty()) {
            //TODO sent to validate : employeeNumber.toInt()
            validateInformationExistent(employeeNumber.toInt())
        } else {
            view.showMessage(R.string.msg_empty_employee)
        }
    }

    override fun openScanner() {
        view.readQR()
    }

    override fun assignTools() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeView() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addScanedElement(scannedTool: Tool) {
        //Validate scanned Tool
        view.enableAssignationBtn(true)
        //All is ok
        view.addItem(scannedTool)
    }

    override fun addTool(): Tool {
        val tool = ToolAssign()
        //TODO this is a fake function
        /*val rnds = (0..4).random()
        return when (rnds) {
            0 -> {
                Tool("Ipad", "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")
            }
            1, 2 -> {
                Tool("Casco", "Italika", "17/07/12", 5423, 43843535443, "94733", true, "www.apple.com")
            }
            3 -> {
                Tool("Italika", "Italika", "06/02/08", 633432, 985872832, "9534.38", false, "www.italika.com")
            }
            else -> {
                Tool("Celular", "XAOMI", "17/05/19", 235235, 2352345653, "2489.99", true, "www.xaomi.com")
            }
        }*/

        return Tool(tool.toolsArray, "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")

    }

    override fun toolsFound() {
        postDelayed(2000) {
            val rnd: Int = (1..2).random()
            val toolsfound = arrayListOf<SelectableItem<Tool>>()
            for (i in 0..rnd) {
                toolsfound.add(SelectableItem(addTool()))
            }
            view.addItemFound(toolsfound)
        }
    }

}