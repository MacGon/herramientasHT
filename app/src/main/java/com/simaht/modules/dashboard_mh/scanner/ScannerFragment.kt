package com.simaht.modules.dashboard_mh.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.dashboard_mh.R
import com.google.gson.Gson
import com.google.zxing.Result
import com.simaht.dashboard_mh.AssignTool.contracts.AssignToolContractI
import kotlinx.android.synthetic.main.fragment_camara_asign.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerFragment: Fragment(), ZXingScannerView.ResultHandler {

    private lateinit var mScannerView: ZXingScannerView
    private lateinit var activity: AppCompatActivity
    lateinit var container: RelativeLayout
    private lateinit var fm: FragmentManager
    private val codeScanner: Int = 1
    private val KEY_DATA: String = "DATA"
    private lateinit var gson: Gson
    private lateinit var presenter: AssignToolContractI.Presenter
    private var flagScaner: Boolean = false

    companion object {
        private lateinit var forResult: IScanner
        fun newIntance(communication: IScanner): ScannerFragment {
            val fragment = ScannerFragment()
            forResult = communication
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_camara_asign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = getActivity() as AppCompatActivity
        container = activity.findViewById(R.id.fragmentCamaraAsign)

        activity.supportFragmentManager.beginTransaction().addToBackStack(ScannerFragment::class.java.name)

        if((ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), 101)
        }
        tvLeyend.visibility = View.VISIBLE
        mScannerView = activity.findViewById(R.id.scanner)
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
        mScannerView.setAutoFocus(true)

    }

    override fun handleResult(rawResult: Result?) {
        forResult.returnValue(rawResult)
        //mScannerView.setResultHandler(this)
        //mScannerView.startCamera()
        //TODO put Result on his Context
        activity.supportFragmentManager.popBackStack()
    }
}