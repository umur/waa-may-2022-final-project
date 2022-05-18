package com.pmp.server.service;

import com.pmp.server.domain.Role;

public interface RoleService {
    Role findByName(String name);
}
