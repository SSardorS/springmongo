package uz.pixel.springmongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pixel.springmongo.Gender;
import uz.pixel.springmongo.OrderColumn;
import uz.pixel.springmongo.OrderDirection;
import uz.pixel.springmongo.document.Group;
import uz.pixel.springmongo.document.Student;
import uz.pixel.springmongo.dto.StudentDto;
import uz.pixel.springmongo.dto.StudentFilter;
import uz.pixel.springmongo.repository.GroupRepository;
import uz.pixel.springmongo.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;

    public boolean add(StudentDto studentDto) {

        Optional<Group> groupOptional = groupRepository.findById(studentDto.getGroupId());

        if (groupOptional.isEmpty())
            return false;

        Student student = new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setBirthDate(studentDto.getBirthDate());
        student.setGender(Gender.valueOf(studentDto.getGender()));
        student.setGroupId(groupOptional.get().getId());

        studentRepository.save(student);

        return true;

    }

    public List<Student> getByGender(String gender) {

        return studentRepository.queryFindByGender(gender);

    }

    public List<Student> getByGroupIs(String groupId) {

        return studentRepository.findByGroupId(groupId);

    }

    public boolean put(String id, StudentDto studentDto) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        Optional<Group> groupOptional = groupRepository.findById(studentDto.getGroupId());

        if (studentOptional.isEmpty() || groupOptional.isEmpty())
            return false;

        Student student = studentOptional.get();
        student.setAge(studentDto.getAge());
        student.setName(studentDto.getName());
        student.setGender(Gender.valueOf(studentDto.getName()));
        student.setBirthDate(studentDto.getBirthDate());
        student.setGroupId(groupOptional.get().getId());

        studentRepository.save(student);

        return true;
    }

    public boolean delete(String id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty())
            return false;

        studentRepository.deleteById(studentOptional.get().getId());
        return true;

    }

    public List<Student> getWithPageAndOrderBy(StudentFilter studentFilter) {

        int limit = studentFilter.getPage() * studentFilter.getLimit();

        int skip = limit - studentFilter.getLimit();

        return studentRepository.getWithPageAndOrderBy(OrderColumn.valueOf(studentFilter.getColumnName()).getColumn(),OrderDirection.valueOf(studentFilter.getDirection()).getDirection(), limit, skip);

    }
}
