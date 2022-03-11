package ioc.javaconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class C {
    private static final Logger logger = LoggerFactory.getLogger(C.class);

    private CC ccBean;

    private String cKey;

    public CC getCcBean() {
        return ccBean;
    }

    public void setCcBean(CC ccBean) {
        this.ccBean = ccBean;
    }

    public void init() {
        logger.info("C init,the cKey is {}, the ccBean instance is {}", cKey, ccBean);
    }

    public void close() {
        logger.info("C close");
    }

    public String getcKey() {
        return cKey;
    }

    public void setcKey(String cKey) {
        this.cKey = cKey;
    }
}
