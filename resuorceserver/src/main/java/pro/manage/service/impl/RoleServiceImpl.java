package pro.manage.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.manage.entity.Role;
import pro.manage.entity.dto.RoleDto;
import pro.manage.repository.RoleRepository;
import pro.manage.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

RoleRepository roleRepository;
    @Override
    public RoleRepository getRepo() {
        return roleRepository;
    }

    @Override
    public Class<RoleDto> getDTOType() {
        return RoleDto.class;
    }
}
