package com.example.demo.controller


import ch.qos.logback.core.net.server.Client
import com.example.demo.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
class ClientController @Autowired constructor(private val clientRepository: ClientRepository) {


    @GetMapping("/clients")
    fun clients(model: Model): String {
        model["title"] = "Liste des clients."
        model["listClients"] = clientRepository.findAll()
        return "client/index"
    }

    @GetMapping("/client/{id}")
    fun deleteItem(@PathVariable id: Long, redirectAttributes: RedirectAttributes): String {
        val client = clientRepository.findById(id)
        if (client.isPresent) {
            clientRepository.delete(client.get())
            redirectAttributes.addFlashAttribute("message", "Client est supprimé avec success ")
        } else {
            redirectAttributes.addFlashAttribute("error", "Client est introuvable ")
        }
        return "redirect:/clients"

    }

}


