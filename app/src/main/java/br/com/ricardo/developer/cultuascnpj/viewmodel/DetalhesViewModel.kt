package br.com.ricardo.developer.cultuascnpj.viewmodel

import android.content.Context
import br.com.ricardo.developer.cultuascnpj.dao.*
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity

class DetalhesViewModel {

    fun concatenar(obj: ArrayList<*>): String {

        var valorConcatenado = StringBuilder()

        for (i in 0 until obj.size) {
            valorConcatenado.append("• ").append(obj[i].toString()).append("\n")
        }

        return valorConcatenado.toString()
    }

    fun limparMascara(s: String): String {
        return s.replace("-", "").replace("/", "").replace(".", "")
    }

    fun verificaCnpj(context: Context, cnpj: String): Int {
        return CnpjDao(context).retornaCnpjSalvosPorParametro(cnpj).count()
    }

    fun inserirCnpj(context: Context, cnpj: CnpjEntity) {
        CnpjDao(context).inserirCnpj(cnpj)
    }

    fun retornaIdCnpj(context: Context, cnpjSelecionado: String): Int {
        return CnpjDao(context).retornaCnpjId(cnpjSelecionado)
    }

    fun inserirContato(context: Context, cnpj: CnpjEntity) {
        ContatoDao(context).inserirContato(cnpj)
    }

    fun inserirEndereco(context: Context, objcnpj: CnpjEntity) {
        EnderecoDao(context).inserirEndereco(objcnpj)
    }

    fun inserirQsa(context: Context, objcnpj: CnpjEntity) {
        QsaDao(context).inserirQsa(objcnpj)
    }

    fun inserirAtividadePrimaria(context: Context, objcnpj: CnpjEntity) {
        AtividadePrimariaDao(context).inserirAtividadePrimaria(objcnpj)
    }

    fun inserirAtividadeSecundaria(context: Context, objcnpj: CnpjEntity) {
        AtividadeSecundariaDao(context).inserirAtividadeSecundaria(objcnpj)
    }
}