package org.assigntime.server.data

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

// <editor-fold name="DB Objects">
@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long = 0,

        @get: NotBlank
        @get: Email
        var email: String = "",

        @get: NotBlank
        var firstName: String = "",

        var lastName: String = "",

        @get: NotBlank
        var password : String = ""

) {
    fun toDto() : UserDTO = UserDTO(id, email, firstName, lastName)
}

@Entity
data class Token (
        @Id
        val token: String = "",

        @get: NotNull
        @OneToOne
        @JoinColumn(name = "id")
        val userId: User = User()
) {
    fun toDto() : TokenDTO = TokenDTO(token, userId = userId.id)
}

// </editor-fold>

// <editor-fold name="DTOs">
data class Login (
        val email: String = "",
        val password: String = ""
)

data class UserDTO (
        val id : Long,
        var email: String,
        var firstName: String,
        var lastName: String
)

data class TokenDTO (
    val token : String,
    val userId : Long
)
// </editor-fold>

