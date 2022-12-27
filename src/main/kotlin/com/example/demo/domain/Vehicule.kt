//import com.example.demo.domain.Client
//import com.example.demo.domain.Marque
//import javax.persistence.*
//
//
//@Entity
//@Table(name = "vehicule")
//class Vehicule(
//
//    @Column(name = "year", unique = false, nullable = false)
//    var year: String,
//
//    @Column(name = "model", unique = false, nullable = false)
//    var model: String,
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "marque_id")
//    var marque: Marque,
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "client_id")
//    var client: Client,
//
//    @Id @GeneratedValue var id: Long? = null
//)