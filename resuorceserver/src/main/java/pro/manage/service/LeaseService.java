package pro.manage.service;
import pro.manage.entity.Lease;
import pro.manage.entity.dto.LeaseDto;
import pro.manage.repository.LeaseRepository;

import java.util.UUID;

public interface LeaseService extends GenericService<Lease, LeaseDto, UUID, LeaseRepository> {

}
