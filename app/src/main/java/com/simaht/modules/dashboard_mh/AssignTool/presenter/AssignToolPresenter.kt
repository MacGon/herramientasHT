package com.simaht.dashboard_mh.AssignTool.presenter

import android.os.Build
import com.baz.simaht.login.extensions.postDelayed
import com.example.dashboard_mh.BuildConfig
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.dashboard_mh.AssignTool.contracts.AssignToolContractI

class AssignToolPresenter(val view: AssignToolContractI.View): AssignToolContractI.Presenter {


    override fun setEmployeeNumber() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun askForTools() {
        view.showLoader()
        if (BuildConfig.DEBUG) {
            postDelayed(2000) {
                view.putToolsFound(arrayListOf(Tool("Heramienta 1","PAX 4000", "TODAY", 124, 4213, "$$36",false,"www.myBill.com"),
                            Tool("Heramienta 2","Moto Italika 220", "TODAY", 421, 21423, "$$2624",true,"www.myITALIK.com")))
            }
        } else {
            //TODO Implement the service response
        }
    }

    override fun addElement() {
        view.readQR()
    }

    override fun openScanner() {
        //TODO("open Scanner to read QR")
        view.readQR()
    }

    override fun assignTools() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addScanedElement(scannedTool: Tool) {
        //Validate scanned Tool
        //All is ok
        view.addItem(scannedTool)
    }

}