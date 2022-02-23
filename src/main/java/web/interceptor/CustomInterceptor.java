package web.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.alibaba.fastjson.JSON;

import me.dslztx.assist.util.ArrayAssist;
import me.dslztx.assist.util.CollectionAssist;

@Component
public class CustomInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        Request request0 = new Request();

        Enumeration<String> headerNames = request.getHeaderNames();

        List<Header> headers = new ArrayList<>();

        String name;
        while (headerNames.hasMoreElements()) {
            name = headerNames.nextElement();
            headers.add(new Header(name, request.getHeader(name)));
        }

        request0.setHeaders(headers);

        logger.info("intercepted result request {}", JSON.toJSONString(request0));

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws IOException {

        ContentCachingResponseWrapper wrappedResponse = (ContentCachingResponseWrapper)(response);

        Response response0 = new Response();

        response0.setStatusCode(wrappedResponse.getStatus());

        Collection<String> headerNames = wrappedResponse.getHeaderNames();
        if (CollectionAssist.isNotEmpty(headerNames)) {
            List<Header> headers = new ArrayList<>();
            for (String headerName : headerNames) {
                headers.add(new Header(headerName, wrappedResponse.getHeader(headerName)));
            }
            response0.setHeaders(headers);
        }

        byte[] dd = wrappedResponse.getContentAsByteArray();
        if (ArrayAssist.isNotEmpty(dd)) {
            response0.setData(new String(dd));
        }
        wrappedResponse.copyBodyToResponse();

        logger.info("intercepted result response {}", JSON.toJSONString(response0));
    }
}

class Request {
    List<Header> headers;

    byte[] data;

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

class Response {

    int statusCode;

    List<Header> headers;

    /**
     * 以字符串形式表示返回内容
     */
    String data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

class Header {
    String name;

    String value;

    public Header(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}