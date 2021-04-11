package br.com.ricardo.developer.cultuascnpj.banco.scripts

var nome = ""
var qual = ""
var pais_origem = ""
var nome_rep_legal = ""
var qual_rep_legal = ""


fun tabelaQsa(): String {

    return "CREATE TABLE IF NOT EXISTS [qsa](\n" +
            "                 [id_qsa] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "                 [id_cnpj] INTEGER NOT NULL,\n" +
            "                 [nome] TEXT NULL,\n" +
            "                 [qual] TEXT NULL,\n" +
            "                 [pais_origem] TEXT NULL,\n" +
            "                 [nome_rep_legal] TEXT NULL,\n" +
            "                 [qual_rep_legal] TEXT NOT NULL);"
}

fun selectRetornaQsa(tabela: String, idCnpj: Int): String? {
    return "select * from $tabela where id_cnpj = $idCnpj"
}