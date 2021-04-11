package br.com.ricardo.developer.cultuascnpj.banco.scripts

fun tabelaAtividadePrimaria(): String {
    return "CREATE TABLE IF NOT EXISTS [atividade_primaria](\n" +
            "                 [id_atividade_primaria] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "                 [id_cnpj] INTEGER NOT NULL,\n" +
            "                 [code] TEXT NULL,\n" +
            "                 [text] TEXT NOT NULL);"
}

fun tabelaAtividadeSecundaria(): String {
    return "CREATE TABLE IF NOT EXISTS [atividade_secundaria](\n" +
            "               [id_atividade_secundaria] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "               [id_cnpj] INTEGER NOT NULL,\n" +
            "               [code] TEXT NULL,\n" +
            "               [text] TEXT NOT NULL);"
}

fun selectRetornaAtividades(tabela: String, idCnpj: Int): String? {
    return "select * from $tabela where id_cnpj = $idCnpj"
}