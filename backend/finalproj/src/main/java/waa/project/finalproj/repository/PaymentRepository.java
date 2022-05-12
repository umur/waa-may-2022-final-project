package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.entity.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}
