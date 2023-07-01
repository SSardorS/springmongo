package uz.pixel.springmongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pixel.springmongo.document.Group;
import uz.pixel.springmongo.dto.GroupDto;
import uz.pixel.springmongo.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody GroupDto groupDto){
        boolean add = groupService.add(groupDto);
        return ResponseEntity.ok().body(add);
    }

    @GetMapping("/all")
    public HttpEntity<?> get(@RequestParam int page){
        Page<Group> all = groupService.getAll(page);
        return ResponseEntity.ok().body(all);
    }


    @GetMapping("/{id}")
    public boolean delete(@PathVariable String id){
        return groupService.deleted(id);
    }
}
