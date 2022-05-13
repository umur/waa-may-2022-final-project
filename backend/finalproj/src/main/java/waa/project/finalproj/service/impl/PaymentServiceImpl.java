package waa.project.finalproj.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import waa.project.finalproj.dto.payment.PaymentDTO;
import waa.project.finalproj.dto.payment.PaymentSaveDTO;
import waa.project.finalproj.entity.Payment;
import waa.project.finalproj.entity.Rent;
import waa.project.finalproj.entity.User;
import waa.project.finalproj.repository.PaymentRepository;
import waa.project.finalproj.service.PaymentService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    @Override
    public void add(PaymentSaveDTO t) {
        paymentRepository.save(modelMapper.map(t, Payment.class));
    }

    @Override
    public void update(int id, PaymentDTO t) {
        var j = paymentRepository.findById(id);
        if (j.isPresent()){
            j.get().setAmount(t.getAmount());
            j.get().setDate(t.getDate());
            j.get().setReferentDate(t.getReferentDate());
            j.get().setUser(modelMapper.map(t.getUser(), User.class));
            j.get().setRent(modelMapper.map(t.getRent(), Rent.class));
            paymentRepository.save(j.get());
        }
    }

    @Override
    public List<PaymentDTO> findAll() {
        return StreamSupport
                .stream(paymentRepository.findAll().spliterator(), false)
                .map(u -> modelMapper.map(u, PaymentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO findById(int id) {
        var h = paymentRepository.findById(id);
        return h.isPresent() ? modelMapper.map(h.get(), PaymentDTO.class) : null;

    }

}
