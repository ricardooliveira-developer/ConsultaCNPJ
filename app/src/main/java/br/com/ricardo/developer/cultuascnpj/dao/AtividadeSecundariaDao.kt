package br.com.ricardo.developer.cultuascnpj.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.ricardo.developer.cultuascnpj.banco.Dao
import br.com.ricardo.developer.cultuascnpj.banco.scripts.selectRetornaAtividades
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity

class AtividadeSecundariaDao(
    context: Context
) : Dao(context) {

    companion object {
        const val TABELA = "atividade_secundaria"
        const val ID_CNPJ = "id_cnpj"
        const val CODE = "code"
        const val TEXT = "text"
    }

    fun inserirAtividadeSecundaria(cnpj: CnpjEntity) {
        try {

            for (i in cnpj.atividades_secundarias!!.indices) {
                openConnection()

                val cv = ContentValues()

                cv.put(ID_CNPJ, cnpj.id_cnpj)
                cv.put(CODE, cnpj.atividades_secundarias!![i].code)
                cv.put(TEXT, cnpj.atividades_secundarias!![i].text)

                db?.insert(TABELA, null, cv)

                closeConnection()
            }
        } catch (e: Exception) {

        }
    }


    fun retornaAtividadeSecundaria(id_cnpj: Int): ArrayList<CnpjEntity.AtividadesSecundarias> {

        val atividadessecundarias = ArrayList<CnpjEntity.AtividadesSecundarias>()
        try {
            openConnection()

            val comando = selectRetornaAtividades(TABELA, id_cnpj)

            val cursor: Cursor? = db?.rawQuery(comando, null)

            cursor!!.moveToFirst()

            var atividadesSecundariasAux = CnpjEntity.AtividadesSecundarias()

            atividadesSecundariasAux.code = cursor.getString(cursor.getColumnIndex(CODE))
            atividadesSecundariasAux.text = cursor.getString(cursor.getColumnIndex(TEXT))

            atividadessecundarias.add(atividadesSecundariasAux)

            while (cursor.moveToNext()) {

                atividadesSecundariasAux = CnpjEntity.AtividadesSecundarias()

                atividadesSecundariasAux.code = cursor.getString(cursor.getColumnIndex(CODE))
                atividadesSecundariasAux.text = cursor.getString(cursor.getColumnIndex(TEXT))

                atividadessecundarias.add(atividadesSecundariasAux)
            }

            cursor.close()

            closeConnection()

        } catch (e: Exception) {
            closeConnection()
        }

        return atividadessecundarias
    }

    fun deletarAtividadeSecundaria(id_cnpj: Int) {
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