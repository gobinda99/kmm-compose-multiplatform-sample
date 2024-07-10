package com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomUserResponse(

    @SerialName("results") var results: ArrayList<RandomUser> = arrayListOf(),
    @SerialName("info") var info: Info? = Info()

)

@Serializable
data class RandomUser(

    @SerialName("gender") var gender: String? = null,
    @SerialName("name") var name: Name? = Name(),
    @SerialName("location") var location: Location? = Location(),
    @SerialName("email") var email: String? = null,
    @SerialName("login") var login: Login? = Login(),
    @SerialName("dob") var dob: Dob? = Dob(),
    @SerialName("registered") var registered: Registered? = Registered(),
    @SerialName("phone") var phone: String? = null,
    @SerialName("cell") var cell: String? = null,
    @SerialName("id") var id: Id? = Id(),
    @SerialName("picture") var picture: Picture? = Picture(),
    @SerialName("nat") var nat: String? = null

)

@Serializable
data class Info(

    @SerialName("seed") var seed: String? = null,
    @SerialName("results") var results: Int? = null,
    @SerialName("page") var page: Int? = null,
    @SerialName("version") var version: String? = null

)

@Serializable
data class Name(

    @SerialName("title") var title: String? = null,
    @SerialName("first") var first: String? = null,
    @SerialName("last") var last: String? = null

)

@Serializable
data class Street(

    @SerialName("number") var number: Int? = null,
    @SerialName("name") var name: String? = null

)

@Serializable
data class Coordinates(

    @SerialName("latitude") var latitude: String? = null,
    @SerialName("longitude") var longitude: String? = null

)

@Serializable
data class Timezone(

    @SerialName("offset") var offset: String? = null,
    @SerialName("description") var description: String? = null

)

@Serializable
data class Location(

    @SerialName("street") var street: Street? = Street(),
    @SerialName("city") var city: String? = null,
    @SerialName("state") var state: String? = null,
    @SerialName("country") var country: String? = null,
    @SerialName("postcode") var postcode: String? = null,
    @SerialName("coordinates") var coordinates: Coordinates? = Coordinates(),
    @SerialName("timezone") var timezone: Timezone? = Timezone()

)

@Serializable
data class Dob(

    @SerialName("date") var date: String? = null,
    @SerialName("age") var age: Int? = null

)

@Serializable
data class Login(

    @SerialName("uuid") var uuid: String? = null,
    @SerialName("username") var username: String? = null,
    @SerialName("password") var password: String? = null,
    @SerialName("salt") var salt: String? = null,
    @SerialName("md5") var md5: String? = null,
    @SerialName("sha1") var sha1: String? = null,
    @SerialName("sha256") var sha256: String? = null

)


@Serializable
data class Registered(

    @SerialName("date") var date: String? = null,
    @SerialName("age") var age: Int? = null

)

@Serializable
data class Id(

    @SerialName("name") var name: String? = null,
    @SerialName("value") var value: String? = null

)

@Serializable
data class Picture(

    @SerialName("large") var large: String? = null,
    @SerialName("medium") var medium: String? = null,
    @SerialName("thumbnail") var thumbnail: String? = null

)
