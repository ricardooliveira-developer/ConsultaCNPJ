package br.com.ricardo.developer.cultuascnpj.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.ricardo.developer.cultuascnpj.banco.Dao
import br.com.ricardo.developer.cultuascnpj.banco.scripts.selectRetornaQsa
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity
import java.util.*
import kotlin.collections.ArrayList

class QsaDao(
    context: Context
) : Dao(context) {

    companion object {
        const val TABELA = "qsa"
        const val ID_CNPJ = "id_cnpj"
        const val NOME = "nome"
        const val QUAL = "qual"
        const val PAIS_ORIGEM = "pais_origem"
        const val NOME_REP_LEGAL = "nome_rep_legal"
        const val QUAL_REP_LEGAL = "qual_rep_legal"
    }

    fun inserirQsa(cnpj: CnpjEntity) {
        try {

            for (i in cnpj.qsa!!.indices) {
                openConnection()

                val cv = ContentValues()

                cv.put(ID_CNPJ, cnpj.id_cnpj)
                cv.put(NOME, cnpj.qsa!![i].nome)
                cv.put(QUAL, cnpj.qsa!![i].qual)
                cv.put(PAIS_ORIGEM, cnpj.qsa!![i].pais_origem)
                cv.put(NOME_REP_LEGAL, cnpj.qsa!![i].nome_rep_legal)
                cv.put(QUAL_REP_LEGAL, cnpj.qsa!![i].qual_rep_legal)

                db?.insert(TABELA, null, cv)

                closeConnection()
            }
        } catch (e: Exception) {

        }
    }

    fun retornaQsa(id_cnpj: Int): ArrayList<CnpjEntity.Qsa>? {

        val qsa = ArrayList<CnpjEntity.Qsa>()
        try {
            openConnection()

            val comando = selectRetornaQsa(TABELA, id_cnpj)

            val cursor: Cursor? = db?.rawQuery(comando, null)

            cursor!!.moveToFirst()

            var qsaAux = CnpjEntity.Qsa()

            qsaAux.nome = cursor.getString(cursor.getColumnIndex(NOME))
            qsaAux.qual = cursor.getString(cursor.getColumnIndex(QUAL))
            qsaAux.nome_rep_legal = cursor.getString(cursor.getColumnIndex(NOME_REP_LEGAL))
            qsaAux.pais_origem = cursor.getString(cursor.getColumnIndex(PAIS_ORIGEM))
            qsaAux.qual_rep_legal = cursor.getString(cursor.getColumnIndex(QUAL_REP_LEGAL))

            qsa.add(qsaAux)

            while (cursor.moveToNext()) {

                qsaAux = CnpjEntity.Qsa()

                qsaAux.nome = cursor.getString(cursor.getColumnIndex(NOME))
                qsaAux.qual = cursor.getString(cursor.getColumnIndex(QUAL))
                qsaAux.nome_rep_legal = cursor.getString(cursor.getColumnIndex(NOME_REP_LEGAL))
                qsaAux.pais_origem = cursor.getString(cursor.getColumnIndex(PAIS_ORIGEM))
                qsaAux.qual_rep_legal = cursor.getString(cursor.getColumnIndex(QUAL_REP_LEGAL))

                qsa.add(qsaAux)
            }

            cursor.close()

            closeConnection()

        } catch (e: Exception) {
            closeConnection()
        }

        return qsa
    }

    fun deletarQsa(id_cnpj: Int) {
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