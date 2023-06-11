package com.project.marvelapp.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.marvelapp.model.vo.CharacterVO
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserPrefDataSourceImpl @Inject constructor(
    @ApplicationContext context: Context
): UserPrefDataSource {

    private val _prefs: Lazy<SharedPreferences> = lazy {
        context.getSharedPreferences("marvelApp", Context.MODE_PRIVATE)
    }

    override val prefs: SharedPreferences
        get() = _prefs.value

    override var favoriteCharacterSets: HashSet<CharacterVO>
        get() {
            return try {
                val json = checkNotNull(_prefs.value.getString(CHARACTER_FAVORITE, null))
                Gson().fromTypeJson(json) as HashSet<CharacterVO>
            } catch (e: Exception) {
                hashSetOf()
            }
        }
        set(value) {
            val jsonString = Gson().toJson(value)
            _prefs.value.edit().putString(CHARACTER_FAVORITE, jsonString).apply()
        }

    companion object {
        const val CHARACTER_FAVORITE = "character_favorite"
    }

    private inline fun <reified T> Gson.fromTypeJson(json: String): T =
        fromJson(json, object : TypeToken<T>() {}.type)
}