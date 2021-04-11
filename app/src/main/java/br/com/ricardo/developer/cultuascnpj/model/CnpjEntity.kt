package br.com.ricardo.developer.cultuascnpj.model

class CnpjEntity {
    var id_cnpj = 0
    var status = ""
    var message = ""
    var cnpj = ""
    var tipo = ""
    var abertura = ""
    var nome = ""
    var fantasia = ""
    var atividade_principal: List<AtividadePrincipal>? = null
    var atividades_secundarias: List<AtividadesSecundarias>? = null
    var natureza_juridica = ""
    var logradouro = ""
    var numero = ""
    var complemento = ""
    var cep = ""
    var bairro = ""
    var municipio = ""
    var uf = ""
    var email = ""
    var telefone = ""
    var efr = ""
    var situacao = ""
    var data_situacao = ""
    var motivo_situacao = ""
    var situacao_especial = ""
    var data_situacao_especial = ""
    var capital_social = ""
    var qsa: List<Qsa>? = null
    var erro = ""


    class AtividadePrincipal {
        var code = ""
        var text = ""
    }

    class AtividadesSecundarias {
        var code = ""
        var text = ""
    }

    class Qsa {
        var nome = ""
        var qual = ""
        var pais_origem = ""
        var nome_rep_legal = ""
        var qual_rep_legal = ""
    }
}