package aop;

import me.dslztx.assist.util.metric.TimerAssist;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NotVeryUsefulAspect {

    @Before(value = "execution( * aop.TargetObject.beforeTest())")
    public void beforeAdvice() {
        System.out.print("hello ");
    }

    @AfterThrowing(value = "execution( * aop.TargetObject.afterThrowingTest())", throwing = "ex")
    public void afterThrowingAdvice(Exception ex) {
        System.out.println(ex.getClass());
    }

    @AfterReturning(value = "execution( * aop.TargetObject.afterReturningTest())", returning = "retObj")
    public void afterReturningAdvice(Object retObj) {
        System.out.println(retObj);
    }

    @After(value = "execution( * aop.TargetObject.afterTest())")
    public void afterAdvice() {
        System.out.println("test after advice");
    }

    @Around(value = "execution( * aop.TargetObject.*()) && @annotation(TimeCostRecord)")
    public void aroundAdvice(ProceedingJoinPoint t) throws Throwable {
        String methodName = t.getSignature().getName();

        TimerAssist.timerStart(methodName);

        try {
            t.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            TimerAssist.timerStop(methodName);

            System.out.println("[" + methodName + "] time cost is: " + TimerAssist.timerValue(methodName));
        }
    }

}
