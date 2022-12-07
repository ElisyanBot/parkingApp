package demo.parkingslot;


import org.geolatte.geom.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import demo.parkingzone.ParkingZone;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id"
)
@Entity(name = "ParkingSlot")
@Table(name = "parkingslot")
public class ParkingSlot {
  
  @Id
  @GeneratedValue(
    strategy = GenerationType.IDENTITY
  )
  private Long id;
  private String address;
  private Point<G2D> coordinate;

  private Boolean slotAvailable;

    
  // @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "parking_zone_id")
  private ParkingZone parkingZone;

  public ParkingSlot(){
    
  }

  public ParkingSlot(String address) {
    this.address = address;
    this.slotAvailable = true;
  }

  public ParkingSlot(String address, ParkingZone parkingZone) {
    this.address = address;
    this.parkingZone = parkingZone;
    this.slotAvailable = true;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Boolean getSlotAvailable() {
    return slotAvailable;
  }

  public void setSlotAvailable(Boolean slotAvailable) {
    this.slotAvailable = slotAvailable;
  }

  public ParkingZone getParkingZone() {
    return parkingZone;
  }

  public void setParkingZone(ParkingZone parkingZone) {
    this.parkingZone = parkingZone;
  }

  public Point<G2D> getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Point<G2D> coordinate) {
    this.coordinate = coordinate;
  }

}
