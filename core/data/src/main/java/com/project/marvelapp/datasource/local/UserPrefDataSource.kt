package com.project.marvelapp.datasource.local

import android.content.SharedPreferences
import com.project.marvelapp.model.vo.CharacterVO

interface UserPrefDataSource {
    val prefs: SharedPreferences
    var favoriteCharacterSets: HashSet<CharacterVO>
}