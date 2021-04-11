package br.com.ricardo.developer.cultuascnpj.banco.scripts

fun tabelaContato(): String {
    return "CREATE TABLE IF NOT EXISTS [contato](\n" +
            "            [id_contato] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "            [id_cnpj] INTEGER NOT NULL,\n" +
            "            [email] TEXT NULL,\n" +
            "            [telefone] TEXT NOT NULL);"
}

fun selectRetornaContato(tabela: String, idCnpj: Int): String? {
    return "select * from $tabela where id_cnpj = $idCnpj"
}