package com.example.graphql_example

import com.google.gson.annotations.SerializedName

data class CustomObject(
    @SerializedName("data") val userData: UserData
)
