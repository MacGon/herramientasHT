package com.simaht.modules.dashboard_mh


import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dashboard_mh.R
import com.simaht.modules.login.view.LoginView
import com.simaht.utils.Utils.Companion.disableSpecialChar
import kotlinx.android.synthetic.main.fragment_create_pass.*


class CreatePassFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_pass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoginView).nextStepKeyboardCreate()

        val lengthFilter = arrayOfNulls<InputFilter>(1)
        lengthFilter[0] = InputFilter.LengthFilter(8) //Filter to 9 characters
        etCreatePass.filters = lengthFilter

        disableSpecialChar(etCreatePass)
        disableSpecialChar(etRepeatPass)

        etCreatePass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tilCreatePassword.boxStrokeColor = resources.getColor(android.R.color.white)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        etRepeatPass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tilRepeatPassword.boxStrokeColor = resources.getColor(android.R.color.white)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        etCreatePass.hint = "Escribe tu nueva contraseña"

        etRepeatPass.hint = "Confirma tu contraseña"
    }
}
