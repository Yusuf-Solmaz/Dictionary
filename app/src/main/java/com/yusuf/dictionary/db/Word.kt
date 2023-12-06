package com.yusuf.dictionary.db

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Word(var kelime_id: String? = "",var ingilizce:String? = "", var turkce:String? = ""): Serializable
