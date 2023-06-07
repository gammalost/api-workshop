package no.bekk.apiworkshop.apiworkshop.repository

import kotlinx.serialization.Serializable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    private lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate

    fun getUsers(): List<User> = jdbcTemplate.query(
        "SELECT * FROM USERS",
        DataClassRowMapper(User::class.java),
    )

    fun getUser(id: String): User? {
        val params = params("ID" to id)
        return namedParameterJdbcTemplate.query(
            "SELECT * FROM USERS WHERE ID=:ID",
            params,
            DataClassRowMapper(User::class.java),
        ).single()
    }

    fun createUser(name: String, age: Int): Int {
        val params = params("NAME" to name, "AGE" to age)
        return namedParameterJdbcTemplate.update(
            "INSERT INTO USERS (NAME, AGE) values (:NAME, :AGE)",
            params,
        )
    }

    fun deleteUser(name: String): Int {
        val params = params("NAME" to name)
        return namedParameterJdbcTemplate.update(
            "DELETE FROM USERS WHERE NAME=:NAME",
            params,
        )
    }
}

private fun params(vararg args: Pair<String, Any>): MapSqlParameterSource {
    val test = MapSqlParameterSource()
    return args.forEach { test.addValue(it.first, it.second) }.let { test }
}

@Serializable
data class User(
    val id: Int,
    val name: String,
    val age: Int,
)
