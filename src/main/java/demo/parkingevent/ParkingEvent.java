package demo.parkingevent;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import demo.car.Car;
import demo.parkingslot.ParkingSlot;
import demo.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id"
)
@Entity( name = "ParkingEvent")
@Table(name = "parkingevent")
public class ParkingEvent{
  @Id
  @GeneratedValue(
    strategy = GenerationType.IDENTITY
  )
  private long id;
  private final LocalDateTime startedParking;
  private LocalDateTime endedParking;
  private Boolean ongoing;

  @OneToOne
  private User user;

  @OneToOne
  private Car car;

  @OneToOne
  private ParkingSlot parkingSlot;

  public ParkingEvent() {
    this.startedParking = LocalDateTime.now();
  }

  public ParkingEvent(User user, Car car, ParkingSlot slot) {
    this.user = user;
    this.car = car;
    this.parkingSlot = slot;
    this.startedParking = LocalDateTime.now();
    this.ongoing = true;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDateTime getStartedParking() {
    return startedParking;
  }

  public LocalDateTime getEndedParking() {
    return endedParking;
  }

  public void setEndedParking(LocalDateTime endedParking) {
    this.endedParking = endedParking;
  }

  public Boolean getOngoing() {
    return ongoing;
  }

  public void setOngoing(Boolean ongoing) {
    this.ongoing = ongoing;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public ParkingSlot getParkingLot() {
    return parkingSlot;
  }

  public void setParkingLot(ParkingSlot parkingSlot) {
    this.parkingSlot = parkingSlot;
  }


}