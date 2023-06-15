package com.hust310.SkillsMaster.config;

import org.springframework.context.annotation.Import;

@org.springframework.context.annotation.Configuration
@Import({MvcConfiguration.class, RedisConfiguration.class})
public class Configuration {
}
