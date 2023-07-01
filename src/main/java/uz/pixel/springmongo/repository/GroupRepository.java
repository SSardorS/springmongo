package uz.pixel.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uz.pixel.springmongo.document.Group;

import java.util.Optional;

public interface GroupRepository extends MongoRepository<Group, String> {


}
