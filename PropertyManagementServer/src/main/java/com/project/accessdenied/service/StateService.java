package com.project.accessdenied.service;

import com.project.accessdenied.entity.State;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StateService {
    List<State> getAll();
    State getById(long id);
    float totalIncomeOfState(long id);

}
