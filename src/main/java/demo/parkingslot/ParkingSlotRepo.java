package demo.parkingslot;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepo extends JpaRepository<ParkingSlot, Integer>{

   List<ParkingSlot> findAllByParkingZoneIdEquals(int id);

   @Query(
      value = "SELECT * FROM parkingslot WHERE parkingslot.id=?1 AND parkingslot.parking_zone_id=?2", 
      nativeQuery = true
   )
   Optional<ParkingSlot> findSlotByZoneIdAndSlotId(int slotId, int zoneId);

}
