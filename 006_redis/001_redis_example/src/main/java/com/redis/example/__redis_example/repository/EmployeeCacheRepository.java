package com.redis.example.__redis_example.repository;

import java.time.Duration;
import java.util.List;

import javax.sound.midi.VoiceStatus;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.redis.example.__redis_example.entity.EmployeesEntity;

@Repository
public class EmployeeCacheRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ValueOperations<String, Object> valueOps;

    public EmployeeCacheRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOps = redisTemplate.opsForValue();
    }

    private static final String KEY = "get_all_employees";

    public List<EmployeesEntity> getAll() {
        Object cached = valueOps.get(KEY);
        if (cached != null) {
            return (List<EmployeesEntity>) cached;
        }
        return null;
    }

    public void saveAll(List<EmployeesEntity> employees) {
        valueOps.set(KEY, employees, Duration.ofSeconds(30)); // 5 minutos de TTL
    }
    
    public void deleteGetAll() {
    	redisTemplate.delete(KEY);
    }
    
}

