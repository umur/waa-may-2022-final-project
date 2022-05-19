package com.project.accessdenied.service.impl;

import com.project.accessdenied.entity.State;
import com.project.accessdenied.repository.StateRepository;
import com.project.accessdenied.service.StateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateServiceImpl implements StateService {
    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<State> getAll() {

        var result = new ArrayList<State>();
        stateRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public State getById(long id) {
        return stateRepository.findById(id).get();
    }

    @Override
    public float totalIncomeOfState(long id) {
        //State s=getById(id);
        return 0;
        //return s.getProperties().stream().filter(p->p.isOccupied()==true).map(p->p.getRentAmount()).reduce(0, (a, b) -> a + b);
    }
}
