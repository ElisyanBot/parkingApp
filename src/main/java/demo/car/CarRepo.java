package demo.car;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepo extends JpaRepository<Car, Integer>{

  List<Car> findByUser(int id); 
  List<Car> findAllByUserIdEquals(int userID);
  
  @Query(
    value = "SELECT * FROM car WHERE car.id= ?1 AND car.user_id= ?2",
    nativeQuery = true
  )
  Optional<Car> findByCarIdAndUserId(int carId, int userId);
}
