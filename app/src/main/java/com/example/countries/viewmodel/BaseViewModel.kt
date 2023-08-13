package com.example.countries.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

//AndroidViewModel 'i extend ettik çünkü bu bir context parametresi alıyor ve ben bu parametreye
//aplicationun genel contextini vererek tüm uygulamada kullanabiliri. Fragmenta veya activity'e özgü değil
//Çünkü acitvitty veya fragment destroy olursa uygulama çöker.Böylelikle daha güvenli bir sınıf yapısı olur.
abstract class BaseViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    //Arka planda oluşan bir işi tanımlama
    private val job = Job()

    override val coroutineContext: CoroutineContext
                //Önce işini yap sonra main thread ' e dön demektir
        get() = job + Dispatchers.Main


    override fun onCleared() {
        super.onCleared()
        //Eğer ki bir şey destroy olursa veya app kapatılırsa işi iptal et
        job.cancel()
    }
}