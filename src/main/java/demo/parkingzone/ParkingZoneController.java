package demo.parkingzone;

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


import demo.parkingslot.ParkingSlot;
import demo.parkingslot.ParkingSlotRepo;


@RestController
public class ParkingZoneController {

  private final ParkingZoneRepo zoneRepo;
  private final ParkingSlotRepo parkingRepo;

  public ParkingZoneController(
    ParkingZoneRepo zoneRepo,
    ParkingSlotRepo parkingRepo
  ){
    this.zoneRepo = zoneRepo;
    this.parkingRepo = parkingRepo;
  }
  
//GET  

  @GetMapping("/api/parkingzones")
  public List<ParkingZone> getAllParkingZones(){
    return zoneRepo.findAll();
  }
  
  @GetMapping("/api/parkingzones/{id}")
  public Optional<ParkingZone> getParkingZone(@PathVariable int id){
    return zoneRepo.findById(id);
  }

  @GetMapping("/api/parkingzones/{id}/parkingslots")
  public List<ParkingSlot> getAllParkingZonesSlots(@PathVariable int id){
    return parkingRepo.findAllByParkingZoneIdEquals(id);
  }

//POST
  record AddParkingZoneReq(
    int pricePerMin
  ){}

  @PostMapping("/api/parkingzones")
  @ResponseStatus(HttpStatus.CREATED)
  public ParkingZone addParkingZone(
    @RequestBody AddParkingZoneReq req
  ){
    ParkingZone zone = new ParkingZone(
      req.pricePerMin
    );

    return zoneRepo.save(zone);
  }


  record addParkingZoneSlotReq(
    String address,
    Float areaX,
    Float areaY
  ){}

  @PostMapping("/api/parkingzones/{id}/parkingslots")
  @ResponseStatus(HttpStatus.CREATED)
  public ParkingSlot addParkingZoneSlot(
    @RequestBody addParkingZoneSlotReq req,
    @PathVariable int id
  ){
    ParkingSlot slot = new ParkingSlot(
      req.address,
      zoneRepo.findById(id).get()
    );

    slot.setCoordinate(
      point(
        WGS84, g(req.areaX, req.areaY)
      )
    );

    return parkingRepo.save(slot);
  }

//PATCH

@PatchMapping(
  path = "/api/parkingzones/{zoneid}/parkingslots/{slotid}",
  params = { "slotAvailable" }
)
@ResponseStatus(HttpStatus.ACCEPTED)
public ParkingSlot updateParkingSlot(
  @PathVariable int zoneid,
  @PathVariable int slotid,
  @RequestParam Boolean slotAvailable
) {
  

  ParkingSlot slot = parkingRepo.findSlotByZoneIdAndSlotId(slotid, zoneid).get();
   
  slot.setSlotAvailable(slotAvailable);

  return parkingRepo.save(slot);
}

}
