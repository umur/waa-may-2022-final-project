package com.pmp.server.repo;

import com.pmp.server.domain.PropertyImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertyImageRepo extends CrudRepository<PropertyImage, UUID> {
}
