package waa.project.finalproj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.project.finalproj.dto.payment.PaymentDTO;
import waa.project.finalproj.dto.payment.PaymentSaveDTO;
import waa.project.finalproj.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public void save(@RequestBody PaymentSaveDTO h){
        paymentService.add(h);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(paymentService.findById(id));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody PaymentDTO l){
        paymentService.update(id, l);
    }
}
