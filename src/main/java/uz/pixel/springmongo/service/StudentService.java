package uz.pixel.springmongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pixel.springmongo.Gender;
import uz.pixel.springmongo.document.Group;
import uz.pixel.springmongo.document.Student;
import uz.pixel.springmongo.dto.StudentDto;
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
}
