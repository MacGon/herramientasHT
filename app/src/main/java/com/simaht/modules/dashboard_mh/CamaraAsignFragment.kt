package com.simaht.modules.dashboard_mh

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.dashboard_mh.R
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class CamaraAsignFragment: Fragment(), ZXingScannerView.ResultHandler {

    private lateinit var mScannerView: ZXingScannerView
    private lateinit var activity: AppCompatActivity
    lateinit var container: RelativeLayout
    private lateinit var fm: FragmentManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_camara_asign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = getActivity() as AppCompatActivity
        container = activity.findViewById(R.id.fragmentCamaraAsign)
        fm = activity.supportFragmentManager
        if((ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), 101)
        }
        mScannerView = activity.findViewById(R.id.scanner)
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
        mScannerView.setAutoFocus(true)
    }

    override fun handleResult(rawResult: Result?) {
        //println(rawResult!!.text)
        Toast.makeText(activity, rawResult?.text, Toast.LENGTH_LONG).show()
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
        //activity.onBackPressed()

    }
}