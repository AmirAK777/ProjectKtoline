package com.example.demo.controller


import com.example.demo.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController{

    @GetMapping("/")
    fun index(model: Model): String {
        model["title"] = "Hello world !"
        return "main/index"
    }

}