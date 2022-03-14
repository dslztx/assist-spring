import aop.TargetObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        TargetObject object = (TargetObject) applicationContext.getBean("targetObject");
        object.beforeTest();

        try {
            object.afterThrowingTest();
        } catch (Exception e) {
//            e.printStackTrace();
        }

        object.afterReturningTest();

        try {
            object.afterTest();
        } catch (Exception e) {
//            e.printStackTrace();
        }


        object.aroundTest();

        object.aroundTest2();
    }
}
