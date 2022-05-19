package pro.manage.controller;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.manage.entity.Lease;
import pro.manage.entity.dto.LeaseDto;
import pro.manage.service.LeaseService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lease")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LeaseController implements GenericController<LeaseDto, UUID, LeaseService>{



   private final LeaseService leaseService;

    @Override
    public LeaseService getService() {
        return leaseService;
    }



    /*
    @PostMapping(value = "/add")
    public void creatBookingRecord(@RequestBody BookingRecord bookingRecord) {
        try {
           bookingRecordService.save(bookingRecord);
        } catch (Exception ex) {
            System.out.println("Transaction Failed!!! "+ ex);
        }
    }
    @DeleteMapping(value = {"/{id}"})
    public void deleteBooking(@PathVariable("id") Long id ){

    }

    @GetMapping(value = "/list")
    public List<BookingRecord> getBooking(){
        return bookingRecordService.findAll();
    }

//    @GetMapping(value = {"/get/{bookingRecordId}"})
//    public BookingRecord getBookingById(@PathVariable ("bookingRecordId") Long bookingRecordId){
//        return bookingRecordService.getBookingById(bookingRecordId);
//    }

     */

}
