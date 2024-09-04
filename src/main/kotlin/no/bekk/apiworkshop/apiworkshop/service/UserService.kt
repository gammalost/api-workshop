package no.bekk.apiworkshop.apiworkshop.service

import no.bekk.apiworkshop.apiworkshop.repository.User
import no.bekk.apiworkshop.apiworkshop.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun helloWorld(): String {
        return "Hello world!"
    }

    fun users(
        alderFra: Int?,
        alderTil: Int?,
    ): List<User> =
        userRepository.getUsers()
            .filter { user -> alderFra?.let { user.age >= it } ?: true }
            .filter { user -> alderTil?.let { user.age <= it } ?: true }
            .sortedBy { it.age }

    fun user(id: String) = userRepository.getUser(id)

    fun create(
        name: String,
        age: Int,
    ) = userRepository.createUser(name, age)

    fun delete(id: String) = userRepository.deleteUser(id)
}
