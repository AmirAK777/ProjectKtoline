package com.example.demo.repository


import com.example.demo.domain.Article
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ArticleRepository : CrudRepository<Article, Long> {
    // ---------------------------------------------------------------
    // Version Native SQL (dépendance avec le schéma relationnel)
    // ---------------------------------------------------------------

    @Query(
        value = "SELECT * FROM article ORDER BY added_at DESC", nativeQuery = true
    )
    fun findAllArticlesOrderByAddedAtDesc(): List<Article>

    @Query(
        value = "SELECT count(*) FROM person where login = login", nativeQuery = true
    )
    fun compterNombre(login : Int) : Int

    // ---------------------------------------------------------------
    // Version DSL by Spring Boot (ne dépend que du modèle)
    // ---------------------------------------------------------------

    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}
