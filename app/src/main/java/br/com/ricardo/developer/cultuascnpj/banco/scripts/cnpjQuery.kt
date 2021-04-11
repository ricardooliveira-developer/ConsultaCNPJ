package br.com.ricardo.developer.cultuascnpj.banco.scripts

fun tabelaCnpj(): String {

    return "CREATE TABLE IF NOT EXISTS [cnpj](\n" +
            "                 [id_cnpj] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "                 [status] TEXT NULL,\n" +
            "                 [message] TEXT NULL,\n" +
            "                 [cnpj] TEXT NULL,\n" +
            "                 [tipo] TEXT NULL,\n" +
            "                 [abertura] TEXT NULL,\n" +
            "                 [nome] TEXT NULL,\n" +
            "                 [fantasia] TEXT NULL,\n" +
            "                 [natureza_juridica] TEXT NULL,\n" +
            "                 [efr] TEXT NULL,\n" +
            "                 [situacao] TEXT NULL,\n" +
            "                 [data_situacao] TEXT NULL,\n" +
            "                 [motivo_situacao] TEXT NULL,\n" +
            "                 [situacao_especial] TEXT NULL,\n" +
            "                 [data_situacao_especial] TEXT NULL,\n" +
            "                 [capital_social] TEXT NOT NULL);"

}

fun selectRetornaTodosCnpj(tabela: String): String? {
    return "select * from $tabela"
}

fun selectRetornaCnpjSalvosPorParametro(tabela: String, cnpj: String): String? {
    return "select * from $tabela where cnpj LIKE '%$cnpj%'"
}