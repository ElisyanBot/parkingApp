package demo.user;

import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import demo.car.Car;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id"
)
@Entity(name = "User")
@Table( name = "user")
public class User {
  @Id
  @GeneratedValue(
    strategy = GenerationType.IDENTITY
  )
  private Long Id;
  private String firstName;
  private String lastName;

  @OneToMany(
    fetch = FetchType.EAGER,
    mappedBy = "user"
  )
  private Set<Car> car = new HashSet<>();

  public User() {
  }

  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public User(String firstName, String lastName, Set<Car> car) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.car = car;
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<Car> getCar() {
    return car;
  }

  public void setCar(Set<Car> car) {
    this.car = car;
  }

}