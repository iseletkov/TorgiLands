package ru.psu.mobile.torgilands

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.psu.mobile.torgilands.services.CAdapterUUID
import ru.psu.mobile.torgilands.services.IServiceAPILands


class CApplication : Application()
{
    companion object{
        const val BASE_URL = "http://192.168.1.5:8080"

        val moshi: Moshi = Moshi.Builder()
            .add(CAdapterUUID())
    //        .add(BigDecimalAdapter()) // And all other adapters
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val logging = HttpLoggingInterceptor()
        // set your desired log level
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient
            .Builder()
            // add your other interceptors …
        // add logging as last interceptor
            .addInterceptor(logging)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(httpClient)
            .build()

        val apiService: IServiceAPILands = retrofit
            .create(IServiceAPILands::class.java)


//        var minioClient = MinioClient.builder()
//            .endpoint("http://192.168.1.102:50101")
//            .credentials("torgi", "torgi123")
//            .build()

    }
}