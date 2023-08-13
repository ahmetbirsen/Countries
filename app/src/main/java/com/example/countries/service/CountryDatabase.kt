package com.example.countries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.countries.model.Country

//Eğer veritabanına birden fazla model ekleyeceksek arrayofun içine yazabiliriz
//veritabanı ile ilgili işlemler, yeni fonksiyonlar güncellemeler vs olursa versionu arrtır. İlk olarak 1 den başlar
@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase : RoomDatabase(){

      abstract fun countryDao() : CountryDao

      //Singleton
      //companion --> her yerden erişim
      companion object{
          //Volatile farklı threadlere görünür hale gelir. (coroutineler)
           @Volatile private var instance : CountryDatabase? = null

          //Senkronize işlem kitlenecek mi kitlenmeyecek mi
           private val lock = Any()

          //instance var mı yok mu bakar, yok ise senkronize bir şekilde bu instance ulaşılması sağlanır
          //synchronized -> farlı threaler bu objeye erişmeye çalışırsa sırayla yap. Aynı anda ulaşmasını engeller
           operator fun invoke(context : Context) = instance ?: synchronized(lock) {
                instance ?: makeDatabase(context).also {
                    instance = it
                }
           }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            //application context kullanmanın nedeni eğer olur da activity veya fragment ile ilgili bir destroy
            //işlemi olursa uygulama çökmesin, veritabnını etkilemesin diye direk applicationun kendi contextini alıyoruz
        context.applicationContext,CountryDatabase::class.java,"countryDatabase"
          ).build()

      }


}