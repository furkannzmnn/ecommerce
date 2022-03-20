package com.base.ecommerce.dto.request

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class MailRequest(
    val id: Int? = 0,

    @NotBlank(message = "name must not be empty")
    
    val name: String = "",

    @Email(message = "invalid email format")
    val mail: String= "",

    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),

    val subject: String,
    val content: String,
)
