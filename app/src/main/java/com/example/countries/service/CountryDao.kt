package com.example.countries.service

import androidx.room.Insert
import androidx.room.Dao
import androidx.room.Query
import com.example.countries.model.Country

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long> //modelde tanımladığın uniq id leri dönderir
    //Tanımladığın class ne ise table adı o olur
    //Insert -> INSERT INTO
    //suspend -> coroutine, pause & resume
    //vararg -> multiple country objects
    //List<Long> -> primary keys

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    //table adını belirtiriz çünkü ekstradan koşul işlemleri ekliyoruz
    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}