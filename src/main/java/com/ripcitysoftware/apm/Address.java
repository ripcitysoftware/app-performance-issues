package com.ripcitysoftware.apm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

  @Id
  @GeneratedValue
  @Column(name = "ADDRESS_ID")
  public Long id;

  private String street;

  private String city;

  private String state;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "PERSON_ID_FK")
  private Person person;

  @Column(name = "DATE_CREATED")
  private Instant dateCreated;

  @Column(name = "DATE_UPDATED")
  private Instant dateUpdated;

  public void setPerson(Person person) {
    this.person = person;
  }

  @PrePersist
  @PreUpdate
  public void updateTimeFields() {
    Instant now = Instant.now();
    if (dateCreated == null) dateCreated = now;
    dateUpdated = now;
  }
}
