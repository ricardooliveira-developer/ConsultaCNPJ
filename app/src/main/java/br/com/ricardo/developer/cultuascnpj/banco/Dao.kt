package br.com.ricardo.developer.cultuascnpj.banco

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import br.com.ricardo.developer.cultuascnpj.util.constantes.BancoConstantes

open class Dao(
    private val context: Context
) {

    protected var db: SQLiteDatabase? = null

    fun openConnection() {
        val dbHelper = SqliteHelperCnpj(
            context,
            BancoConstantes.NOME,
            null,
            BancoConstantes.VERSAO
        )

        this.db = dbHelper.writableDatabase
    }

    fun closeConnection() {
        db?.close()
        db = null
    }
}