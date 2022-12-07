package demo.car;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import demo.user.UserRepo;

@RestController
public class CarController {

  private final CarRepo carRepo;
  private final UserRepo userRepo;

  public CarController(CarRepo carRepo, UserRepo userRepo){
    this.carRepo = carRepo;
    this.userRepo = userRepo;
  }
  
  @GetMapping("/api/cars")
  public List<Car> getAllCars(){     
    return  carRepo.findAll();
  }

  @GetMapping("/api/cars/{id}")
  public Optional<Car> getCar(@PathVariable int id ){
    return carRepo.findById(id);
  }


//POST
  record addCarReq(
    String licensePlateNr,
    int userId
  ){}

  @PostMapping("/api/cars")
  @ResponseStatus(HttpStatus.CREATED)
  public Car addCar(@RequestBody addCarReq req) {
    
    Car car = new Car( 
      req.licensePlateNr,
      userRepo.findById(req.userId).get()
    );

    return carRepo.save(car);
  }
}

// User user = userRepo.findById(req.userId).get();
// Set<Car> cars = user.getCar();
// cars.add(car);
// userRepo.save(user);
