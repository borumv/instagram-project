package com.example.configuration.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

//    @Pointcut("execution(public * com.example.services.*(..))")
//    public void setMethodService(){
//
//    }
//
//    @Before("execution(public com.example.entities.User com.example.services.sdjpa.*.*(..))")
//    public void AOPSimpleMethod(JoinPoint joinPoint){
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        log.info("Kind - {}", joinPoint.getKind());
//        log.info("SourceLocation - {}", joinPoint.getSourceLocation());
//        log.info("Static Part - {}", joinPoint.getStaticPart());
//        log.info("This - {}", joinPoint.getThis());
//        log.info("Вызов метода {}", methodSignature);
//        Object[]args = joinPoint.getArgs();
//        if(args.length > 0){
//            log.info("Аргументы:");
//            for (Object arg : args) {
//                System.out.println(arg.getClass().getName());
//                System.out.println(arg);
//            }
//        }
//    }















    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.example.*)" +
            " || within(com.example.services.*)" +
            " || within(com.example.repositories.*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
}






//Service: ServiceName, involveMethod: methodName,