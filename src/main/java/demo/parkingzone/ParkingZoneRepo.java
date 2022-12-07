package demo.parkingzone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingZoneRepo extends JpaRepository<ParkingZone, Integer> {
  
  ParkingZone findParkingZoneById(int id);

}
