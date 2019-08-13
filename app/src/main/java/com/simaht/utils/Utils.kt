package com.simaht.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.InputFilter
import android.widget.EditText
import com.example.dashboard_mh.R


open class Utils {

    companion object {
        private lateinit var progressDialog: ProgressDialog

        fun progressDialogShow(context: Context) {
            progressDialog = ProgressDialog(context)
            //val msgLoading = "Conectándose al servidor,\nespere un momento..."
            //val spannableString = SpannableString(msgLoading)
            //spannableString.setSpan(RelativeSizeSpan(1.3f), 0, spannableString.length, 0)
            //progressDialog.setMessage(spannableString)
            progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progressDialog.show()
            progressDialog.setContentView(R.layout.custom_progressdialog)
            progressDialog.setCancelable(false)
        }

        fun progressDialogDismiss() {
            progressDialog.dismiss()
        }

        fun disableSpecialChar(editText: EditText) {
            //Caracteres -> @#$_&-+()/?!;:'"*,.~`|•√π÷×¶∆\}{=°^¥€¢£%©®™✓[]><
            val blockCharacterSet = "@#$ _&-+()/?!;:'\"*,.~`|•√π÷×¶∆\\}{=°^¥€¢£%©®™✓[]><"

            //val lengthFilter = arrayOfNulls<InputFilter>(1)
            //lengthFilter[0] = InputFilter.LengthFilter(8) //Filter to 9 characters
            //editText.filters = lengthFilter

            val lengthFilter = InputFilter.LengthFilter(8)
            val filter = InputFilter { source, start, end, dest, dstart, dend ->
                if (source != null && blockCharacterSet.contains("" + source)) {
                    ""
                } else null
            }
            editText.filters = arrayOf(filter, lengthFilter)
        }
    }
}