package com.example.graphql_example

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("user") val user: User
)

