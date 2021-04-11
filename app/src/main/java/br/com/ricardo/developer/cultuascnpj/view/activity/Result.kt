package br.com.ricardo.developer.cultuascnpj.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ricardo.developer.cultuascnpj.R
import br.com.ricardo.developer.cultuascnpj.adapters.ListaCnpjSalvosAdapter
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity
import com.google.gson.Gson

@Suppress("DEPRECATION")
class Result : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var listObjCnpj: Array<CnpjEntity>
    private lateinit var listaCnpjSalvosAdapter: ListaCnpjSalvosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_salva_fragment)
        iniVars()
    }

    private fun iniVars() {
        context = this
        recuperarParametros()
        montarLista()
    }

    private fun recuperarParametros() {
        val gson = Gson()
        val extras = intent.extras

        var objCnpjJson = extras?.getString("obj_cnpj").toString()

        listObjCnpj = gson.fromJson(
            objCnpjJson,
            Array<CnpjEntity>::class.java
        )
    }

    private fun montarLista() {

        try {
            var listNomes = arrayListOf<String>()

            for (i in listObjCnpj.indices) {
                listNomes.add("${listObjCnpj[i].nome} - ${listObjCnpj[i].cnpj}")
            }

            val rcv = findViewById<RecyclerView>(R.id.rv_salvados)
            rcv.layoutManager =
                LinearLayoutManager(context)

            listaCnpjSalvosAdapter = ListaCnpjSalvosAdapter(
                context,
                R.layout.celula_cnpj,
                listNomes,
                ::processarItemSelecionado
            )

            rcv.adapter = listaCnpjSalvosAdapter
        } catch (e: Exception) {
            e.message
        }
    }

    override fun onBackPressed() {
        voltar()
        super.onBackPressed()
    }

    private fun voltar() {
        val telaPrincipal = Intent(context, TelaPrincipal::class.java)
        startActivity(telaPrincipal)
        finish()
    }

    fun processarItemSelecionado(item: String) {

        var aux = item.split(' ')
        var obj = listObjCnpj.filter { it -> it.cnpj == aux[aux.size - 1] }

        val gson = Gson()
        val objCnpjJson = gson.toJson(listObjCnpj)

        val intent = Intent(context, Detalhes::class.java)
        intent.putExtra("obj_cnpj", objCnpjJson)
        intent.putExtra("valor_cnpj", aux[aux.size - 1])
        intent.putExtra("parametroSalvar", "1")
        startActivity(intent)
        finish()
    }
}