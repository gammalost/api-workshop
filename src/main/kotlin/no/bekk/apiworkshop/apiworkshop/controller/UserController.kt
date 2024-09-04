package no.bekk.apiworkshop.apiworkshop.controller

import no.bekk.apiworkshop.apiworkshop.repository.User
import no.bekk.apiworkshop.apiworkshop.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/hello")
    fun helloWorld(): String {
        return userService.helloWorld()
    }

    @GetMapping("/users")
    fun users(
        @RequestParam alderFra: Int?,
        @RequestParam alderTil: Int?,
    ): List<User> {
        return userService.users(alderFra, alderTil)
    }

    @GetMapping("/usersDetailed")
    fun usersDetailed(
        @RequestParam alderFra: Int?,
        @RequestParam alderTil: Int?,
    ): List<UserDetailedDTO> {
        return userService.users(alderFra, alderTil).map {
            UserDetailedDTO(
                name = it.name,
                age = it.age,
                id = it.id,
                email = "${it.name.replace(" ", ".")}@bekk.no",
            )
        }
    }

    data class UserDetailedDTO(
        val name: String,
        val age: Int,
        val id: Int,
        val email: String,
    )

    @GetMapping("/users/{id}")
    fun user(
        @PathVariable id: String,
    ): User? {
        return userService.user(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/users")
    fun create(
        @RequestBody user: UserDTO,
    ): ResponseEntity<Int>? {
        return userService.create(user.name, user.age).let { id ->
            ResponseEntity.status(HttpStatus.CREATED).body(id)
        }
    }

    @DeleteMapping("/users/{id}")
    fun delete(
        @PathVariable id: String,
    ) = userService.delete(id)

    data class UserDTO(
        val name: String,
        val age: Int,
    )
}
