package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}
