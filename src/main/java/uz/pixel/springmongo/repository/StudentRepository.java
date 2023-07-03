package uz.pixel.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uz.pixel.springmongo.document.Student;
import uz.pixel.springmongo.dto.StudentFilter;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

    @Query("{gender:?0}")
    List<Student> queryFindByGender(String gender);

    List<Student> findByGroupId(String groupId);

    @Query("find({state:'ACTIVE'}).sort({?orderBy:?order}).skip(?skip).limit(?limit)")
    List<Student> getWithPageAndOrderBy(String orderBy, int order, int limit, int skip);
}
