//package com.example.demo.domain
//
//import Vehicule
//import javax.persistence.*
//
//
//@Entity
//@Table(name = "marque")
//class Marque(
//    @Column(name = "name", unique = false, nullable = false)
//    var name: String,
//
//    @OneToMany(mappedBy = "marque", cascade = [CascadeType.ALL])
//    var vehicules: List<Vehicule> = ArrayList(),
//
//    @Id @GeneratedValue var id: Long? = null
//)