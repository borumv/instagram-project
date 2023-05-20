package com.example.configuration.aspects;

import com.example.entities.Chat;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * com.example.services.*(..))")
    public void setMethodService() {
    }

    @Before("execution(public com.example.entities.User com.example.services.sdjpa.*.*(..))")
    public void AOPSimpleMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        var className = joinPoint.getSourceLocation().getWithinType().getSimpleName();
        var methodName = methodSignature.getMethod().getName();
        var args = joinPoint.getArgs();
        var parameterNames = methodSignature.getParameterNames();
        var parameterTypes = methodSignature.getParameterTypes();


        record MethodArgumentsInfo(String argType, String argName, String argValue) implements CharSequence {
            @Override
            public int length() {
                return toString().length();
            }

            @Override
            public char charAt(int index) {
                return toString().charAt(index);
            }

            @Override
            public CharSequence subSequence(int start, int end) {
                return toString().subSequence(start, end);
            }


            @Override
            public String toString() {
                return argType + " " + argName + " " + argValue;
            }
        }
        var collect = IntStream.range(0, args.length)
                .mapToObj(index -> new MethodArgumentsInfo(parameterTypes[index].toString(), parameterNames[index], args[index].toString()))
                .collect(Collectors.joining(", "));

        log.info("class: {}, invoke method: {}, arguments: {}", className, methodName, collect);


    }
}