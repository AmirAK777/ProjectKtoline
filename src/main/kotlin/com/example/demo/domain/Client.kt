package com.example.demo.domain

import javax.persistence.*

@Entity
@Table(name = "client")
class Client(

    @Column(name = "Title", unique = false, nullable = false)
    var title: String,

    @Column(name = "Surname", unique = false, nullable = false)
    var surname: String,

    @Column(name = "GivenName", unique = false, nullable = false)
    var givenName: String,

    @Column(name = "EmailAddress", unique = false, nullable = false)
    var emailAddress: String,

    @Column(name = "Birthday", unique = false, nullable = false)
    var birthday: String,

    @Column(name = "CCType", unique = false, nullable = false)
    var ccType: String,

    @Column(name = "CCNumber", unique = false, nullable = false)
    var ccNumber: String,

    @Column(name = "CCExpires", unique = false, nullable = false)
    var ccExpires: String,

    @Column(name = "TelephoneNumber", unique = false, nullable = false)
    var telephoneNumber: String,

    @Column(name = "StreetAddress", unique = false, nullable = false)
    var streetAddress: String,

    @Column(name = "City", unique = false, nullable = false)
    var city: String,

    @Column(name = "StateFull", unique = false, nullable = false)
    var stateFull: String,

    @Column(name = "ZipCode", unique = false, nullable = false)
    var zipCode: String,

    @Column(name = "Centimeters", unique = false, nullable = false)
    var centimeters: String,

    @Column(name = "FeetInches", unique = false, nullable = false)
    var feetInches: String,

    @Column(name = "Latitude", unique = false, nullable = false)
    var latitude: String,

    @Column(name = "Longitude", unique = false, nullable = false)
    var longitude: String,

    @Column(name = "Contrainte", unique = false, nullable = true,)
    var contrainte: String?,

    @Column(name = "Vehicule", unique = false, nullable = true,)
    var vehicle: String?,

//    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL])
//    var vehicles: List<Vehicule>,





    @Id @GeneratedValue var id: Long? = null) {

}