package com.ripcitysoftware.apm;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

  @OneToMany(cascade = CascadeType.ALL,
      mappedBy = "personId", orphanRemoval = true)
  private List<Address> addressList = new ArrayList<>();

  @Column(name = "DATE_CREATED")
  private Instant dateCreated;

  @Column(name = "DATE_UPDATED")
  private Instant dateUpdated;

  public void addAddress(Address address) {
    if (!addressList.contains(address)) {
      addressList.add(address);
    }
  }
  public void removeAddress(Address address) {
    addressList.remove(address);
  }

  @PrePersist
  @PreUpdate
  public void updateTimeFields() {
    Instant now = Instant.now();
    if (dateCreated == null) dateCreated = now;
    dateUpdated = now;
  }
}
