package com.example.demo.configuration

import com.example.demo.domain.Article
import com.example.demo.domain.Client
import com.example.demo.domain.Person
import com.example.demo.repository.ArticleRepository
import com.example.demo.repository.ClientRepository
import com.example.demo.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.core.annotation.Order
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

//@Order(value = 1)
@Component
class DatabaseInitializer @Autowired constructor(
    val personRepository: PersonRepository,
    val articleRepository: ArticleRepository,
    val clientRepository: ClientRepository,
    val bCryptPasswordEncoder: BCryptPasswordEncoder
) : ApplicationRunner {

    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        databaseInitializer()
    }

    fun databaseInitializer() {
//        val countClient = clientRepository.count();
//
//        if (countClient > 0) return
//        val Amir = clientRepository.save(Client("Mr.", "Bob", "FERAZ","baba@dayrep.com","8/30/1940","Visa","44,","12/2019","02.03.41.33.71","4 rue du Général Ailleret","LE TAMPON","Guyane","97430","164","5' 5","45.751263","3.09124","2004 Mercedes-Benz C"))
//        val Nath = clientRepository.save(Client("Mrs.", "Baba", "ALLAIS","Roxeau@dayrep.com","12/12/1966","Master","54","12/2092","47.03.41.33.55","4 rue du Génér eret","TAMPON","Gune","9550","444","6' 6","555.751255","3.091244","204 Mercedes-Benz C"))

        val countUser = personRepository.count();

        if (countUser > 0) return

        val bob = personRepository.save(Person("solid", "Bob", "Martin",
            "ROLE_VIP",  bCryptPasswordEncoder.encode("Amir"),
            "Un référence pour les développeurs. est un ingénieur logiciel et auteur américain. Robert Cecil Martin (familièrement connu sous le nom Uncle Bob1) est un ingénieur logiciel et auteur américain. Il est co-auteur du Manifeste Agile2,3. Il dirige maintenant la société de conseil Uncle Bob Consulting LLC et le site web Clean Coders, qui héberge des vidéos basées sur son expérience et ses publications."))

        val meyer = personRepository.save(Person("meyer", "Bertrand", "Meyer",
            "ROLE_VIP",  bCryptPasswordEncoder.encode("Amir2"),
            "Bertrand Meyer publie, durant sa carrière, plusieurs ouvrages consacrés à l'informatique théorique ainsi qu'au langage Eiffel et des articles dans de nombreuses revues. Entre autres activités, il est professeur de génie logiciel à l'École polytechnique fédérale de Zurich de 2001 à 2016"
        ))

        val barbara = personRepository.save(Person("barbara", "Barbara", "Liskov",
            "ROLE_USER",  bCryptPasswordEncoder.encode("password"),
            "Barbara Liskov est une informaticienne américaine. Elle a reçu en 2004 la médaille John von Neumann1 pour « ses contributions fondamentales aux langages de programmation, à la méthodologie de la programmation et aux systèmes distribués » et le Prix Turing en 2008."))

        val gof = personRepository.save(Person("gof", "GoF", "Design Patterns",
            "ROLE_USER",  bCryptPasswordEncoder.encode("password"),
            "Gang of Four (GoF), les quatre informaticiens Erich Gamma, Richard Helm, Ralph Johnson et John Vlissides, auteurs de l'ouvrage de référence en programmation orientée objet Design Patterns : Catalogue de modèles de conception réutilisables."))

        articleRepository.save(
            Article(
                title = "S de SOLID",
                headline = "Responsabilité unique (Single Responsibility)",
                content = "Une classe, une fonction ou une méthode doit avoir une et une seule responsabilité",
                creditedTo = bob
            )
        )

        articleRepository.save(
            Article(
                title = "O de SOLD",
                headline = "Ouvert/fermé (Open/closed principle)",
                content = "Une entité applicative (classe, fonction, module ...) doit être fermée à la modification directe mais ouverte à l'extension",
                creditedTo = meyer
            )
        )

        articleRepository.save(
            Article(
                title = "L de SOLID",
                headline = "Substitution de Liskov (Liskov substitution principle)",
                content = "Une instance de type T doit pouvoir être remplacée par une instance de type G, tel que G sous-type de T, sans que cela ne modifie la cohérence du programme",
                creditedTo = barbara
            )
        )

        articleRepository.save(
            Article(
                title = "I de SOLID",
                headline = "Ségrégation des interfaces (Interface segregation principle)",
                content = "Préférer plusieurs interfaces spécifiques pour chaque client plutôt qu'une seule interface générale",
                creditedTo = bob
            )
        )

        articleRepository.save(
            Article(
                title = "D de SOLID",
                headline = "Inversion des dépendances (Dependency inversion principle)",
                content = "Il faut dépendre des abstractions, pas des implémentations",
                creditedTo = gof
            )
        )
    }
}


