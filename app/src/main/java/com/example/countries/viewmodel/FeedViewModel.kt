package com.example.countries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.Country
import com.example.countries.service.CountryAPIService
import com.example.countries.service.CountryDatabase
import com.example.countries.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        countryLoading.value = true

        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {

                        storeInSQLite(t)
                    }
                    override fun onError(e: Throwable) {

                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }
                })
           )
       }

    private fun showCountries ( countryList : List<Country>) {
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

    private fun storeInSQLite(list : List<Country>) {
        //Yeni bir coroutine oluşturur.
        //Güncel olan thread ne ise onu bloklamaz
        //bir job'a ihtiyaç duyar
        //job iptal olursa buradaki coroutine de iptal olur
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()

            //insertAll fonksiyonu eklenen verinini primary key lerini dönderecek
                                            //Listeyi tekil elemanlar haline getirir
            val listLong = dao.insertAll(*list.toTypedArray())// -> list -> individual
            var i = 0
            while ( i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i = i + 1
            }
            showCountries(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }

    //Fragment silinince yok olunca disposablelları kaldırır ve hafızayı verimli hale getirir
    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}


























