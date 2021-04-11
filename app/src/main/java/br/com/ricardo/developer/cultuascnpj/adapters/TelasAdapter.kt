package br.com.ricardo.developer.cultuascnpj.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class TelasAdapter(
        fragmentManager: FragmentManager,
        val lista: ArrayList<Fragment>
) : FragmentPagerAdapter(fragmentManager) {

    // devolver qual o fragmento referenciado pelo parametro position
    override fun getItem(position: Int): Fragment {
        return lista[position]
    }

    // devolve a quantidade de fragmentos no meu viewpager
    override fun getCount(): Int {
        return lista.size
    }
}