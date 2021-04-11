package br.com.ricardo.developer.cultuascnpj.interfaces


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ICnpj {

    @GET("{cnpj}")
    fun getCnpj(@Path("cnpj") cnpj: String): Call<ResponseBody>
}