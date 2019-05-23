package info.jakepark.springboot2kotlin.business.common.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractPersistableEntity<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: ID? = null

    @javax.persistence.Version
    val version: Long? = null

}
