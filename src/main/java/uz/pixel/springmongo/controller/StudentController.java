package uz.pixel.springmongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pixel.springmongo.document.Student;
import uz.pixel.springmongo.dto.StudentDto;
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

}
