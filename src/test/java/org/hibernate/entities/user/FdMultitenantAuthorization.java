package org.hibernate.entities.user;

import java.io.Serializable;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.PartitionKey;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "fd_multitenant_authorizations")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FdMultitenantAuthorization
        implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "account_id")
    @PartitionKey
    private Long accountId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 255)
    @Column(name = "provider")
    private String provider;

    @Size(max = 255)
    @Column(name = "uid")
    private String uid;

    @Lob
    @Column(name = "refresh_token")
    private String refreshToken;

    @Lob
    @Column(name = "access_token")
    private String accessToken;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false, insertable = false)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", updatable = false, insertable = false)
    @Fetch(FetchMode.JOIN)
    private UserProfile userProfile;
}
