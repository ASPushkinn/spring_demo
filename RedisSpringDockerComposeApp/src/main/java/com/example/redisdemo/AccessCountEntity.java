package com.example.redisdemo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Optional;

@Getter
@Setter
@RedisHash("accesscounts")
public class AccessCountEntity {
    @Id
    String location;
    int  accessCount;

    public AccessCountEntity(String location) {
        this.location = location;
    }

}
