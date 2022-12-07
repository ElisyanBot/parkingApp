package demo.parkingslot;

import java.util.List;
import java.util.Optional;


import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import static org.geolatte.geom.builder.DSL.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.parkingzone.ParkingZoneRepo;

@RestController()
public class ParkingSlotController {
  private final ParkingSlotRepo parkingRepo;
  private final ParkingZoneRepo zoneRepo;

  public ParkingSlotController (
    ParkingSlotRepo parkingRepo, 
    ParkingZoneRepo zoneRepo
  ){
    this.parkingRepo = parkingRepo;
    this.zoneRepo = zoneRepo;
  }

  //GETS
  @GetMapping("/api/parkingslots")
  public List<ParkingSlot> getAllParkingSlots(){
    return parkingRepo.findAll();
  }

  @GetMapping("/api/parkingslots/{id}")
  public Optional<ParkingSlot> getParkingSlot (
    @PathVariable int id
  ){
    return parkingRepo.findById(id);
  }

  //POSTS
  record addParkingSlotReq(
    String address,
    Float areaX,
    Float areaY,
    int zoneId
  ){}

  @PostMapping("/api/parkingslots")
  @ResponseStatus(HttpStatus.CREATED)
  public ParkingSlot addParkingSlot (
    @RequestBody addParkingSlotReq req
  ) {
    ParkingSlot slot = new ParkingSlot (
      req.address,
      zoneRepo.findById(req.zoneId).get()
    );

    slot.setCoordinate(
      point(
        WGS84, g(req.areaX, req.areaY)
      )
    );

    return parkingRepo.save(slot);
  }

  //PATCHES
  @PatchMapping(
    path = "/api/parkingslots/{id}",
    params = { "slotAvailable" }
  )
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ParkingSlot updateParkingSlot(
    @PathVariable int id,
    @RequestParam Boolean slotAvailable
  ) {
    
    ParkingSlot slot = parkingRepo.findById(id).get();
    slot.setSlotAvailable(slotAvailable);

    return parkingRepo.save(slot);
  }
}
