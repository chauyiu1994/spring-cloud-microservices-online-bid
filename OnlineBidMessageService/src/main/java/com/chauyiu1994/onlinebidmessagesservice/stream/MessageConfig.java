package com.chauyiu1994.onlinebidmessagesservice.stream;

import org.springframework.cloud.stream.converter.CompositeMessageConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

import java.util.Collections;

@Configuration
public class MessageConfig {

    @Bean
    public CompositeMessageConverterFactory converterFactory() {
        return new CompositeMessageConverterFactory(
                Collections.<MessageConverter>emptyList(), null);
    }

}
