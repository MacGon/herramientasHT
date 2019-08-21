package com.simaht.modules.dashboard_mh.tools.toollist.presenter

import android.util.Log
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.presenter.ToolAssign
import com.simaht.modules.dashboard_mh.tools.toollist.contract.AddingToolContract
import com.simaht.network.remote.RestAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AddingToolPresenter(val view: AddingToolContract.View):AddingToolContract.Presenter {

    private val TAG: String = "AssignToolPresenter2"

    override fun getToolInfo(controlNum: String) {
        view.progressDialogShow()
        val api = RestAPI()
        val disposable: Disposable = api.consultTool(controlNum).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.e(TAG, response.message!!)
                    if (response.code != 200) {
                        view.onMessageError("Error: ${response.message}")
                        Log.e(TAG, "Error: " + response.message)
                        view.progressDialogHide()
                    }
                    else {
                        val toolAssign = ToolAssign()
                        for (tool in response.info) {
                            if (toolAssign.validateControlId(tool)) {
                                view.onMessageError("Error: Código escaneado anteriormente")
                                view.progressDialogHide()
                            } else {
                                toolAssign.toolsArray.add(tool)
                                toolAssign.update()
                                addScanedElement(Tool(tool.controlID!!, "(${tool.numSerie!!})", tool.numPlaca!!, tool.idCategoria!!, tool.descCategoria!!, tool.idTipo!!, tool.descTipo!!, tool.numSim!!))
                                view.progressDialogHide()
                            }
                        }
                    }
                }, { error ->
                    Log.e(TAG, "Error del servidor: " + error.message)
                    view.onMessageError("Error del servidor: ${error.message}")
                    view.progressDialogHide()
                })
    }

    override fun addElement() {
        view.readQR()
    }

    override fun openScanner() {
        view.readQR()
    }

    override fun assignTools() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

        return Tool("0104000000274", "0821086542", "GS1613202", 1, "MOVILES", 4, "PAX SIM", "0000000000000000000F")

    }

    override fun addScanedElement(scannedTool: Tool) {
        //Validate scanned Tool
        view.addItem(scannedTool)
    }
}