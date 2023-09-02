package org.hibernate.entities.user;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.PartitionKey;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "fd_multitenant_users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FdMultitenantUser
    implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "name")
    private String name;
    @Column(name = "password_digest")
    private String passwordDigest;
    @Column(name = "authentication_token")
    private String authenticationToken;
    @Column(name = "confirmation_token")
    private String confirmationToken;
    @Column(name = "confirmed")
    private Boolean confirmed;
    @Column(name = "account_admin")
    private Boolean accountAdmin;
    @Column(name = "twitter_id")
    private String twitterId;
    @Column(name = "facebook_id")
    private String facebookId;
    @Column(name = "freshid_mapping_status")
    private Integer freshidMappingStatus;
    @Column(name = "inviter_id")
    private Long inviterId;
    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "id", referencedColumnName = "id", updatable = false, insertable = false)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", updatable = false, insertable = false)
    private UserProfile userProfile;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "id", referencedColumnName = "user_id", updatable = false, insertable = false)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", updatable = false, insertable = false)
    private FdMultitenantUserEmail userEmail;

    @OneToOne(mappedBy = "fdMultitenantUser")
    private TerritoryUser territoryUser;
}
