package com.simaht.modules.dashboard_mh.scanner

import com.google.zxing.Result

interface IScanner {
    fun returnValue(rawResult: Result?)
}