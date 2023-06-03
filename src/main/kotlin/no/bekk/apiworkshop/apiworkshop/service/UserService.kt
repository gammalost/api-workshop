package no.bekk.apiworkshop.apiworkshop.service

import no.bekk.apiworkshop.apiworkshop.repository.User
import no.bekk.apiworkshop.apiworkshop.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getUsers() = userRepository.getUsers()

    fun getUser(name: String): User? = userRepository.getUser(name)
}
