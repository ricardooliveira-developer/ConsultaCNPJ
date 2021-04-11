package br.com.ricardo.developer.cultuascnpj.viewmodel

import android.content.Context
import br.com.ricardo.developer.cultuascnpj.dao.*
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity

class SalvosViewModel {

    fun retornaListaSalvo(context: Context): ArrayList<CnpjEntity?> {
        return CnpjDao(context).retornaCnpjSalvos()
    }

    fun retornaContato(context: Context, cnpjEntity: CnpjEntity): CnpjEntity {
        return ContatoDao(context).retornaCnpjContato(cnpjEntity)
    }

    fun retornaEndereco(context: Context, cnpjEntity: CnpjEntity): CnpjEntity? {
        return EnderecoDao(context).retornaEndereco(cnpjEntity)
    }

    fun retornaQsa(context: Context, cnpjEntity: CnpjEntity): ArrayList<CnpjEntity.Qsa>? {
        return QsaDao(context).retornaQsa(cnpjEntity.id_cnpj)
    }

    fun retornaAtividadePrimaria(
        context: Context,
        cnpjEntity: CnpjEntity
    ): ArrayList<CnpjEntity.AtividadePrincipal> {
        return AtividadePrimariaDao(context).retornaAtividadePrimaria(cnpjEntity.id_cnpj)
    }

    fun retornaAtividadeSecundaria(
        context: Context,
        cnpjEntity: CnpjEntity
    ): ArrayList<CnpjEntity.AtividadesSecundarias> {
        return AtividadeSecundariaDao(context).retornaAtividadeSecundaria(cnpjEntity.id_cnpj)
    }


}