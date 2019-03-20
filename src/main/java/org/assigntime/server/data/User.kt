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
        val email: String = "",

        @get: NotBlank
        val firstName: String = "",

        val lastName: String = "",

        @get: NotBlank
        val password : String = ""

)

@Entity
data class Token (
        @Id
        val token: String = "",

        @get: NotNull
        @OneToOne
        @JoinColumn(name = "id")
        val user: User = User()
)

// </editor-fold>

// <editor-fold name="DTOs">
data class Login (
        val email: String = "",
        val password: String = ""
)
// </editor-fold>

