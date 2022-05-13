package waa.project.finalproj.service;

import waa.project.finalproj.dto.payment.PaymentDTO;
import waa.project.finalproj.dto.payment.PaymentSaveDTO;

import java.util.List;

public interface PaymentService {
    void add(PaymentSaveDTO t);
    void update(int id, PaymentDTO t);
    List<PaymentDTO> findAll();
    PaymentDTO findById(int id);
}
