package br.com.ricardo.developer.cultuascnpj.viewmodel

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.text.Editable
import android.text.TextWatcher
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity
import br.com.ricardo.developer.cultuascnpj.util.retrofit.InializadorRetrofit
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import org.json.JSONObject

@Suppress("DEPRECATION")
class ConsultaViewModel {

    fun getCnpj(cnpj: String): CnpjEntity? {
        var cnpjAux = CnpjEntity()
        try {
            val getCnpjTarefa: AsyncTask<String?, Void?, CnpjEntity?> =
                @SuppressLint("StaticFieldLeak")
                object : AsyncTask<String?, Void?, CnpjEntity?>() {
                    override fun doInBackground(vararg params: String?): CnpjEntity? {

                        try {

                            val getCnpj =
                                InializadorRetrofit().retornaInterfaceCnpj()
                                    .getCnpj(cnpj)

                            var response = getCnpj!!.execute()

                            if (response.isSuccessful) {
                                if (response.code() == 200) {
                                    try {
                                        val gson = Gson()
                                        val jObj = JSONObject(response.body()!!.string())

                                        val cnpjResult = gson.fromJson(
                                            jObj.toString(),
                                            CnpjEntity::class.java
                                        )

                                        cnpjAux = cnpjResult;
                                    } catch (e: Exception) {
                                        cnpjAux.erro = "Erro: ${e.message}"
                                        return cnpjAux
                                    }
                                } else {
                                    cnpjAux.erro = "Erro ao buscar CNPJ: Código ${response.code()}"
                                }
                            } else
                                cnpjAux.erro = "Erro ao buscar CNPJ"

                        } catch (e: Exception) {
                            cnpjAux.erro = "Erro: ${e.message}"
                            return cnpjAux
                        }
                        return cnpjAux
                    }
                }

            getCnpjTarefa.execute().get()

        } catch (e: Exception) {
            cnpjAux.erro = "Erro: ${e.message}"
            return cnpjAux
        }
        return cnpjAux
    }

    fun limparMascara(s: String): String {
        return s.replace("-", "").replace("/", "").replace(".", "")
    }

    fun mask(txtCnpj: TextInputEditText) {

        txtCnpj.addTextChangedListener(object : TextWatcher {
            var isUpdating: Boolean = false
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                // Variáveis de strings
                val str = limparMascara(s.toString())
                var mascara = ""
                var mask = "##.###.###/####-##"

                // Checa se está sendo feito update, para não entrar em loop infinito
                if (isUpdating) {
                    isUpdating = false
                    return
                }
                // Checa mascara e cria string com base na mascara
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && count > before) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                // Faz o update da string no EditText e verifica se está completa, colorindo se válido ou não
                isUpdating = true
                txtCnpj.setText(mascara)
                txtCnpj.setSelection(mascara.length)

            }
        })
    }

    fun validarValores(edCnpj: String): String {
        return try {
            when {
                edCnpj == "" -> "O campo CNPJ não pode estar em branco."
                edCnpj.length < 14 -> "O campo CNPJ precisa ter 14 caracteres!"
                else -> ""
            }

        } catch (e: Exception) {
            e.message.toString()
        }
    }
}