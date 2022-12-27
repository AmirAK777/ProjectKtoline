package com.example.demo.service

import com.example.demo.repository.ClientRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

@Service
class ClientService(val repo: ClientRepository) {

    fun calculateAge(birthday: String?): Int {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val dateOfBirth: Date = sdf.parse(birthday)
        val currentCalendar = Calendar.getInstance()
        val currentDate: Date = currentCalendar.time
        val ageInMillis: Long = currentDate.getTime() - dateOfBirth.getTime()
        val ageInYears = ageInMillis / (365L * 24 * 60 * 60 * 1000)
        return ageInYears.toInt()
    }

    fun tailleVerification(centimeters: String, feetInches: String): Boolean {
        val words: List<String> = feetInches.split(" ")
        val pied = words[0].substring(0, words[0].length - 1).toInt()
        val pouce = words[1].substring(0, words[1].length - 1).toInt()
        val feetInchesToCm = ((pied * 30.48) + (pouce * 2.54))


        if (abs(feetInchesToCm-centimeters.toDouble()) > 1.15) {
            return true
        }
        return false

    }

}