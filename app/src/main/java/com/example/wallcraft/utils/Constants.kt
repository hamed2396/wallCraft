package com.example.wallcraft.utils

object Constants {
    const val BASE_URL = "https://api.unsplash.com/"
    const val CONNECTION_TIME_OUT = 60L
    const val PING_INTERVAL = 3L
    const val PING_NAMED = "ping_named"
    const val API_KEY = "djnw4RS8kpPz83FdIeeNasojrfOM7TGge7RC1lxynPQ"
    const val AUTHORIZATION = "Authorization"
    const val ERROR_400 = "The request was unacceptable, often due to missing a required parameter"
    const val ERROR_401 = "Invalid Access Token"
    const val ERROR_403 = "Missing permissions to perform request"
    const val ERROR_404 = "The requested resource doesn’t exist"
    const val ERROR_500 = "Something went wrong on our end"
    const val CHECK_CONNECTION = "Please Check Your Connection"
    const val CLIENT_ID = "Client-ID"
    const val DEFAULT_PHOTO_ID = "bo8jQKTaE0Y"

    //db
    const val FAVORITE_TABLE = "favorite_table"
    const val DB_NAME = "db_name"

    //other
    const val WALLPAPERS = "Wallpapers"
    var HOME_CHIP = DEFAULT_PHOTO_ID
    var PAGER_POSITION=0
    const val IMAGE_MIME_TYPE = "image/jpeg"
    const val JPG = ".jpg"
    const val OLDEST = "oldest"
    const val LATEST = "latest"
    const val POPULAR = "popular"
    var TITLE_ORDER="latest"

}
