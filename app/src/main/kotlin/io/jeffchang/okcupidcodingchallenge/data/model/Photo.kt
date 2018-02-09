package io.jeffchang.okcupidcodingchallenge.data.model

import android.arch.persistence.room.Embedded
import com.squareup.moshi.Json

/**
 * A series of models relevant to an user's picture
 * and the various ways that is stored for different devices.
 */


data class Photo(
        @Json(name = "base_path") var basePath: String?,
        @Json(name = "ordinal") var ordinal: Int?,
        @Json(name = "id") var id: String?,
        @Json(name = "caption") var caption: String?,
        @Embedded(prefix = "fullpath_")
        @Json(name = "full_paths") var fullPaths: FullPaths?,
        @Embedded(prefix = "original_")
        @Json(name = "original_size") var originalSize: OriginalSize?,
        @Embedded(prefix = "thumbpath_")
        @Json(name = "thumb_paths") var thumbPaths: ThumbPaths?
)

data class OriginalSize(
        @Json(name = "width") var width: Int?,
        @Json(name = "height") var height: Int?
)

data class CropRect(
        @Json(name = "height") var height: Int?,
        @Json(name = "y") var y: Int?,
        @Json(name = "width") var width: Int?,
        @Json(name = "x") var x: Int?
)

data class ThumbPaths(
        @Json(name = "large") var large: String?,
        @Json(name = "desktop_match") var desktopMatch: String?,
        @Json(name = "small") var small: String?,
        @Json(name = "medium") var medium: String?
)

data class FullPaths(
        @Json(name = "large") var large: String?,
        @Json(name = "small") var small: String?,
        @Json(name = "medium") var medium: String?,
        @Json(name = "original") var original: String?
)