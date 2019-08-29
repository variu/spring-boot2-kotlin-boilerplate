package info.jakepark.springboot2kotlin.business.sample.entity

import info.jakepark.springboot2kotlin.business.common.entity.AbstractAuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User (

    @Column(nullable = false)
    val name: String,

    @Column
    val password: String,

    @Column
    val status: Int
) : AbstractAuditEntity<User, Long>()