package com.base.ecommerce.model

import java.io.Serializable
import javax.persistence.*

@Entity(name = "image")
data class Image(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int? = 0,

    @Lob
    @Column(name="profile_picture")
    var profilePicture: ByteArray? = null,

    @Lob
    var pictureAlbums:ByteArray? = null,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product? = null


):Serializable{

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        if (id != other.id) return false
        if (profilePicture != null) {
            if (other.profilePicture == null) return false
            if (!profilePicture.contentEquals(other.profilePicture)) return false
        } else if (other.profilePicture != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (profilePicture?.contentHashCode() ?: 0)
        return result
    }


}

