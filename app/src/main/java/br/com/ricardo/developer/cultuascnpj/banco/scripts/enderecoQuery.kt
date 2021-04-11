package br.com.ricardo.developer.cultuascnpj.banco.scripts

fun tabelaEndereco(): String {

    return "CREATE TABLE IF NOT EXISTS [endereco](\n" +
            "                 [id_endereco] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "                 [id_cnpj] INTEGER NOT NULL,\n" +
            "                 [logradouro] TEXT NULL,\n" +
            "                 [numero] TEXT NULL,\n" +
            "                 [complemento] TEXT NULL,\n" +
            "                 [bairro] TEXT NULL,\n" +
            "                 [municipio] TEXT NULL,\n" +
            "                 [uf] TEXT NOT NULL);"

}

fun selectRetornaEndereco(tabela: String, idCnpj: Int): String? {
    return "select * from $tabela where id_cnpj = $idCnpj"
}