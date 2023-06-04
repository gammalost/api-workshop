package no.bekk.apiworkshop.apiworkshop.service

import no.bekk.apiworkshop.apiworkshop.repository.User
import no.bekk.apiworkshop.apiworkshop.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getUsers() = userRepository.getUsers().sortedBy { it.age }

    fun getUser(name: String): User? = userRepository.getUser(name) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun createUser(name: String, age: Int) = userRepository.createUser(name, age)
}
