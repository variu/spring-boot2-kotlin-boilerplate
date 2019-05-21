package info.jakepark.springboot2kotlin.business.sample.entity

import javax.persistence.*

@Entity
@Table(name = "sample")
data class Sample(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(nullable = false)
    val name: String
)