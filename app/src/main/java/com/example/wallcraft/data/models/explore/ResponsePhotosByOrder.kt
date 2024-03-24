package com.example.wallcraft.data.models.explore


import com.google.gson.annotations.SerializedName

class ResponsePhotosByOrder : ArrayList<ResponsePhotosByOrder.ResponsePhotosByOrderItem>(){
    data class ResponsePhotosByOrderItem(
        @SerializedName("alt_description")
        val altDescription: String?, // a person is walking in the desert at sunset
        @SerializedName("alternative_slugs")
        val alternativeSlugs: AlternativeSlugs?,
        @SerializedName("asset_type")
        val assetType: String?, // photo
        @SerializedName("blur_hash")
        val blurHash: String?, // L%E{-2M{sln%.TIVaeayx^oJWBj[
        @SerializedName("breadcrumbs")
        val breadcrumbs: List<Any?>?,
        @SerializedName("color")
        val color: String?, // #262626
        @SerializedName("created_at")
        val createdAt: String?, // 2024-02-07T22:34:15Z
        @SerializedName("current_user_collections")
        val currentUserCollections: List<Any?>?,
        @SerializedName("description")
        val description: String?, // Mesmerizing red sand dunes and endless mountain valleys providing iconic views across NEOM | NEOM, Saudi Arabia
        @SerializedName("height")
        val height: Int?, // 5792
        @SerializedName("id")
        val id: String?, // STV2s3FYw7Y
        @SerializedName("liked_by_user")
        val likedByUser: Boolean?, // false
        @SerializedName("likes")
        val likes: Int?, // 130
        @SerializedName("links")
        val links: Links?,
        @SerializedName("promoted_at")
        val promotedAt: String?, // 2017-03-24T09:56:57Z
        @SerializedName("slug")
        val slug: String?, // a-person-is-walking-in-the-desert-at-sunset-STV2s3FYw7Y
        @SerializedName("sponsorship")
        val sponsorship: Sponsorship?,
        @SerializedName("topic_submissions")
        val topicSubmissions: TopicSubmissions?,
        @SerializedName("updated_at")
        val updatedAt: String?, // 2024-03-22T22:19:00Z
        @SerializedName("urls")
        val urls: Urls?,
        @SerializedName("user")
        val user: User?,
        @SerializedName("width")
        val width: Int? // 8688
    ) {
        data class AlternativeSlugs(
            @SerializedName("de")
            val de: String?, // eine-person-geht-bei-sonnenuntergang-in-der-wuste-spazieren-STV2s3FYw7Y
            @SerializedName("en")
            val en: String?, // a-person-is-walking-in-the-desert-at-sunset-STV2s3FYw7Y
            @SerializedName("es")
            val es: String?, // una-persona-camina-por-el-desierto-al-atardecer-STV2s3FYw7Y
            @SerializedName("fr")
            val fr: String?, // une-personne-marche-dans-le-desert-au-coucher-du-soleil-STV2s3FYw7Y
            @SerializedName("it")
            val `it`: String?, // una-persona-sta-camminando-nel-deserto-al-tramonto-STV2s3FYw7Y
            @SerializedName("ja")
            val ja: String?, // 夕暮れ時の砂漠を歩いている人-STV2s3FYw7Y
            @SerializedName("ko")
            val ko: String?, // 해질녘-사막을-걷고-있는-사람-STV2s3FYw7Y
            @SerializedName("pt")
            val pt: String? // uma-pessoa-esta-andando-no-deserto-ao-por-do-sol-STV2s3FYw7Y
        )
    
        data class Links(
            @SerializedName("download")
            val download: String?, // https://unsplash.com/photos/STV2s3FYw7Y/download?ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8MXx8MTcxMTE4NTk4MXw
            @SerializedName("download_location")
            val downloadLocation: String?, // https://api.unsplash.com/photos/STV2s3FYw7Y/download?ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8MXx8MTcxMTE4NTk4MXw
            @SerializedName("html")
            val html: String?, // https://unsplash.com/photos/a-person-is-walking-in-the-desert-at-sunset-STV2s3FYw7Y
            @SerializedName("self")
            val self: String? // https://api.unsplash.com/photos/a-person-is-walking-in-the-desert-at-sunset-STV2s3FYw7Y
        )
    
        data class Sponsorship(
            @SerializedName("impression_urls")
            val impressionUrls: List<String?>?,
            @SerializedName("sponsor")
            val sponsor: Sponsor?,
            @SerializedName("tagline")
            val tagline: String?, // Made to Change
            @SerializedName("tagline_url")
            val taglineUrl: String? // https://www.neom.com/en-us?utm_source=unsplash&utm_medium=referral
        ) {
            data class Sponsor(
                @SerializedName("accepted_tos")
                val acceptedTos: Boolean?, // true
                @SerializedName("bio")
                val bio: String?, // Located in the northwest of Saudi Arabia, NEOM’s diverse climate offers both sun-soaked beaches and snow-capped mountains. NEOM’s unique location will provide residents with enhanced livability while protecting 95% of the natural landscape.
                @SerializedName("first_name")
                val firstName: String?, // NEOM
                @SerializedName("for_hire")
                val forHire: Boolean?, // false
                @SerializedName("id")
                val id: String?, // mYizSrdJkkU
                @SerializedName("instagram_username")
                val instagramUsername: String?, // discoverneom
                @SerializedName("last_name")
                val lastName: Any?, // null
                @SerializedName("links")
                val links: Links?,
                @SerializedName("location")
                val location: String?, // NEOM, Saudi Arabia
                @SerializedName("name")
                val name: String?, // NEOM
                @SerializedName("portfolio_url")
                val portfolioUrl: String?, // http://www.neom.com
                @SerializedName("profile_image")
                val profileImage: ProfileImage?,
                @SerializedName("social")
                val social: Social?,
                @SerializedName("total_collections")
                val totalCollections: Int?, // 7
                @SerializedName("total_likes")
                val totalLikes: Int?, // 1
                @SerializedName("total_photos")
                val totalPhotos: Int?, // 222
                @SerializedName("total_promoted_photos")
                val totalPromotedPhotos: Int?, // 72
                @SerializedName("twitter_username")
                val twitterUsername: String?, // neom
                @SerializedName("updated_at")
                val updatedAt: String?, // 2024-03-22T18:27:08Z
                @SerializedName("username")
                val username: String? // neom
            ) {
                data class Links(
                    @SerializedName("followers")
                    val followers: String?, // https://api.unsplash.com/users/neom/followers
                    @SerializedName("following")
                    val following: String?, // https://api.unsplash.com/users/neom/following
                    @SerializedName("html")
                    val html: String?, // https://unsplash.com/@neom
                    @SerializedName("likes")
                    val likes: String?, // https://api.unsplash.com/users/neom/likes
                    @SerializedName("photos")
                    val photos: String?, // https://api.unsplash.com/users/neom/photos
                    @SerializedName("portfolio")
                    val portfolio: String?, // https://api.unsplash.com/users/neom/portfolio
                    @SerializedName("self")
                    val self: String? // https://api.unsplash.com/users/neom
                )
    
                data class ProfileImage(
                    @SerializedName("large")
                    val large: String?, // https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
                    @SerializedName("medium")
                    val medium: String?, // https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
                    @SerializedName("small")
                    val small: String? // https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
                )
    
                data class Social(
                    @SerializedName("instagram_username")
                    val instagramUsername: String?, // discoverneom
                    @SerializedName("paypal_email")
                    val paypalEmail: Any?, // null
                    @SerializedName("portfolio_url")
                    val portfolioUrl: String?, // http://www.neom.com
                    @SerializedName("twitter_username")
                    val twitterUsername: String? // neom
                )
            }
        }
    
        data class TopicSubmissions(
            @SerializedName("blue")
            val blue: Blue?,
            @SerializedName("monochromatic")
            val monochromatic: Monochromatic?,
            @SerializedName("nature")
            val nature: Nature?,
            @SerializedName("textures-patterns")
            val texturesPatterns: TexturesPatterns?,
            @SerializedName("wallpapers")
            val wallpapers: Wallpapers?
        ) {
            data class Blue(
                @SerializedName("approved_on")
                val approvedOn: String?, // 2023-07-31T10:03:30Z
                @SerializedName("status")
                val status: String? // approved
            )
    
            data class Monochromatic(
                @SerializedName("approved_on")
                val approvedOn: String?, // 2023-09-01T19:28:23Z
                @SerializedName("status")
                val status: String? // approved
            )
    
            data class Nature(
                @SerializedName("approved_on")
                val approvedOn: String?, // 2020-04-06T14:20:12Z
                @SerializedName("status")
                val status: String? // approved
            )
    
            data class TexturesPatterns(
                @SerializedName("approved_on")
                val approvedOn: String?, // 2020-04-06T14:20:11Z
                @SerializedName("status")
                val status: String? // approved
            )
    
            data class Wallpapers(
                @SerializedName("approved_on")
                val approvedOn: String?, // 2023-12-13T14:37:30Z
                @SerializedName("status")
                val status: String? // rejected
            )
        }
    
        data class Urls(
            @SerializedName("full")
            val full: String?, // https://images.unsplash.com/photo-1707344088547-3cf7cea5ca49?crop=entropy&cs=srgb&fm=jpg&ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8MXx8MTcxMTE4NTk4MXw&ixlib=rb-4.0.3&q=85
            @SerializedName("raw")
            val raw: String?, // https://images.unsplash.com/photo-1707344088547-3cf7cea5ca49?ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8MXx8MTcxMTE4NTk4MXw&ixlib=rb-4.0.3
            @SerializedName("regular")
            val regular: String?, // https://images.unsplash.com/photo-1707344088547-3cf7cea5ca49?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8MXx8MTcxMTE4NTk4MXw&ixlib=rb-4.0.3&q=80&w=1080
            @SerializedName("small")
            val small: String?, // https://images.unsplash.com/photo-1707344088547-3cf7cea5ca49?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8MXx8MTcxMTE4NTk4MXw&ixlib=rb-4.0.3&q=80&w=400
            @SerializedName("small_s3")
            val smallS3: String?, // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1707344088547-3cf7cea5ca49
            @SerializedName("thumb")
            val thumb: String? // https://images.unsplash.com/photo-1707344088547-3cf7cea5ca49?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8MXx8MTcxMTE4NTk4MXw&ixlib=rb-4.0.3&q=80&w=200
        )
    
        data class User(
            @SerializedName("accepted_tos")
            val acceptedTos: Boolean?, // true
            @SerializedName("bio")
            val bio: String?, // Located in the northwest of Saudi Arabia, NEOM’s diverse climate offers both sun-soaked beaches and snow-capped mountains. NEOM’s unique location will provide residents with enhanced livability while protecting 95% of the natural landscape.
            @SerializedName("first_name")
            val firstName: String?, // NEOM
            @SerializedName("for_hire")
            val forHire: Boolean?, // false
            @SerializedName("id")
            val id: String?, // mYizSrdJkkU
            @SerializedName("instagram_username")
            val instagramUsername: String?, // discoverneom
            @SerializedName("last_name")
            val lastName: String?, // Deckers
            @SerializedName("links")
            val links: Links?,
            @SerializedName("location")
            val location: String?, // NEOM, Saudi Arabia
            @SerializedName("name")
            val name: String?, // NEOM
            @SerializedName("portfolio_url")
            val portfolioUrl: String?, // http://www.neom.com
            @SerializedName("profile_image")
            val profileImage: ProfileImage?,
            @SerializedName("social")
            val social: Social?,
            @SerializedName("total_collections")
            val totalCollections: Int?, // 7
            @SerializedName("total_likes")
            val totalLikes: Int?, // 1
            @SerializedName("total_photos")
            val totalPhotos: Int?, // 222
            @SerializedName("total_promoted_photos")
            val totalPromotedPhotos: Int?, // 72
            @SerializedName("twitter_username")
            val twitterUsername: String?, // neom
            @SerializedName("updated_at")
            val updatedAt: String?, // 2024-03-22T18:27:08Z
            @SerializedName("username")
            val username: String? // neom
        ) {
            data class Links(
                @SerializedName("followers")
                val followers: String?, // https://api.unsplash.com/users/neom/followers
                @SerializedName("following")
                val following: String?, // https://api.unsplash.com/users/neom/following
                @SerializedName("html")
                val html: String?, // https://unsplash.com/@neom
                @SerializedName("likes")
                val likes: String?, // https://api.unsplash.com/users/neom/likes
                @SerializedName("photos")
                val photos: String?, // https://api.unsplash.com/users/neom/photos
                @SerializedName("portfolio")
                val portfolio: String?, // https://api.unsplash.com/users/neom/portfolio
                @SerializedName("self")
                val self: String? // https://api.unsplash.com/users/neom
            )
    
            data class ProfileImage(
                @SerializedName("large")
                val large: String?, // https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
                @SerializedName("medium")
                val medium: String?, // https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
                @SerializedName("small")
                val small: String? // https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
            )
    
            data class Social(
                @SerializedName("instagram_username")
                val instagramUsername: String?, // discoverneom
                @SerializedName("paypal_email")
                val paypalEmail: Any?, // null
                @SerializedName("portfolio_url")
                val portfolioUrl: String?, // http://www.neom.com
                @SerializedName("twitter_username")
                val twitterUsername: String? // neom
            )
        }
    }
}