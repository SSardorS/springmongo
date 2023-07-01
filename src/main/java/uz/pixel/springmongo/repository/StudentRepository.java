package uz.pixel.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uz.pixel.springmongo.document.Student;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

    @Query("{gender:?0}")
    List<Student> queryFindByGender(String gender);

    List<Student> findByGroupId(String groupId);
}
