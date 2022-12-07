package demo.parkingevent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingEventRepo extends JpaRepository<ParkingEvent, Integer> {
  
}
