package com.simaht.modules.dashboard_mh


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dashboard_mh.R
import com.simaht.modules.login.presenter.Employee
import com.simaht.modules.login.view.LoginView
import com.simaht.utils.Utils.Companion.disableSpecialChar
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val employee = Employee()
        tvTitleLogin.text = String.format(resources.getString(R.string.msg_title_login), employee.empNombre)
        tvForgetPass
        (activity as LoginView).nextStepKeyboardLogin()

        disableSpecialChar(etPasswordLogin)

        etPasswordLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tilPasswordLogin.boxStrokeColor = resources.getColor(android.R.color.white)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        etPasswordLogin.hint = "Contraseña"
    }
}
