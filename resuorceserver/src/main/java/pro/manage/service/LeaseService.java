package pro.manage.service;
import pro.manage.entity.Lease;
import pro.manage.entity.dto.LeaseDto;
import pro.manage.repository.LeaseRepository;

public interface LeaseService extends GenericService<Lease, LeaseDto, Long, LeaseRepository> {

}
