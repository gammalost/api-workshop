package no.bekk.apiworkshop.apiworkshop.controller

import no.bekk.apiworkshop.apiworkshop.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {

    @GetMapping("/hello")
    fun helloWorld() = "Hello world!"

    @GetMapping("/users")
    fun getUsers() = userService.getUsers()

    @GetMapping("/user")
    fun getUser(@RequestParam name: String) = userService.getUser(name)

    @PostMapping("/user")
    fun createUser(@RequestParam name: String, @RequestParam age: Int): ResponseEntity<String> {
        userService.createUser(name, age)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
