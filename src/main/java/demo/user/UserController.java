package demo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.car.Car;
import demo.car.CarRepo;



@RestController
public class UserController {

  private final UserRepo userRepo;
  private final CarRepo carRepo;
 
  public UserController( 
    UserRepo userRepo,
    CarRepo carRepo
  ) {
    this.userRepo = userRepo;
    this.carRepo = carRepo;
  }

//GET
  @GetMapping("/api/users")
  public List<User> getAllUsers(){
    return userRepo.findAll();
  }

  @GetMapping("/api/users/{id}")
  public Optional<User> getUser(@PathVariable int id ){
    return userRepo.findById(id);
  }

  @GetMapping("/api/users/{id}/cars")
  public List<Car> getAllUserCars(@PathVariable int id){
    return carRepo.findAllByUserIdEquals(id);
  }

  @GetMapping("/api/users/{userid}/cars/{carid}")
  public Car getUserCar(
    @PathVariable int userid, 
    @PathVariable int carid
  ) {
    return carRepo.findByCarIdAndUserId(carid, userid).get();
  }


//POST
  record addUserReq(
    String firstName,
    String lastName
  ) { }

  //change to "Restful way"
  @PostMapping("/api/users")
  public ResponseEntity<User> addUser(
    @RequestBody addUserReq req
    ) {
    //adds new user
    User user = new User(
      req.firstName,
      req.lastName
    );

    userRepo.save(user);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(user);
  }


  record addUserCarReq(
    String licensePlateNr
  ) { }

  @PostMapping("/api/users/{id}/cars")
  public Car addUserCar(
    @RequestBody addUserCarReq req, 
    @PathVariable int id 
  ) {
      Car car = new Car (
        req.licensePlateNr,
        userRepo.findById(id).get()
      );
  
      return carRepo.save(car);
   }
}

