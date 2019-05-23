package info.jakepark.springboot2kotlin.business.common.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractAuditEntity<U, ID> : AbstractPersistableEntity<ID>() {

    @CreatedDate
    protected var createdAt: LocalDateTime? = null

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    protected var createdBy: U? = null

    @LastModifiedDate
    protected var modifiedAt: LocalDateTime? = null

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "modified_by")
    protected var modifiedBy: U? = null

}
