package org.hibernate.entities.contacts;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.hibernate.entities.EntityTagAssociation;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@DiscriminatorValue("Contact")
@NoArgsConstructor
@Where(clause = "targetable_type='Contact'")
@EqualsAndHashCode(callSuper = true)
public class ContactTagAssociation
        extends EntityTagAssociation implements Serializable {
    @Builder
    public ContactTagAssociation(Long id, Long targetableId, Long tagId, Instant createdAt,
                                 Instant updatedAt, SalesContact contact) {
        super(id, contact.getAccountId(), targetableId, tagId, createdAt, updatedAt);
        this.contact = contact;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",referencedColumnName = "account_id")
    @JoinColumn(name = "targetable_id", referencedColumnName = "id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private SalesContact contact;

    public void setContact(SalesContact salesContact) {
        this.contact = salesContact;
        this.setAccountId(salesContact.getAccountId());
        this.setTargetableId(salesContact.getId());
    }
}
