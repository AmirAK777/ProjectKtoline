package com.example.demo.controller

import com.example.demo.dto.PersonDto
import com.example.demo.repository.PersonRepository
import com.example.demo.toPerson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid


@Controller
class PersonController @Autowired constructor(private val personRepository: PersonRepository) {

    @GetMapping("/persons")
    fun index(model: Model): String {
        model["title"] = "Les Personnes"
        model["firstname"] = "Nom"
        model["person"] = personRepository.findAll()
        return "person/index"
    }

    @GetMapping("/addPerson")
    fun showForm(personDto: PersonDto): String {
        return "person/form"
    }
    @PostMapping("/addPerson")
    fun addPerson(@Valid personDto: PersonDto,
    bindingResult: BindingResult): String {
        return if (bindingResult.hasErrors()) {
            "person/form"
        } else {
            personRepository.save(personDto.toPerson())
            "redirect:/persons"
        }
    }

    fun alreadyLoginExists(login :String): Boolean {
        if (personRepository.findByLogin(login) == null){
            return false
        }
        return true
    }

}