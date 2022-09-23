package com.ll.exam.app_2022_09_22.job.helloWorld;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WithParamJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job withParamJob() {
        return jobBuilderFactory.get("helloWorldJob")
                .start(withParamStep1())
                .next(withParamStep2())
                .build();
    }

    @Bean
    @JobScope
    public Step withParamStep1() {
        return stepBuilderFactory.get("withParamStep1")
                .tasklet(withParamStep1Tasklet())
                .build();
    }

    @Bean
    @JobScope
    public Step withParamStep2() {
        return stepBuilderFactory.get("withParamStep2")
                .tasklet(withParamStep2Tasklet())
                .build();
    }

    @Bean
    @StepScope
    public Tasklet withParamStep1Tasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("출력 : withParam1 테스클릿!");

            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    @StepScope
    public Tasklet withParamStep2Tasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("출력 : withParam2 테스클릿!");

            return RepeatStatus.FINISHED;
        };
    }
}