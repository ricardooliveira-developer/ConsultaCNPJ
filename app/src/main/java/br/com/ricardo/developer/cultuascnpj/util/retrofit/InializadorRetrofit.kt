package br.com.ricardo.developer.cultuascnpj.util.retrofit

import br.com.ricardo.developer.cultuascnpj.interfaces.ICnpj
import br.com.ricardo.developer.cultuascnpj.util.api.ToolBoxInfra
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InializadorRetrofit {

    private val client = OkHttpClient.Builder().build()

    private var retrofit = Retrofit.Builder().baseUrl(ToolBoxInfra.URL_BASE)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        ).client(client).build()

    fun retornaInterfaceCnpj(): ICnpj {
        return retrofit.create(ICnpj::class.java)
    }
}