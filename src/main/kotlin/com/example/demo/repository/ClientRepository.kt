package com.example.demo.repository

import com.example.demo.domain.Client
import org.springframework.data.repository.CrudRepository
import java.text.SimpleDateFormat
import java.util.*


interface ClientRepository : CrudRepository<Client,Long>{
    fun existsClientBySurnameIgnoreCaseAndGivenNameIgnoreCase(surname:String, givenName:String):Boolean

    fun existsClientByCcNumber(ccNumber: String):Boolean

}