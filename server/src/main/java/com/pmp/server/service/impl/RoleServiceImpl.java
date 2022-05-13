package com.pmp.server.service.impl;

import com.pmp.server.domain.Role;
import com.pmp.server.repo.RoleRepo;
import com.pmp.server.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role findByName(String name) {
        return roleRepo.findByRoleName(name);
    }
}
