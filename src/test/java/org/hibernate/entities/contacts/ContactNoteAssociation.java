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
import org.hibernate.entities.EntityNoteAssociation;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@DiscriminatorValue("Contact")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "targetable_type='Contact'")
public class ContactNoteAssociation
        extends EntityNoteAssociation implements Serializable {
    @Builder
    public ContactNoteAssociation(Long id, Long targetableId, Long noteId, Instant createdAt,
                                  Instant updatedAt, SalesContact contact) {
        super(id, contact.getAccountId(), targetableId, noteId, createdAt, updatedAt);
        this.contact = contact;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",referencedColumnName = "account_id",insertable = false,updatable = false)
    @JoinColumn(name = "targetable_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private SalesContact contact;

    public void setContact(SalesContact salesContact) {
        this.contact = salesContact;
        this.setAccountId(salesContact.getAccountId());
        this.setTargetableId(salesContact.getId());
    }
}
