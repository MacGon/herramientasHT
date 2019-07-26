package com.simaht.modules.camara.presenter

import android.content.Context
import com.simaht.modules.camara.contract.ContractInterface
import com.simaht.modules.camara.model.FunCamaraModel
import me.dm7.barcodescanner.zxing.ZXingScannerView

class FunCamaraPresenter(_view: ContractInterface.View): ContractInterface.Presenter {

    private var view: ContractInterface.View = _view
    private var model: ContractInterface.Model = FunCamaraModel(this)

    override fun mostrarResultado(codigo: String) {
        view.mostrarResultado(codigo)
    }

    override fun escanearCodigo(context: Context) {
        model.escanearCodigo(context)
    }

    override fun mostrarCamara(scannerView: ZXingScannerView) {
        view.mostrarCamara(scannerView)
    }

    override fun finalizarEscaneo() {
        view.finalizarEscaneo()
    }


}