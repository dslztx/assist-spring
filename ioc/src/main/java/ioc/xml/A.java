package ioc.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class A {
    private static final Logger logger = LoggerFactory.getLogger(A.class);

    private AA aaBean;

    private String aKey;

    public AA getAaBean() {
        return aaBean;
    }

    public void setAaBean(AA aaBean) {
        this.aaBean = aaBean;
    }

    public void init() {
        logger.info("A init, the aKey is {}, the aaBean instance is {}", aKey, aaBean);
    }

    public void close() {
        logger.info("A close");
    }

    public String getaKey() {
        return aKey;
    }

    public void setaKey(String aKey) {
        this.aKey = aKey;
    }
}
