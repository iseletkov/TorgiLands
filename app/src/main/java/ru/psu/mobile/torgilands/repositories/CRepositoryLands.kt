package ru.psu.mobile.torgilands.repositories

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.psu.mobile.torgilands.api.IAPIDummy
import ru.psu.mobile.torgilands.db.CDatabase
import ru.psu.mobile.torgilands.model.CEmployee
import ru.psu.mobile.torgilands.model.CLand
import java.util.UUID

class CRepositoryLands(
    application                         : Application
)
{
    companion object{
        private const val BASE_URL = "https://dummy.restapiexample.com/api/v1/"
    }
    private val db                          = CDatabase.getDatabase(application)
    private val daoLands                    = db.daoLands()

        private val moshi: Moshi = Moshi.Builder()
//        .add(UuidAdapter())
//        .add(BigDecimalAdapter()) // And all other adapters
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val logging = HttpLoggingInterceptor()
        // set your desired log level
        .setLevel(HttpLoggingInterceptor.Level.BODY)


    private val httpClient = OkHttpClient
        .Builder()
        // add your other interceptors …
        // add logging as last interceptor
        .addInterceptor(logging)
        .build()


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    private var apiService: IAPIDummy = retrofit
        .create(IAPIDummy::class.java)

    fun getEmployees() : Flow<CEmployee>
    {
        return flow{
            emit (
                apiService
                    .getEmployeeById(2)
            )
        }
            .map { response ->
                response.data
            }
            .flowOn(Dispatchers.IO)
    }

    fun getAll()                            : Flow<List<CLand>>
    {
        //Есть ли данные локально?
        //Спросить у сервера
        //Получить ответ от сервера
        //Сохранить новые данные локально
        //Взять из локального источника актуальные данные
        //return daoLands.getByHeaderAndPrice("123", 123.0)
        return daoLands.getAll()
    }

    fun getById(
        id : UUID
    ) : Flow<CLand?>
    {
        return daoLands.getById(id)
    }

    suspend fun insert(
        land: CLand
    )
    {
        daoLands.insert(land)
    }

    suspend fun delete(
        land: CLand
    )
    {
        daoLands.delete(land)
    }
}