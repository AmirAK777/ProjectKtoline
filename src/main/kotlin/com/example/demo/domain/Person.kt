package com.example.demo.domain

import javax.persistence.*

@Entity
@Table(name = "person")
class Person(

    @Column(name = "login", unique = true, nullable = false)
    var login: String,

    @Column(name = "firstname", nullable = false)
    var firstname: String,

    @Column(name = "lastname", nullable = false)
    var lastname: String,

    var role: String,
    var password: String,

    @Column(columnDefinition="TEXT")
    var description: String? = null,

    @OneToMany(mappedBy = "creditedTo", cascade = [CascadeType.ALL])
    var articles: MutableList<Article> = mutableListOf(),

    @Id @GeneratedValue var id: Long? = null)