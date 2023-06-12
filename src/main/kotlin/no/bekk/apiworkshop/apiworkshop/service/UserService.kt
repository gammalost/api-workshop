package no.bekk.apiworkshop.apiworkshop.service

import no.bekk.apiworkshop.apiworkshop.repository.User
import no.bekk.apiworkshop.apiworkshop.repository.UserDetailed
import no.bekk.apiworkshop.apiworkshop.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun helloWorld() = "Hello world!"
    fun getUsers(alderFra: Int? = null, alderTil: Int? = null) = userRepository.getUsers()
        .sortedBy { it.age }
        .filter { it.age in ((alderFra ?: 0)..(alderTil ?: Int.MAX_VALUE)) }

    fun getUser(id: String): User? = userRepository.getUser(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun createUser(name: String, age: Int) = userRepository.createUser(name, age)
    fun deleteUser(name: String) = userRepository.deleteUser(name)

    fun getUsersDetailed() = userRepository.getUsers()
        .map {
            UserDetailed(
                id = it.id,
                name = it.name,
                age = it.age,
                email = it.name.replace(" ", ".") + "@bekk.no",
            )
        }
}
