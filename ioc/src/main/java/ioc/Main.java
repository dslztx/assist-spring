package ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.dslztx.assist.util.StringAssist;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println(StringAssist.joinUseSeparator(context.getBeanDefinitionNames(), '\n'));

        // context.registerShutdownHook();
        // context.close();
    }
}