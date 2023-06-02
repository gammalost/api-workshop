package no.bekk.apiworkshop.apiworkshop.controller

import no.bekk.apiworkshop.apiworkshop.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class UserController(
    private val userService: UserService,
) {

    @GetMapping("users")
    fun getUsers() = userService.getUsers()

    @GetMapping("user")
    fun getUser(@RequestParam name: String) = userService.getUser(name)
}
