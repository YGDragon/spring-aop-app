package web.ygdragon.keepingtrackapp.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogUserActions {

    /**
     * Registering application method calls
     *
     * @param joinPoint Aspect method
     * @throws Throwable Exception or error of executing
     */
    @Around("@annotation(TrackUserAction)")
    @AfterReturning(value = "@annotation(TrackUserAction)", returning = "result")
    public void logActions(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Object result = joinPoint.proceed();

        System.out.println("INFO >> " + "Произошел вызов метода " + methodName
                + " с параметрами " + Arrays.asList(arguments));
        System.out.println("INFO >> Результат выполнения метода: " + result);
    }
}
