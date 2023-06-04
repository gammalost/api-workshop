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

    fun getUsers() = jdbcTemplate.query(
        "SELECT * FROM USERS",
        DataClassRowMapper(User::class.java),
    )

    fun getUser(name: String): User? {
        val params = MapSqlParameterSource().addValue("NAME", name)
        return namedParameterJdbcTemplate.query(
            "SELECT * FROM USERS WHERE NAME=:NAME",
            params,
            DataClassRowMapper(User::class.java),
        ).firstOrNull()
    }

    fun createUser(name: String, age: Int): Int {
        val params = MapSqlParameterSource().addValue("NAME", name).addValue("AGE", age)
        return namedParameterJdbcTemplate.update(
            "INSERT INTO USERS (NAME, AGE) values (:NAME, :AGE)",
            params,
        )
    }
}

@Serializable
data class User(
    val id: Int,
    val name: String,
    val age: Int,
)
