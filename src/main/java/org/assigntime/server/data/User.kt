package org.assigntime.server.data

import java.util.*
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
        var password: String = ""

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
        val user: User = User()
) {
    fun toDto() : TokenDTO = TokenDTO(token, userId = user.id)
}

@Entity
data class Entry (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long = 0,

        @get: NotBlank
        var title: String = "",

        var dateTime: Date = Date(Long.MIN_VALUE),

        var recurrenceFrequency: RecurrenceFrequency = RecurrenceFrequency.NONE,

        @get: NotNull
        @ManyToOne
        var owner: User = User()
)
// </editor-fold>

// <editor-fold name="DTOs">
data class Login (
        @Email
        val email: String = "",
        @get: NotBlank
        val password: String = ""
)

data class UserDTO (
        val id : Long,
        var email: String,
        var firstName: String,
        var lastName: String
)

data class TokenDTO (
        @get: NotBlank
        val token : String,
        val userId : Long
)
// </editor-fold>

// <editor-fold name="Enums">
enum class RecurrenceFrequency {
    NONE,
    DAILY,
    WEEKLY,
    FORTNIGHTLY,
    MONTHLY,
    YEARLY
}
// </editor-fold>

