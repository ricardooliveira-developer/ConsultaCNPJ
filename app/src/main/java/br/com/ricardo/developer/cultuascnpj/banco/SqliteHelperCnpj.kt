package br.com.ricardo.developer.cultuascnpj.banco

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.ricardo.developer.cultuascnpj.banco.scripts.*

class SqliteHelperCnpj(
    context: Context,
    nome: String,
    factory: SQLiteDatabase.CursorFactory?,
    versao: Int
) : SQLiteOpenHelper(context, nome, factory, versao) {

    override fun onCreate(db: SQLiteDatabase?) {
        try {

            val sb = StringBuilder()

            sb.append(tabelaCnpj()).append("#").append(tabelaAtividadePrimaria()).append("#")
                .append(tabelaAtividadeSecundaria()).append("#").append(tabelaQsa()).append("#")
                .append(tabelaContato()).append("#").append(tabelaEndereco())

            val comandos = sb.toString().split("#")

            for (i in comandos.indices) {
                db?.execSQL(comandos[i].toLowerCase())
            }

        } catch (e: Exception) {
            e.message
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}