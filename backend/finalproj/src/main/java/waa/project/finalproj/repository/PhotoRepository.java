package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
}
