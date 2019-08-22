package com.simaht.modules.dashboard_mh.tools.employeefoundlast.presenter

import com.baz.simaht.login.extensions.postDelayed
import com.example.dashboard_mh.BuildConfig
import com.simaht.SIMAHTSingleton
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.contract.IEmployeeFoundContract
import com.simaht.modules.model.ActivoFijo
import com.simaht.modules.model.Custody
import com.simaht.network.remote.RestAPI
import com.simaht.utils.SelectableItem

class EmployeeFoundPresenter(val view: IEmployeeFoundContract.View) : IEmployeeFoundContract.Presenter {

    //ID GENERICO to Apply Custody
    private val IPAD = "116"
    private val IMPRESORA_A = "060"
    private val IMPRESORA_B = "061"
    private val PAX = "124"
    private var toolCustodyTest = arrayListOf<Tool>()
    private var toolCustody = arrayListOf<ActivoFijo>()
    private var employeeNumber: Int = 0

    override fun addToolToCustodyTest(tool: Tool) {
        toolCustodyTest.add(tool)
    }

    override fun addToolToCustody(tool: ActivoFijo) {
        toolCustody.add(tool)
    }

    override fun removeFromCystodyTest(tool: Tool) {
        toolCustodyTest.remove(tool)
    }

    override fun removeFromCystody(tool: ActivoFijo) {
        toolCustody.remove(tool)
    }

    override fun searchTools(employee: Int) {
        employeeNumber = employee
        view.showLoader()
        if (!BuildConfig.DEBUG) { //FIXME Remove this NEGATION Value
            postDelayed(500) {
                val toolsfound = arrayListOf<SelectableItem<Tool>>()
                toolsfound.add(SelectableItem(Tool("Ipad", "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")))
                toolsfound.add(SelectableItem(Tool("Impresora", "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")))
                toolsfound.add(SelectableItem(Tool("Casco", "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")))
                view.addItemFound(toolsfound)
                view.hideLoader()
            }
        } else {
            //FIXME made It like a singleton instance
            val api = RestAPI()
            api.consultTools(employee) { resp, stauts ->
                if (stauts) {
                    resp?.let {
                        val itemsFound = ArrayList<SelectableItem<ActivoFijo>>()
                        it.forEach {
                            itemsFound.add(SelectableItem(it))
                        }

                        if (BuildConfig.DEBUG) { //FIXME remove this part
                            val custodyArray : ArrayList<SelectableItem<ActivoFijo>> = arrayListOf()
                            applyFilter(itemsFound).forEach { custodyArray.add(it) }
                            val employeeName = itemsFound.first().item.nombreEmpleado

                            if (custodyArray.isNotEmpty()) {
                                view.setEmployee(true, employeeName)
                                view.addItemFound(devicesFound = custodyArray)
                            } else
                                view.setEmployee(false, employeeName)
                        }
                    }
                } else {
                    view.showMessage("Somethin was wrong OMG!")
                }
                view.hideLoader()
            }
        }
    }

    override fun addTool(): Tool {
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

        return Tool("Ipad", "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")

    }

    override fun applyCustody() {

        if (haveItemsToCustody()) {
            view.showLoader()
            val api = RestAPI() //FIXME
            toolCustody.forEach {
                it.numEmpleadoDestino = "919464" //FIXME /get my UserID usign Singleton
                it.numEmpleadoOrigen = "$employeeNumber"
            }
            val custody = Custody(toolCustody, "5356") //FIXME my UserID too!
            //Rest API
            api.doCustody(custody) {
                if (it)
                    view.custodyDone()
                else
                    view.wrongCustody()

                view.hideLoader()
            }
        } else {
            //TODO -- Could I need this validation??
        }
    }

    override fun haveItemsToCustody(): Boolean {
        return if (SIMAHTSingleton.isDebug())
            toolCustodyTest.isNotEmpty()
        else
            toolCustody.isNotEmpty()
    }

    private fun applyFilter(itemsFound: ArrayList<SelectableItem<ActivoFijo>>) = itemsFound.filter {
        it.item.idGenerico == IPAD || it.item.idGenerico == IMPRESORA_A || it.item.idGenerico == IMPRESORA_B || it.item.idGenerico == PAX }

}