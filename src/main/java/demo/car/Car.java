package demo.car;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import demo.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id"
)
@Entity(name = "Car")
@Table(name = "car")
public class Car {
  @Id
  @GeneratedValue(
    strategy = GenerationType.IDENTITY
  )
  private Long id;
  private String LicensePlateNr;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private User user;


  public Car() {
  }

  public Car(String licensePlateNr) {
    this.LicensePlateNr = licensePlateNr;
  }

  public Car(String licensePlateNr, User user) {
    this.LicensePlateNr = licensePlateNr;
    this.user = user;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLicensePlateNr() {
    return LicensePlateNr;
  }

  public void setLicensePlateNr(String licensePlateNr) {
    LicensePlateNr = licensePlateNr;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}