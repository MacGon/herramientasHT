package com.simaht.modules.test_camera.presenter

import android.content.Context
import com.simaht.modules.test_camera.contract.ContractInterfaceTest
import com.simaht.modules.test_camera.model.TestCameraModel
import me.dm7.barcodescanner.zxing.ZXingScannerView

class testCameraPresenter(_view: ContractInterfaceTest.View): ContractInterfaceTest.Presenter {

    private var view: ContractInterfaceTest.View = _view
    private var model: ContractInterfaceTest.Model = TestCameraModel(this)

    override fun mostrarResultado(codigo: String) {
        view.mostrarResultado(codigo)
    }

    override fun escanearCodigo(context: Context) {
        model.escanearCodigo(context)
    }

    override fun mostrarCamara(scannerView: ZXingScannerView) {
        view.mostrarCamara(scannerView)
    }

    override fun finalizarEscaneo(codigo: String) {
        view.finalizarEscaneo(codigo)
    }
}