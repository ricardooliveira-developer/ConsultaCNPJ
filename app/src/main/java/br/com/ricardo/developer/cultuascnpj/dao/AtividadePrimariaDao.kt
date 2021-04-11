package br.com.ricardo.developer.cultuascnpj.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.ricardo.developer.cultuascnpj.banco.Dao
import br.com.ricardo.developer.cultuascnpj.banco.scripts.selectRetornaAtividades
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity
import java.util.*
import kotlin.collections.ArrayList

class AtividadePrimariaDao(
    context: Context
) : Dao(context) {

    companion object {
        const val TABELA = "atividade_primaria"
        const val ID_CNPJ = "id_cnpj"
        const val CODE = "code"
        const val TEXT = "text"
    }

    fun inserirAtividadePrimaria(cnpj: CnpjEntity) {
        try {

            for (i in cnpj.atividade_principal!!.indices) {
                openConnection()

                val cv = ContentValues()

                cv.put(ID_CNPJ, cnpj.id_cnpj)
                cv.put(CODE, cnpj.atividade_principal!![i].code)
                cv.put(TEXT, cnpj.atividade_principal!![i].text)

                db?.insert(TABELA, null, cv)

                closeConnection()
            }
        } catch (e: Exception) {

        }
    }


    fun retornaAtividadePrimaria(id_cnpj: Int): ArrayList<CnpjEntity.AtividadePrincipal> {

        val atividadePrimaria = ArrayList<CnpjEntity.AtividadePrincipal>()
        try {
            openConnection()

            val comando = selectRetornaAtividades(TABELA, id_cnpj)

            val cursor: Cursor? = db?.rawQuery(comando, null)

            cursor!!.moveToFirst()

            var atividadePrimariaAux = CnpjEntity.AtividadePrincipal()

            atividadePrimariaAux.code = cursor.getString(cursor.getColumnIndex(CODE))
            atividadePrimariaAux.text = cursor.getString(cursor.getColumnIndex(TEXT))

            atividadePrimaria.add(atividadePrimariaAux)

            while (cursor.moveToNext()) {

                atividadePrimariaAux = CnpjEntity.AtividadePrincipal()

                atividadePrimariaAux.code = cursor.getString(cursor.getColumnIndex(CODE))
                atividadePrimariaAux.text = cursor.getString(cursor.getColumnIndex(TEXT))

                atividadePrimaria.add(atividadePrimariaAux)
            }

            cursor.close()

            closeConnection()

        } catch (e: Exception) {
            closeConnection()
        }

        return atividadePrimaria
    }

    fun deletarAtividadePrimaria(id_cnpj: Int) {
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