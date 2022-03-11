package ioc;

import me.dslztx.assist.util.StringAssist;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println(StringAssist.joinUseSeparator(context.getBeanDefinitionNames(), '\n'));

//        context.registerShutdownHook();
//        context.close();
    }
}