package br.com.ricardo.developer.cultuascnpj.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.ricardo.developer.cultuascnpj.R
import br.com.ricardo.developer.cultuascnpj.model.CnpjEntity
import br.com.ricardo.developer.cultuascnpj.viewmodel.DetalhesViewModel
import com.google.gson.Gson

class Detalhes : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var objCnpj: Array<CnpjEntity>
    private lateinit var cnpjSelecionado: String
    private lateinit var titulo: TextView
    private lateinit var cnpj: TextView
    private lateinit var razaoSocial: TextView
    private lateinit var nomeFantasia: TextView
    private lateinit var dataAbertura: TextView
    private lateinit var tipo: TextView
    private lateinit var situacao: TextView
    private lateinit var naturezaJuridica: TextView
    private lateinit var capitalSocial: TextView
    private lateinit var endereo: TextView
    private lateinit var contato: TextView
    private lateinit var atividadePrincipal: TextView
    private lateinit var atividadeSecundaria: TextView
    private lateinit var quadroSocios: TextView
    private lateinit var btnSalvar: Button
    private var paramSalvar: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhe_cnpj_activity)

        iniVars()
        iniActions()
    }


    private fun iniVars() {
        context = this

        titulo = findViewById(R.id.tv_titulo)
        cnpj = findViewById(R.id.tv_cnpj)
        razaoSocial = findViewById(R.id.tv_razao_social)
        nomeFantasia = findViewById(R.id.tv_nome_fantasia)
        dataAbertura = findViewById(R.id.tv_data_abertura)
        tipo = findViewById(R.id.tv_tipo)
        situacao = findViewById(R.id.tv_situacao)
        naturezaJuridica = findViewById(R.id.tv_natureza_juridica)
        capitalSocial = findViewById(R.id.tv_capital_social)
        endereo = findViewById(R.id.tv_endereco)
        contato = findViewById(R.id.tv_contato)
        atividadePrincipal = findViewById(R.id.tv_atividade_principal)
        atividadeSecundaria = findViewById(R.id.tv_atividade_secundaria)
        quadroSocios = findViewById(R.id.tv_quadro_socios)
        btnSalvar = findViewById(R.id.btn_salvar)

        recuperaParametro()

        var cnpjQtd = DetalhesViewModel().verificaCnpj(
            context,
            DetalhesViewModel().limparMascara(cnpjSelecionado)
        )

        if (paramSalvar == 1 && cnpjQtd == 0)
            btnSalvar.visibility = View.VISIBLE
        else
            btnSalvar.visibility = View.GONE

        var objcnpjSelecionado = objCnpj.filter { it -> it.cnpj == cnpjSelecionado }
        preencherCampos(objcnpjSelecionado[0])
    }

    fun recuperaParametro() {
        val gson = Gson()
        val extras = intent.extras

        var objCnpjJson = extras?.getString("obj_cnpj").toString()
        cnpjSelecionado = extras?.getString("valor_cnpj").toString()
        paramSalvar = (extras?.getString("parametroSalvar").toString()).toInt()

        objCnpj = gson.fromJson(
            objCnpjJson,
            Array<CnpjEntity>::class.java
        )
    }

    private fun iniActions() {
        btnSalvar.setOnClickListener {

            try {
                var cnpjQtd = DetalhesViewModel().verificaCnpj(
                    context,
                    DetalhesViewModel().limparMascara(cnpjSelecionado)
                )

                if (cnpjQtd == 0) {
                    var objcnpjSelecionado = objCnpj.first { it -> it.cnpj == cnpjSelecionado }

                    objcnpjSelecionado.cnpj = DetalhesViewModel().limparMascara(cnpjSelecionado)

                    DetalhesViewModel().inserirCnpj(context, objcnpjSelecionado)
                    objcnpjSelecionado.id_cnpj =
                        DetalhesViewModel().retornaIdCnpj(
                            context,
                            objcnpjSelecionado.cnpj
                        )

                    DetalhesViewModel().inserirContato(context, objcnpjSelecionado)
                    DetalhesViewModel().inserirEndereco(context, objcnpjSelecionado)
                    DetalhesViewModel().inserirQsa(context, objcnpjSelecionado)
                    DetalhesViewModel().inserirAtividadePrimaria(context, objcnpjSelecionado)
                    DetalhesViewModel().inserirAtividadeSecundaria(context, objcnpjSelecionado)

                    Toast.makeText(context, "Salvo com sucesso", Toast.LENGTH_SHORT).show()
                } else
                    Toast.makeText(context, "CNPJ já cadastrado", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun preencherCampos(objCnpj: CnpjEntity?) {

        titulo.text = "${objCnpj?.nome} - ${objCnpj?.cnpj}"
        cnpj.text = "${cnpj.text}  ${objCnpj?.cnpj}"
        razaoSocial.text = "${razaoSocial.text}   ${objCnpj?.nome}"
        nomeFantasia.text = "${nomeFantasia.text} ${objCnpj?.fantasia}"
        dataAbertura.text = "${dataAbertura.text} ${objCnpj?.abertura}"
        tipo.text = "${tipo.text} ${objCnpj?.tipo}"
        situacao.text = "${situacao.text} ${objCnpj?.situacao}"
        naturezaJuridica.text = "${naturezaJuridica.text} ${objCnpj?.natureza_juridica}"
        capitalSocial.text = "${capitalSocial.text} ${objCnpj?.capital_social}"
        endereo.text =
            "${endereo.text} ${objCnpj?.logradouro}, ${objCnpj?.numero} " +
                    "${objCnpj?.complemento} - ${objCnpj?.cep} - " +
                    "${objCnpj?.bairro} - ${objCnpj?.municipio} - ${objCnpj?.uf}"
        contato.text = "${contato.text} ${objCnpj?.email} - ${objCnpj?.telefone}"

        var lista = arrayListOf<String>()

        for (i in objCnpj!!.atividade_principal!!.indices) {
            lista.add(objCnpj!!.atividade_principal!![i].text)
        }

        atividadePrincipal.text =
            "${atividadePrincipal.text}\n\n${DetalhesViewModel().concatenar(lista)}"

        lista = arrayListOf()

        for (i in objCnpj!!.atividades_secundarias!!.indices) {
            lista.add(objCnpj!!.atividades_secundarias!![i].text)
        }

        atividadeSecundaria.text =
            "${atividadeSecundaria.text}\n\n${DetalhesViewModel().concatenar(lista)}"

        lista = arrayListOf()

        for (i in objCnpj!!.qsa!!.indices) {
            lista.add(objCnpj!!.qsa!![i].nome)
        }

        quadroSocios.text =
            "${quadroSocios.text}\n\n${DetalhesViewModel().concatenar(lista)}"

        var teste = ""

    }

    private fun voltar() {
        val gson = Gson()
        val objCnpjJson = gson.toJson(objCnpj!!)

        val intent = Intent(context, Result::class.java)
        intent.putExtra("obj_cnpj", objCnpjJson)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        voltar()
        super.onBackPressed()
    }
}