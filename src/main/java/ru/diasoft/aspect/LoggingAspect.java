package ru.diasoft.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.diasoft.domain.Greeting;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Around("execution(* ru.diasoft.controller.GreetingController.*(..))")
    public Greeting logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info("Класс : " + joinPoint.getTarget().getClass().getName());
        logger.info("Метод : " + joinPoint.getSignature());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Входные параметры : ");
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            stringBuilder
                    .append(joinPoint.getArgs()[i])
                    .append(" ");
        }
        logger.info(stringBuilder.toString());

        Object proceed = joinPoint.proceed();

        logger.info("Результат работы : " + proceed);

        return (Greeting) proceed;
    }

}
