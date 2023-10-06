package com.fivengers.blooming.global.aspect;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.fivengers.blooming..*Controller.*(..))")
    public void allController() {

    }

    @Pointcut("execution(* com.fivengers.blooming..*Service.*(..))")
    public void allService() {

    }

    @Pointcut("execution(* com.fivengers.blooming..*Adapter.*(..))")
    public void allAdapter() {

    }

    @Around("allService() || allController() || allAdapter()")
    public Object createAllLog(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        Signature signature = joinPoint.getSignature();
        requestLogging(joinPoint.getTarget().getClass().getSimpleName(),
                ((MethodSignature) signature).getMethod().getName(),
                toParametersString(((CodeSignature) signature).getParameterNames(),
                        joinPoint.getArgs()));

        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        resultLogging(result, (MethodSignature) signature, stopWatch);
        return result;
    }

    private String toParametersString(String[] parameterNames, Object[] args) {
        return IntStream.range(0, parameterNames.length)
                .mapToObj(i -> {
                    String[] argument = args[i].toString().split("\\.");
                    return parameterNames[i] + "=" + argument[argument.length - 1];
                })
                .collect(Collectors.joining(","));
    }

    private void requestLogging(String className, String methodName, String arguments) {
        log.info("[REQUEST] {} -> {}({})",
                className, methodName, arguments.equals("") ? "NONE" : arguments);
    }

    private void resultLogging(Object result, MethodSignature signature, StopWatch stopWatch) {
        log.info("[RESPONSE] {} -> {}, [TIME] = {}ms",
                signature.getMethod().getName(),
                result.getClass().getSimpleName(),
                stopWatch.getTotalTimeMillis());
    }
}
