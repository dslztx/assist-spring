package ioc.componentscan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class B {
    private static final Logger logger = LoggerFactory.getLogger(B.class);

    @Value("${bKey}")
    String bKey;

    @Autowired
    private BB bbBean;

    public BB getBbBean() {
        return bbBean;
    }

    public void setBbBean(BB bbBean) {
        this.bbBean = bbBean;
    }

    @PostConstruct
    public void init() {
        logger.info("B init, the bKey is {}, the bbBean instance is {}", bKey, bbBean);
    }

    @PreDestroy
    public void close() {
        logger.info("B close");
    }
}
