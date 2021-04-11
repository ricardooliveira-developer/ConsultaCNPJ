package br.com.ricardo.developer.cultuascnpj.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.ricardo.developer.cultuascnpj.R
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity
import br.com.ricardo.developer.cultuascnpj.view.activity.Result
import br.com.ricardo.developer.cultuascnpj.viewmodel.ConsultaViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class Consulta : Fragment() {

    private lateinit var txtCnpj: TextInputEditText
    private lateinit var btnPesquisar: Button
    private lateinit var edCnpj: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.consulta_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        txtCnpj = view!!.findViewById(R.id.txtCnpj_value)
        btnPesquisar = view!!.findViewById(R.id.btn_pesquisar)
        edCnpj = view!!.findViewById(R.id.txtCnpj_value)
    }

    private fun iniActions() {

        ConsultaViewModel().mask(txtCnpj)

        btnPesquisar.setOnClickListener {

            var cnpj = ConsultaViewModel().limparMascara(edCnpj.text.toString())

            var valida: String = ConsultaViewModel().validarValores(cnpj)

            if (valida == "") {

                var listObjCnpj = ArrayList<CnpjEntity>()

                var objCnpj = ConsultaViewModel().getCnpj(cnpj)

                if (objCnpj!!.erro == "" && objCnpj!!.status == "OK") {

                    listObjCnpj.add(objCnpj)

                    irParaTelaResul(listObjCnpj)

                    Toast.makeText(
                        requireContext(),
                        "Pesquisa realizada com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "ERRO: ${objCnpj!!.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireContext(), "CNPJ INVALIDO: $valida", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun irParaTelaResul(listObjCnpj: ArrayList<CnpjEntity>) {
        try {

            val gson = Gson()
            val objCnpjJson = gson.toJson(listObjCnpj!!)

            val intent = Intent(requireContext(), Result::class.java)
            intent.putExtra("obj_cnpj", objCnpjJson)
            startActivity(intent)

            this.requireActivity().finish()
        } catch (e: Exception) {
            e.message
        }
    }
}
