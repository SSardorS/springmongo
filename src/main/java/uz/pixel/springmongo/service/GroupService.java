package uz.pixel.springmongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.pixel.springmongo.document.Group;
import uz.pixel.springmongo.dto.GroupDto;
import uz.pixel.springmongo.repository.GroupRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public boolean add(GroupDto groupDto){
        Group group = new Group();
        group.setName(groupDto.getName());

        groupRepository.save(group);
        System.out.println(new Date());
        return true;
    }

    public Page<Group> getAll(int page) {

        PageRequest pageable = PageRequest.of(page, 8);

        return groupRepository.findAll(pageable);

    }

    public boolean deleted(String id) {

        Optional<Group> byId = groupRepository.findById(id);

        if (byId.isEmpty())
            return false;

        groupRepository.deleteById(byId.get().getId());
        return true;

    }

    public boolean put(String id, GroupDto groupDto) {

        Optional<Group> groupOptional = groupRepository.findById(id);

        if (groupOptional.isEmpty())
            return false;

        Group group = groupOptional.get();

        group.setName(groupDto.getName());

        groupRepository.save(group);

        return true;

    }
}
