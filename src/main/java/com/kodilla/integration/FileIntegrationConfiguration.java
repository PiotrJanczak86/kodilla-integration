package com.kodilla.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.*;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.io.File;

@Configuration
public class FileIntegrationConfiguration {

    @Bean
    FileWritingMessageHandler outputFileAdapter() {
        File directory = new File("data/output");
        FileWritingMessageHandler handler = new FileWritingMessageHandler(directory);
        handler.setExpectReply(false);
        return handler;
    }

    @Bean
    IntegrationFlow fileIntegrationFlow(
            FileReadingMessageSource fileAdapter,
            FileWritingMessageHandler outputFileHandler) {

        return IntegrationFlow.from(fileAdapter, config -> config.poller(Pollers.fixedDelay(1000)))
                .transform(f -> "")
                .handle(outputFileHandler)
                .get();
    }

    @Bean
    FileReadingMessageSource fileAdapter() {
        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File("data/input"));

        return fileSource;
    }

    @Bean
    FileTransformer transformer() {
        return new FileTransformer();
    }

//    @Bean
//    DefaultFileNameGenerator fileNameGenerator(){
//        DefaultFileNameGenerator fileNameGenerator = new DefaultFileNameGenerator();
//        fileNameGenerator.setHeaderName("data/output/testing.txt");
//        return fileNameGenerator;
//    }

}