package com.example.wallcraft.data.models.home


import com.google.gson.annotations.SerializedName

class ResponsePhotos : ArrayList<ResponsePhotos.responsePhotosItem>(){
    data class responsePhotosItem(
        @SerializedName("alt_description")
        val altDescription: String?, // a desert landscape with mountains in the distance
        @SerializedName("blur_hash")
        val blurHash: String?, // LhHdd9Ipbcn$%#aexaRk~CRkkCWC
        @SerializedName("breadcrumbs")
        val breadcrumbs: List<Any?>?,
        @SerializedName("color")
        val color: String?, // #c07340
        @SerializedName("created_at")
        val createdAt: String?, // 2023-04-28T12:59:36Z
        @SerializedName("current_user_collections")
        val currentUserCollections: List<Any?>?,
        @SerializedName("description")
        val description: String?, // Nature Reserve - NEOM, Saudi Arabia | The NEOM Nature Reserve region is being designed to deliver protection and restoration of biodiversity across 95% of NEOM.
        @SerializedName("height")
        val height: Int?, // 5676
        @SerializedName("id")
        val id: String?, // SGZ5DkDOoRo
        @SerializedName("liked_by_user")
        val likedByUser: Boolean?, // false
        @SerializedName("likes")
        val likes: Int?, // 446
        @SerializedName("links")
        val links: Links?,
        @SerializedName("promoted_at")
        val promotedAt: String?, // 2024-01-27T10:36:41Z
        @SerializedName("slug")
        val slug: String?, // a-desert-landscape-with-mountains-in-the-distance-SGZ5DkDOoRo
        @SerializedName("sponsorship")
        val sponsorship: Sponsorship?,
        @SerializedName("topic_submissions")
        val topicSubmissions: TopicSubmissions?,
        @SerializedName("updated_at")
        val updatedAt: String?, // 2024-01-24T18:47:52Z
        @SerializedName("urls")
        val urls: Urls?,
        @SerializedName("user")
        val user: User?,
        @SerializedName("width")
        val width: Int? // 8513
    ) {
        data class Links(
            @SerializedName("download")
            val download: String?, // https://unsplash.com/photos/SGZ5DkDOoRo/download?ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNjM1MjU3Nnw
            @SerializedName("download_location")
            val downloadLocation: String?, // https://api.unsplash.com/photos/SGZ5DkDOoRo/download?ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNjM1MjU3Nnw
            @SerializedName("html")
            val html: String?, // https://unsplash.com/photos/a-desert-landscape-with-mountains-in-the-distance-SGZ5DkDOoRo
            @SerializedName("self")
            val self: String? // https://api.unsplash.com/photos/a-desert-landscape-with-mountains-in-the-distance-SGZ5DkDOoRo
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
                val totalPhotos: Int?, // 202
                @SerializedName("total_promoted_photos")
                val totalPromotedPhotos: Int?, // 72
                @SerializedName("twitter_username")
                val twitterUsername: String?, // neom
                @SerializedName("updated_at")
                val updatedAt: String?, // 2024-01-26T12:01:55Z
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
            @SerializedName("archival")
            val archival: Archival?,
            @SerializedName("fashion-beauty")
            val fashionBeauty: FashionBeauty?,
            @SerializedName("minimalism")
            val minimalism: Minimalism?,
            @SerializedName("nature")
            val nature: Nature?,
            @SerializedName("wallpapers")
            val wallpapers: Wallpapers?
        ) {
            data class Archival(
                @SerializedName("approved_on")
                val approvedOn: String?, // 2024-01-27T09:41:33Z
                @SerializedName("status")
                val status: String? // approved
            )
    
            data class FashionBeauty(
                @SerializedName("approved_on")
                val approvedOn: String?, // 2020-04-06T14:20:19Z
                @SerializedName("status")
                val status: String? // approved
            )
    
            data class Minimalism(
                @SerializedName("status")
                val status: String? // unevaluated
            )
    
            data class Nature(
                @SerializedName("status")
                val status: String? // unevaluated
            )
    
            data class Wallpapers(
                @SerializedName("approved_on")
                val approvedOn: String?, // 2023-05-04T12:04:08Z
                @SerializedName("status")
                val status: String? // approved
            )
        }
    
        data class Urls(
            @SerializedName("full")
            val full: String?, // https://images.unsplash.com/photo-1682686581362-796145f0e123?crop=entropy&cs=srgb&fm=jpg&ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNjM1MjU3Nnw&ixlib=rb-4.0.3&q=85
            @SerializedName("raw")
            val raw: String?, // https://images.unsplash.com/photo-1682686581362-796145f0e123?ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNjM1MjU3Nnw&ixlib=rb-4.0.3
            @SerializedName("regular")
            val regular: String?, // https://images.unsplash.com/photo-1682686581362-796145f0e123?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNjM1MjU3Nnw&ixlib=rb-4.0.3&q=80&w=1080
            @SerializedName("small")
            val small: String?, // https://images.unsplash.com/photo-1682686581362-796145f0e123?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNjM1MjU3Nnw&ixlib=rb-4.0.3&q=80&w=400
            @SerializedName("small_s3")
            val smallS3: String?, // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1682686581362-796145f0e123
            @SerializedName("thumb")
            val thumb: String? // https://images.unsplash.com/photo-1682686581362-796145f0e123?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NTg4MTZ8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNjM1MjU3Nnw&ixlib=rb-4.0.3&q=80&w=200
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
            val lastName: String?, // Ling
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
            val totalPhotos: Int?, // 202
            @SerializedName("total_promoted_photos")
            val totalPromotedPhotos: Int?, // 72
            @SerializedName("twitter_username")
            val twitterUsername: String?, // neom
            @SerializedName("updated_at")
            val updatedAt: String?, // 2024-01-26T12:01:55Z
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