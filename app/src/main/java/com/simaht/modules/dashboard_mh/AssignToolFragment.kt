package com.simaht.modules.dashboard_mh


import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import com.example.dashboard_mh.R
import kotlinx.android.synthetic.main.fragment_assign_tool.*


class AssignToolFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val actions = arrayOf("Asignar herramienta", "Levantar inventario", "Dar de baja herramienta", "Expediente digital")
    private var spinner:Spinner? = null
    private var textviewMsg:TextView? = null
    private var button: AppCompatButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para el fragmento

        return inflater.inflate(R.layout.fragment_assign_tool, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //Cambiar el tipo de teclado
        etEmployeeNumber.inputType = InputType.TYPE_CLASS_NUMBER
        //Oculta el teclado al hacer tap fuera del textEdit
        etEmployeeNumber.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(view)
            }
        }

        //(activity as AppCompatActivity).supportActionBar!!.title = (Html.fromHtml("<font color=\"#6A6A6A\">" + "Asignar" + "</font>"))

        spinner = this.spAsignar
        spinner!!.onItemSelectedListener = this

        (spinner as AppCompatSpinner).setSelection(-1)

        // Se crea un ArrayAdapter usando el array de acciones
        val aa = ArrayAdapter(this.context!!, R.layout.spinner_item_main, actions)
        // Se configura el diseño para la lista de opciones
        aa.setDropDownViewResource(R.layout.spinner_item_empty)
        // Se ajusta el adaptador a nuestro Spinner
        spinner!!.adapter = aa

        val integrateCartaAsignacion: cartaAsignacion = activity as MainActivity

        btnAssignTool.setOnClickListener{
            //integrateCartaAsignacion.showCartaAsignacion()
            AlertDialog.Builder(view.context)
                //.setTitle("Firma de común acuerdo")
                .setView(R.layout.fragment_firma)
                /*.setMessage("Con esta firma dejo constatado que estoy de acuerdo y confirmo que todas " +
                        "las herramientas que se me entregaron están en óptimas condiciones y me comprometo mantenerlas" +
                        "en estado óptimo para mi trabajo. Confirmo que me notificaron de las sanciones a las que soy " +
                        "sujeto por todo daño y prejuicio que yo provoque a dichas herramientas.")*/
                /*.setPositiveButton("Estoy de acuerdo", null)*/
                .show()

        }

    }


    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        actions[position]
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    //Metodo para ocultar teclado haciendo TAP en cualquier parte del activity
    private fun hideKeyboard(view: View) {
        val inputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }



    interface cartaAsignacion{
        fun showCartaAsignacion()
    }
}
