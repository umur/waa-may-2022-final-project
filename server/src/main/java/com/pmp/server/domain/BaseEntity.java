package com.pmp.server.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity{
  @Id
//  @GeneratedValue(generator = "UUID")
//  @GenericGenerator(
//    name = "UUID",
//    strategy = "org.hibernate.id.UUIDGenerator"
//  )
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updated_at;

  @Column(name = "is_deleted")
  private boolean isDeleted= Boolean.FALSE;

  @PrePersist
  protected void onCreate(){
    if(Objects.isNull(this.id)){
      this.id = UUID.randomUUID();
    }
  }
}

//TODO: transcation table while integrating stripe
//TODO: user device table while implementing push notification
