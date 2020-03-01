package com.chauyiu1994.onlineBidUsersService.stream;

import org.springframework.cloud.stream.converter.CompositeMessageConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class MessageConfig {

    @Bean
    public CompositeMessageConverterFactory converterFactory() {
        return new CompositeMessageConverterFactory(
                Collections.<MessageConverter>emptyList(), null);
    }

    @Bean
    public ExecutorService messagePool() {

        return Executors.newCachedThreadPool();
    }
}
