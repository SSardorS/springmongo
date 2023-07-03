package uz.pixel.springmongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pixel.springmongo.OrderColumn;
import uz.pixel.springmongo.document.Student;
import uz.pixel.springmongo.dto.StudentDto;
import uz.pixel.springmongo.dto.StudentFilter;
import uz.pixel.springmongo.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @PostMapping
    public boolean add(@RequestBody StudentDto studentDto){

        return studentService.add(studentDto);

    }

    @GetMapping("/{gender}")
    public HttpEntity<?> get(@PathVariable String gender){

        List<Student> byGender = studentService.getByGender(gender);

        return ResponseEntity.ok().body(byGender);

    }

    @GetMapping("/group/{groupId}")
    public HttpEntity<?> getByGroupId(@PathVariable String groupId){

        List<Student> byGender = studentService.getByGroupIs(groupId);

        return ResponseEntity.ok().body(byGender);

    }

    @GetMapping("/filter")
    public HttpEntity<?> getWithPageAndOrderBy(@RequestBody StudentFilter studentFilter){

        List<Student> byGender = studentService.getWithPageAndOrderBy(studentFilter);

        return ResponseEntity.ok().body(byGender);

    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody StudentDto studentDto, @PathVariable String id){

        boolean edit = studentService.put(id, studentDto);

        return ResponseEntity.ok().body(edit);

    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable String id){

        boolean edit = studentService.delete(id);

        return ResponseEntity.ok().body(edit);

    }

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/mongo/template/{gender}")
    public HttpEntity<?> getWithMongoTemplate(@PathVariable String gender){

        Query query = new Query(Criteria.where("gender").is(gender));

        List<Student> students = mongoTemplate.find(query, Student.class, "student");

        return ResponseEntity.ok().body(students);

    }


    @GetMapping("/mongo/template/{limit}/{pageNumber}")
    public HttpEntity<?> getWithMongoTemplateAllStudentPage(@PathVariable int limit, @PathVariable int pageNumber){

        Query query = new Query().with(PageRequest.of(pageNumber, limit));

        List<Student> students = mongoTemplate.find(query, Student.class, "student");

        return ResponseEntity.ok().body(students);

    }

    @GetMapping("/mongo/template/group/{id}")
    public HttpEntity<?> getWithMongoTemplateByGroupId(@PathVariable String id){

        Query query = new Query(Criteria.where("groupId").is(id));

        List<Student> students = mongoTemplate.find(query, Student.class, "student");

        return ResponseEntity.ok().body(students);

    }

    @GetMapping("/mongo/template/orderBy/{orderColumn}/{orderDirection}")
    public HttpEntity<?> getWithMongoTemplateOrderBy(@PathVariable String orderColumn, @PathVariable String orderDirection){

        Query query = new Query().with(Sort.by(Sort.Direction.fromString(orderDirection), OrderColumn.valueOf(orderColumn).getColumn()));

        List<Student> students = mongoTemplate.find(query, Student.class, "student");

        return ResponseEntity.ok().body(students);

    }



}
