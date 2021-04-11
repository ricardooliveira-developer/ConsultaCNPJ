package br.com.ricardo.developer.cultuascnpj.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ricardo.developer.cultuascnpj.R
import br.com.ricardo.developer.cultuascnpj.adapters.ListaCnpjSalvosAdapter
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity
import br.com.ricardo.developer.cultuascnpj.view.activity.Detalhes
import br.com.ricardo.developer.cultuascnpj.viewmodel.SalvosViewModel
import com.google.gson.Gson

class Salvos : Fragment() {

    private lateinit var listaCnpjSalvosAdapter: ListaCnpjSalvosAdapter
    private lateinit var listCnpj: ArrayList<CnpjEntity?>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_salva_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniVars()
    }

    private fun iniVars() {
        montarLista()
    }

    private fun montarLista() {

        listCnpj = SalvosViewModel().retornaListaSalvo(requireContext())


        for (i in 0 until listCnpj.size) {
            listCnpj[i] = SalvosViewModel().retornaContato(requireContext(), listCnpj[i]!!)
        }

        for (i in 0 until listCnpj.size) {
            listCnpj[i] = SalvosViewModel().retornaEndereco(requireContext(), listCnpj[i]!!)
        }

        for (i in 0 until listCnpj.size) {
            listCnpj[i]?.qsa =
                SalvosViewModel().retornaQsa(requireContext(), listCnpj[i]!!)?.toList()
        }

        for (i in 0 until listCnpj.size) {
            listCnpj[i]?.atividade_principal =
                SalvosViewModel().retornaAtividadePrimaria(requireContext(), listCnpj[i]!!)
                    ?.toList()
        }

        for (i in 0 until listCnpj.size) {
            listCnpj[i]?.atividades_secundarias =
                SalvosViewModel().retornaAtividadeSecundaria(requireContext(), listCnpj[i]!!)
                    ?.toList()
        }

        var listNome = arrayListOf<String>()

        for (i in 0 until listCnpj.size) {
            listNome.add("${listCnpj[i]!!.nome} - ${listCnpj[i]!!.cnpj}")
        }

        val rcv = view!!.findViewById<RecyclerView>(R.id.rv_salvados)
        rcv.layoutManager =
            LinearLayoutManager(context)

        listaCnpjSalvosAdapter = ListaCnpjSalvosAdapter(
            requireContext(),
            R.layout.celula_cnpj,
            listNome,
            ::processarItemSelecionado
        )

        rcv.adapter = listaCnpjSalvosAdapter
    }

    fun processarItemSelecionado(item: String) {

        var aux = item.split(' ')
        var obj = listCnpj.filter { it -> it!!.cnpj == aux[aux.size - 1] }

        val gson = Gson()
        val objCnpjJson = gson.toJson(listCnpj)

        val intent = Intent(context, Detalhes::class.java)
        intent.putExtra("obj_cnpj", objCnpjJson)
        intent.putExtra("valor_cnpj", aux[aux.size - 1])
        intent.putExtra("parametroSalvar", "0")
        startActivity(intent)
        this.requireActivity().finish()
    }

}