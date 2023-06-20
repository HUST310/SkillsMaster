package com.hust310.SkillsMaster.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust310.SkillsMaster.util.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class MybatisRedisCache implements Cache {


    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private RedisTemplate redisTemplate;

    private RedisTemplate getRedisTemplate() {
        //通过ApplicationContextHolder工具类获取RedisTemplate
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate) ApplicationContextHolder.getBeanByName("redisTemplate");
        }
        return redisTemplate;
    }

    private final String id;

    public MybatisRedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        //使用redis的Hash类型进行存储
        getRedisTemplate().opsForHash().put(id, key.toString(), value);
    }

    @Override
    public Object getObject(Object key) {
        try {
            Object res = getRedisTemplate().opsForHash().get(id, key.toString());
            ObjectMapper mapper = new ObjectMapper();
            String className = id.substring(32, id.length() - 6);


            Class<?> aClass = Class.forName("com.hust310.SkillsMaster.domain." + className);
            return mapper.readValue(mapper.writeValueAsString(res),
                    mapper.getTypeFactory().constructParametricType(ArrayList.class, aClass));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("缓存出错 ");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        if (key != null) {
            getRedisTemplate().delete(key.toString());
        }
        return null;
    }

    @Override
    public void clear() {
        log.debug("清空缓存");
        Set<String> keys = getRedisTemplate().keys(this.id);
        if (!CollectionUtils.isEmpty(keys)) {
            getRedisTemplate().delete(keys);
        }
    }

    @Override
    public int getSize() {
        Long size = (Long) getRedisTemplate().execute((RedisCallback<Long>) RedisServerCommands::dbSize);
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}