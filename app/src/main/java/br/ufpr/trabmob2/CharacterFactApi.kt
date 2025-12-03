package br.ufpr.trabmob2

import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterFactApi {
    @GET("characters/house/{house}")
    suspend fun getStudentsByHouse(@Path("house") house: String): List<CharacterFact>
    @GET("character/{id}")
    suspend fun findCharacterById(@Path("id") id: String): List<CharacterFact>
    @GET("characters")
    suspend fun getCharacters(): List<CharacterFact>
    @GET("characters/staff")
    suspend fun getTeachers(): List<CharacterFact>
}