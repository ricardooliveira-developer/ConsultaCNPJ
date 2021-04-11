package br.com.ricardo.developer.cultuascnpj.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.ricardo.developer.cultuascnpj.banco.Dao
import br.com.ricardo.developer.cultuascnpj.banco.scripts.selectRetornaEndereco
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity

class EnderecoDao(
    context: Context
) : Dao(context) {

    companion object {
        const val TABELA = "endereco"
        const val ID_CNPJ = "id_cnpj"
        const val LOGRADOURO = "logradouro"
        const val NUMERO = "numero"
        const val COMPLEMENTO = "complemento"
        const val BAIRRO = "bairro"
        const val MUNICIPIO = "municipio"
        const val UF = "uf"
    }

    fun inserirEndereco(cnpj: CnpjEntity) {
        try {

            openConnection()

            val cv = ContentValues()

            cv.put(ID_CNPJ, cnpj.id_cnpj)
            cv.put(LOGRADOURO, cnpj.logradouro)
            cv.put(NUMERO, cnpj.numero)
            cv.put(COMPLEMENTO, cnpj.complemento)
            cv.put(BAIRRO, cnpj.bairro)
            cv.put(MUNICIPIO, cnpj.municipio)
            cv.put(UF, cnpj.uf)

            db?.insert(TABELA, null, cv)

            closeConnection()
        } catch (e: Exception) {

        }
    }

    fun retornaEndereco(endereco: CnpjEntity): CnpjEntity {

        try {
            openConnection()

            val comando = selectRetornaEndereco(TABELA, endereco.id_cnpj)

            val cursor: Cursor? = db?.rawQuery(comando, null)

            if (cursor != null) {
                cursor!!.moveToFirst()

                endereco.logradouro = cursor.getString(cursor.getColumnIndex(LOGRADOURO))
                endereco.numero = cursor.getString(cursor.getColumnIndex(NUMERO))
                endereco.complemento = cursor.getString(cursor.getColumnIndex(COMPLEMENTO))
                endereco.bairro = cursor.getString(cursor.getColumnIndex(BAIRRO))
                endereco.municipio = cursor.getString(cursor.getColumnIndex(MUNICIPIO))
                endereco.uf = cursor.getString(cursor.getColumnIndex(UF))

                cursor.close()
            }
            closeConnection()

        } catch (e: Exception) {
            closeConnection()
        }

        return endereco
    }

    fun deletarEndereco(id_cnpj: Int) {
        try {
            openConnection()

            val whereClause = "id_cnpj = ?"
            val whereArgs = arrayOf(id_cnpj.toString())

            db?.delete(TABELA, whereClause, whereArgs)

            closeConnection()

        } catch (e: Exception) {
            closeConnection()
        }
    }
}