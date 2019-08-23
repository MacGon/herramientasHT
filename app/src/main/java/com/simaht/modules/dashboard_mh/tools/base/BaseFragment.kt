package com.simaht.modules.dashboard_mh.tools.base

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {

    protected val disposable = CompositeDisposable()

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }


}