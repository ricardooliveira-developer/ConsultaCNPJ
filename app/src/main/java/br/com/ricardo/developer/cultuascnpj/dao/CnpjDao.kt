package br.com.ricardo.developer.cultuascnpj.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.ricardo.developer.cultuascnpj.banco.Dao
import br.com.ricardo.developer.cultuascnpj.banco.scripts.selectRetornaCnpjSalvosPorParametro
import br.com.ricardo.developer.cultuascnpj.banco.scripts.selectRetornaTodosCnpj
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity
import java.util.*

class CnpjDao(
    context: Context
) : Dao(context) {

    companion object {
        const val TABELA = "cnpj"
        const val ID_CNPJ = "id_cnpj"
        const val STATUS = "status"
        const val MESSAGE = "message"
        const val CNPJ = "cnpj"
        const val TIPO = "tipo"
        const val ABERTURA = "abertura"
        const val NOME = "nome"
        const val FANTASIA = "fantasia"
        const val NATUREZA_JURIDICA = "natureza_juridica"
        const val EFR = "efr"
        const val SITUACAO = "situacao"
        const val DATA_SITUACAO = "data_situacao"
        const val MOTIVO_SITUACAO = "motivo_situacao"
        const val SITUACAO_ESPECIAL = "situacao_especial"
        const val DATA_SITUACAO_ESPECIAL = "data_situacao_especial"
        const val CAPITAL_SOCIAL = "capital_social"
    }

    fun inserirCnpj(cnpj: CnpjEntity) {
        try {

            openConnection()

            val cv = ContentValues()

            cv.put(STATUS, cnpj.status)
            cv.put(MESSAGE, cnpj.message)
            cv.put(CNPJ, cnpj.cnpj)
            cv.put(TIPO, cnpj.tipo)
            cv.put(ABERTURA, cnpj.abertura)
            cv.put(NOME, cnpj.nome)
            cv.put(FANTASIA, cnpj.fantasia)
            cv.put(NATUREZA_JURIDICA, cnpj.natureza_juridica)
            cv.put(EFR, cnpj.efr)
            cv.put(SITUACAO, cnpj.situacao)
            cv.put(DATA_SITUACAO, cnpj.data_situacao)
            cv.put(MOTIVO_SITUACAO, cnpj.motivo_situacao)
            cv.put(SITUACAO_ESPECIAL, cnpj.situacao_especial)
            cv.put(DATA_SITUACAO_ESPECIAL, cnpj.data_situacao_especial)
            cv.put(CAPITAL_SOCIAL, cnpj.capital_social)

            db?.insert(TABELA, null, cv)

            closeConnection()
        } catch (e: Exception) {

        }
    }

    fun retornaCnpjSalvos(): ArrayList<CnpjEntity?> {

        val listaCnpj = ArrayList<CnpjEntity?>()

        try {
            openConnection()

            val comando = selectRetornaTodosCnpj(TABELA)

            val cursor: Cursor? = db?.rawQuery(comando, null)

            cursor!!.moveToFirst()

            var cnpjAux = CnpjEntity()

            cnpjAux.id_cnpj = cursor.getInt(cursor.getColumnIndex(ID_CNPJ))
            cnpjAux.status = cursor.getString(cursor.getColumnIndex(STATUS))
            cnpjAux.message = cursor.getString(cursor.getColumnIndex(MESSAGE))
            cnpjAux.cnpj = cursor.getString(cursor.getColumnIndex(CNPJ))
            cnpjAux.tipo = cursor.getString(cursor.getColumnIndex(TIPO))
            cnpjAux.abertura = cursor.getString(cursor.getColumnIndex(ABERTURA))
            cnpjAux.nome = cursor.getString(cursor.getColumnIndex(NOME))
            cnpjAux.fantasia = cursor.getString(cursor.getColumnIndex(FANTASIA))
            cnpjAux.natureza_juridica = cursor.getString(cursor.getColumnIndex(NATUREZA_JURIDICA))
            cnpjAux.efr = cursor.getString(cursor.getColumnIndex(EFR))
            cnpjAux.situacao = cursor.getString(cursor.getColumnIndex(SITUACAO))
            cnpjAux.data_situacao = cursor.getString(cursor.getColumnIndex(DATA_SITUACAO))
            cnpjAux.motivo_situacao = cursor.getString(cursor.getColumnIndex(MOTIVO_SITUACAO))
            cnpjAux.situacao_especial = cursor.getString(cursor.getColumnIndex(SITUACAO_ESPECIAL))
            cnpjAux.data_situacao_especial = cursor.getString(
                cursor.getColumnIndex(
                    DATA_SITUACAO_ESPECIAL
                )
            )
            cnpjAux.capital_social = cursor.getString(cursor.getColumnIndex(CAPITAL_SOCIAL))
            listaCnpj.add(cnpjAux)

            while (cursor.moveToNext()) {

                cnpjAux = CnpjEntity()

                cnpjAux.id_cnpj = cursor.getInt(cursor.getColumnIndex(ID_CNPJ))
                cnpjAux.status = cursor.getString(cursor.getColumnIndex(STATUS))
                cnpjAux.message = cursor.getString(cursor.getColumnIndex(MESSAGE))
                cnpjAux.cnpj = cursor.getString(cursor.getColumnIndex(CNPJ))
                cnpjAux.tipo = cursor.getString(cursor.getColumnIndex(TIPO))
                cnpjAux.abertura = cursor.getString(cursor.getColumnIndex(ABERTURA))
                cnpjAux.nome = cursor.getString(cursor.getColumnIndex(NOME))
                cnpjAux.fantasia = cursor.getString(cursor.getColumnIndex(FANTASIA))
                cnpjAux.natureza_juridica =
                    cursor.getString(cursor.getColumnIndex(NATUREZA_JURIDICA))
                cnpjAux.efr = cursor.getString(cursor.getColumnIndex(EFR))
                cnpjAux.situacao = cursor.getString(cursor.getColumnIndex(SITUACAO))
                cnpjAux.data_situacao = cursor.getString(cursor.getColumnIndex(DATA_SITUACAO))
                cnpjAux.motivo_situacao = cursor.getString(cursor.getColumnIndex(MOTIVO_SITUACAO))
                cnpjAux.situacao_especial =
                    cursor.getString(cursor.getColumnIndex(SITUACAO_ESPECIAL))
                cnpjAux.data_situacao_especial = cursor.getString(
                    cursor.getColumnIndex(
                        DATA_SITUACAO_ESPECIAL
                    )
                )
                cnpjAux.capital_social = cursor.getString(cursor.getColumnIndex(CAPITAL_SOCIAL))
                listaCnpj.add(cnpjAux)

            }

            cursor.close()

            closeConnection()

        } catch (e: Exception) {
            closeConnection()
        }

        return listaCnpj
    }

    fun retornaCnpjSalvosPorParametro(cnpj: String): ArrayList<CnpjEntity?> {

        val listaCnpj = ArrayList<CnpjEntity?>()

        try {
            openConnection()

            val comando = selectRetornaCnpjSalvosPorParametro(TABELA, cnpj)

            val cursor: Cursor? = db?.rawQuery(comando, null)

            cursor!!.moveToFirst()

            var cnpjAux = CnpjEntity()

            cnpjAux.id_cnpj = cursor.getInt(cursor.getColumnIndex(ID_CNPJ))
            cnpjAux.status = cursor.getString(cursor.getColumnIndex(STATUS))
            cnpjAux.message = cursor.getString(cursor.getColumnIndex(MESSAGE))
            cnpjAux.cnpj = cursor.getString(cursor.getColumnIndex(CNPJ))
            cnpjAux.tipo = cursor.getString(cursor.getColumnIndex(TIPO))
            cnpjAux.abertura = cursor.getString(cursor.getColumnIndex(ABERTURA))
            cnpjAux.nome = cursor.getString(cursor.getColumnIndex(NOME))
            cnpjAux.fantasia = cursor.getString(cursor.getColumnIndex(FANTASIA))
            cnpjAux.natureza_juridica = cursor.getString(cursor.getColumnIndex(NATUREZA_JURIDICA))
            cnpjAux.efr = cursor.getString(cursor.getColumnIndex(EFR))
            cnpjAux.situacao = cursor.getString(cursor.getColumnIndex(SITUACAO))
            cnpjAux.data_situacao = cursor.getString(cursor.getColumnIndex(DATA_SITUACAO))
            cnpjAux.motivo_situacao = cursor.getString(cursor.getColumnIndex(MOTIVO_SITUACAO))
            cnpjAux.situacao_especial = cursor.getString(cursor.getColumnIndex(SITUACAO_ESPECIAL))
            cnpjAux.data_situacao_especial = cursor.getString(
                cursor.getColumnIndex(
                    DATA_SITUACAO_ESPECIAL
                )
            )
            cnpjAux.capital_social = cursor.getString(cursor.getColumnIndex(CAPITAL_SOCIAL))
            listaCnpj.add(cnpjAux)

            while (cursor.moveToNext()) {

                cnpjAux = CnpjEntity()

                cnpjAux.id_cnpj = cursor.getInt(cursor.getColumnIndex(ID_CNPJ))
                cnpjAux.status = cursor.getString(cursor.getColumnIndex(STATUS))
                cnpjAux.message = cursor.getString(cursor.getColumnIndex(MESSAGE))
                cnpjAux.cnpj = cursor.getString(cursor.getColumnIndex(CNPJ))
                cnpjAux.tipo = cursor.getString(cursor.getColumnIndex(TIPO))
                cnpjAux.abertura = cursor.getString(cursor.getColumnIndex(ABERTURA))
                cnpjAux.nome = cursor.getString(cursor.getColumnIndex(NOME))
                cnpjAux.fantasia = cursor.getString(cursor.getColumnIndex(FANTASIA))
                cnpjAux.natureza_juridica =
                    cursor.getString(cursor.getColumnIndex(NATUREZA_JURIDICA))
                cnpjAux.efr = cursor.getString(cursor.getColumnIndex(EFR))
                cnpjAux.situacao = cursor.getString(cursor.getColumnIndex(SITUACAO))
                cnpjAux.data_situacao = cursor.getString(cursor.getColumnIndex(DATA_SITUACAO))
                cnpjAux.motivo_situacao = cursor.getString(cursor.getColumnIndex(MOTIVO_SITUACAO))
                cnpjAux.situacao_especial =
                    cursor.getString(cursor.getColumnIndex(SITUACAO_ESPECIAL))
                cnpjAux.data_situacao_especial = cursor.getString(
                    cursor.getColumnIndex(
                        DATA_SITUACAO_ESPECIAL
                    )
                )
                cnpjAux.capital_social = cursor.getString(cursor.getColumnIndex(CAPITAL_SOCIAL))
                listaCnpj.add(cnpjAux)

            }

            cursor.close()

            closeConnection()

        } catch (e: Exception) {
            closeConnection()
        }

        return listaCnpj
    }

    fun retornaCnpjId(cnpj: String): Int {

        var id = 0

        try {
            openConnection()

            val comando = selectRetornaCnpjSalvosPorParametro(TABELA, cnpj.trim())

            val cursor: Cursor? = db?.rawQuery(comando, null)

            cursor!!.moveToFirst()

            id = cursor.getInt(cursor.getColumnIndex(ID_CNPJ))

            cursor.close()

            closeConnection()

        } catch (e: Exception) {
            closeConnection()
        }

        return id
    }

    fun deletarPorCnpj(cnpj: String) {
        try {
            openConnection()

            val whereClause = "cnpj = ?"
            val whereArgs = arrayOf(cnpj)

            db?.delete(TABELA, whereClause, whereArgs)

            closeConnection()

        } catch (e: Exception) {
            closeConnection()
        }
    }

}