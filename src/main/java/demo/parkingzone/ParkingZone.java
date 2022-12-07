package demo.parkingzone;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import demo.parkingslot.ParkingSlot;
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
@Entity(name = "ParkingZone")
@Table(name = "parkingzone")
public class ParkingZone {

  @Id
  @GeneratedValue(
    strategy = GenerationType.IDENTITY
  )
  private Long id;
  private Integer pricePerMin;



  @OneToMany(
    fetch = FetchType.EAGER,
   mappedBy = "parkingZone"
  )
  private Set<ParkingSlot> parkingSlot = new HashSet<ParkingSlot>();

  public ParkingZone() {
  }

  public ParkingZone(int pricePerMin) {
    this.pricePerMin = pricePerMin;
  }
  public ParkingZone(
    int pricePerMin,
    Set <ParkingSlot> parkingSlot
  ) {
    this.pricePerMin = pricePerMin;
    this.parkingSlot = parkingSlot;
  }



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getPricePerMin() {
    return this.pricePerMin;
  }

  public void setPricePerMin(Integer pricePerMin) {
    this.pricePerMin = pricePerMin;
  }

  public Set<ParkingSlot> getParkingSlot() {
    return parkingSlot;
  }

  public void setParkingSlot(Set<ParkingSlot> parkingSlot) {
    this.parkingSlot = parkingSlot;
  }
}
