package com.ripcitysoftware.apm;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

  @Id
  @GeneratedValue
  @Column(name = "PERSON_ID")
  public Long id;

  private String name;

  private String address;

  @Column(name = "DATE_CREATED")
  private Instant dateCreated;

  @Column(name = "DATE_UPDATED")
  private Instant dateUpdated;

  @PrePersist
  @PreUpdate
  public void updateTimeFields() {
    Instant now = Instant.now();
    if (dateCreated == null) dateCreated = now;
    dateUpdated = now;
  }
}
