package com.cep.cep_podcast.data.vos

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class LookingForVO(
    var cohosts: Boolean? = false,
    var cross_promotion: Boolean? = false,
    var guests: Boolean? = false,
    var sponsors: Boolean? = false
)