package br.com.ricardo.developer.cultuascnpj.view.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import br.com.ricardo.developer.cultuascnpj.R
import br.com.ricardo.developer.cultuascnpj.adapters.TelasAdapter
import br.com.ricardo.developer.cultuascnpj.view.fragment.Consulta
import br.com.ricardo.developer.cultuascnpj.view.fragment.Salvos
import com.google.android.material.tabs.TabLayout

class TelaPrincipal : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var fm: FragmentManager
    private lateinit var consulta: Consulta
    private lateinit var salvos: Salvos
    private lateinit var lista: ArrayList<Fragment>
    private lateinit var vp: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_principal_activity)
        iniVars()
    }

    private fun iniVars() {
        context = this
        fm = supportFragmentManager
        vp = findViewById(R.id.vp)
        tabs = findViewById(R.id.tabs)
        montarListas()
    }

    private fun montarListas() {

        consulta = Consulta()
        salvos = Salvos()

        lista = ArrayList()
        lista.add(consulta)
        lista.add(salvos)

        vp.adapter = TelasAdapter(
            supportFragmentManager,
            lista
        )

        tabs.setupWithViewPager(vp)

        configTabIcons()
    }

    fun configTabIcons() {

        val tab01 = LayoutInflater.from(context).inflate(R.layout.custom_tab, null) as TextView
        tab01.text = getString(R.string.consulta)
        tabs.getTabAt(0)?.customView = tab01

        val tab02 = LayoutInflater.from(context).inflate(R.layout.custom_tab, null) as TextView
        tab02.text = getString(R.string.item_salvo)
        tabs.getTabAt(1)?.customView = tab02

    }
}