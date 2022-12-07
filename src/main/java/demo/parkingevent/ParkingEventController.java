package demo.parkingevent;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.car.CarRepo;
import demo.parkingslot.ParkingSlotRepo;
import demo.user.UserRepo;

@RestController
public class ParkingEventController {
  
  private final ParkingEventRepo eventRepo;
  private final ParkingSlotRepo slotRepo;
  private final UserRepo userRepo;
  private final CarRepo carRepo;

  public ParkingEventController(
    ParkingEventRepo eventRepo,
    ParkingSlotRepo slotRepo,
    UserRepo userRepo,
    CarRepo carRepo
  ){
    this.eventRepo = eventRepo;
    this.slotRepo = slotRepo;
    this.userRepo = userRepo;
    this.carRepo = carRepo;
  }

  @GetMapping("/api/parkingevents")
  public List<ParkingEvent> getAllParkingEvents(){
    return eventRepo.findAll();
  }

  @GetMapping("/api/parkingevents/{id}")
  public ParkingEvent getParkingEvent(
    @PathVariable int id
  ){
    return eventRepo.findById(id).get();
  }

//POST

  record addParkingEventReq(
    int parkingSlotId,
    int userId,
    int carId
  ){}

  @PostMapping("/api/parkingevents")
  public ParkingEvent addParkingEvent(
    @RequestBody addParkingEventReq req
  ){
    ParkingEvent event = new ParkingEvent(
      userRepo.findById(req.userId).get(),
      carRepo.findById(req.carId).get(),
      slotRepo.findById(req.parkingSlotId).get()
    );
    return eventRepo.save(event);
  }

  @PatchMapping(
    path = "/api/parkingevents/{id}",
    params = {"ongoing"}
  )
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ParkingEvent updateParkingEvent(
    @RequestParam boolean ongoing,
    @PathVariable int id
    ){
      if(!ongoing) {
      ParkingEvent event = eventRepo.findById(id).get();
      event.setOngoing(ongoing);
      event.setEndedParking(LocalDateTime.now());
      return eventRepo.save(event);
    }

    return null;
  }

}
