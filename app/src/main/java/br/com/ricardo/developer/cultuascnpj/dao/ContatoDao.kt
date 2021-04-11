package br.com.ricardo.developer.cultuascnpj.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.ricardo.developer.cultuascnpj.banco.Dao
import br.com.ricardo.developer.cultuascnpj.banco.scripts.selectRetornaContato
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity

class ContatoDao(
    context: Context
) : Dao(context) {

    companion object {
        const val TABELA = "contato"
        const val ID_CNPJ = "id_cnpj"
        const val EMAIL = "email"
        const val TELEFONE = "telefone"
    }

    fun inserirContato(cnpj: CnpjEntity) {
        try {

            openConnection()

            val cv = ContentValues()

            cv.put(ID_CNPJ, cnpj.id_cnpj)
            cv.put(EMAIL, cnpj.email)
            cv.put(TELEFONE, cnpj.telefone)

            db?.insert(TABELA, null, cv)

            closeConnection()
        } catch (e: Exception) {

        }
    }

    fun retornaCnpjContato(contato: CnpjEntity): CnpjEntity {

        try {
            openConnection()

            val comando = selectRetornaContato(TABELA, contato.id_cnpj)

            val cursor: Cursor? = db?.rawQuery(comando, null)

            if (cursor != null) {
                cursor!!.moveToFirst()

                contato.email = cursor.getString(cursor.getColumnIndex(EMAIL))
                contato.telefone = cursor.getString(cursor.getColumnIndex(TELEFONE))

                cursor.close()
            }
            closeConnection()

        } catch (e: Exception) {
            closeConnection()
        }

        return contato
    }

    fun deletarContato(id_cnpj: Int) {
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