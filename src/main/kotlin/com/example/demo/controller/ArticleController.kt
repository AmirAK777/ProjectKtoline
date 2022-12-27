package com.example.demo.controller

import com.example.demo.domain.Article
import com.example.demo.repository.ArticleRepository
import com.example.demo.toDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ArticleController @Autowired constructor(private val articleRepository: ArticleRepository) {

    @GetMapping("/articles")
    fun index(model: Model): String {
        model["title"] = "Les articles"
        model["articles"] = articleRepository.findAllByOrderByAddedAtDesc()
        return "article/index"
    }

    @GetMapping("/article/{id}")
    fun detail(@PathVariable id: Long, model: Model): String {
        model["title"] = "Article"
        val article: Article? = articleRepository.findByIdOrNull(id)
        if (article != null) {
            model["article"] = article
            return "article/details"
        }
        return index(model)
    }

}

