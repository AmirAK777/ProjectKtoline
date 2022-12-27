package com.example.demo.repository

import com.example.demo.domain.Person
import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person, Long> {
    fun findByLogin(login: String): Person?

}

