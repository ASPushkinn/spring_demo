package com.example.redisdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccessCountController {
    private final AccessCountRepository accessCountRepository;

    public AccessCountController(AccessCountRepository accessCountRepository) {
        this.accessCountRepository = accessCountRepository;
    }

    @GetMapping(value = "accesscount")
    public List<AccessCountEntity> getAccessCountList() {
        List<AccessCountEntity> accessCountEntityList = (List<AccessCountEntity>) accessCountRepository.findAll();
        return accessCountEntityList;

    }

    @PutMapping(value = "/{location}")
    public void updateAccessCountItem(@PathVariable("location") String location) {
        AccessCountEntity accessCountEntity = getAccessCountEntity(location);
        if (accessCountEntity == null) {
            accessCountEntity = new AccessCountEntity(location);
        }
        accessCountEntity.setAccessCount(accessCountEntity.getAccessCount() + 1);
        accessCountRepository.save(accessCountEntity);
    }

    private AccessCountEntity getAccessCountEntity(String location) {
        return Optional.ofNullable(accessCountRepository.findById(location)).get().orElse(null);
    }

}
