package no.bekk.apiworkshop.apiworkshop.controller

import no.bekk.apiworkshop.apiworkshop.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {

    @GetMapping("/hello")
    fun helloWorld() = userService.helloWorld()

    @GetMapping("/users")
    fun getUsers(
        @RequestParam alderFra: Int?,
        @RequestParam alderTil: Int?,
    ) = userService.getUsers(alderFra, alderTil)

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: String) = userService.getUser(id)

    @PostMapping("/user")
    fun createUser(@RequestParam name: String, @RequestParam age: Int): ResponseEntity<String> {
        userService.createUser(name, age)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping("/user")
    fun deleteUser(@RequestParam name: String) = userService.deleteUser(name)
}
