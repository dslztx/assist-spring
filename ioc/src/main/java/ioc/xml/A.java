package ioc.xml;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class A {
    private static final Logger logger = LoggerFactory.getLogger(A.class);

    private AA aaBean;

    private String aKey;

    private int age;

    private List<Integer> intList;

    private List<String> strList;

    private Set<Integer> intSet;

    private Set<String> strSet;

    private Map<String, Integer> strIntMap;

    private Map<String, String> strStrMap;

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

    public List<Integer> getIntList() {
        return intList;
    }

    public void setIntList(List<Integer> intList) {
        this.intList = intList;
    }

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public Set<Integer> getIntSet() {
        return intSet;
    }

    public void setIntSet(Set<Integer> intSet) {
        this.intSet = intSet;
    }

    public Set<String> getStrSet() {
        return strSet;
    }

    public void setStrSet(Set<String> strSet) {
        this.strSet = strSet;
    }

    public Map<String, Integer> getStrIntMap() {
        return strIntMap;
    }

    public void setStrIntMap(Map<String, Integer> strIntMap) {
        this.strIntMap = strIntMap;
    }

    public Map<String, String> getStrStrMap() {
        return strStrMap;
    }

    public void setStrStrMap(Map<String, String> strStrMap) {
        this.strStrMap = strStrMap;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
