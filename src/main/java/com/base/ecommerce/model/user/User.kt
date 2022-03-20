package com.base.ecommerce.model.user

import javax.persistence.*

@Entity(name = "user_information")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: String,
    val phone: String,
    val website: String,
    val company: String,
    val password: String,
    @Column(name ="enabled")
    var enabled: Boolean,
    @javax.persistence.Transient
    val role: List<String>,
    ) {

    data class Builder(
        var id: Int = 0,
        var name: String = "",
        var username: String = "",
        var email: String = "",
        var address: String = "",
        var phone: String = "",
        var website: String = "",
        var company: String = "",
        var password: String = "",
        var role: List<String> = listOf(),
        var enabled: Boolean = false
    ) {
        fun id(id: Int) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun username(username: String) = apply { this.username = username }
        fun email(email: String) = apply { this.email = email }
        fun address(address: String) = apply { this.address = address }
        fun phone(phone: String) = apply { this.phone = phone }
        fun website(website: String) = apply { this.website = website }
        fun company(company: String) = apply { this.company = company }
        fun password(password: String) = apply { this.password = password }
        fun role(role: List<String>) = apply { this.role = role }
        fun enabled(enabled: Boolean) = apply { this.enabled = enabled }
        fun build() = User(id, name, username, email, address, phone, website, company, password, enabled, role)
    }
}