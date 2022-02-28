package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.dslztx.assist.util.ObjectAssist;

enum ServiceStatus {
    START, ONLINE, STOP, OFFLINE;
}

@Controller
public class HealthController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    private static volatile ServiceStatus serviceStatus = null;

    public static void serviceStart() {
        serviceStatus = ServiceStatus.START;
    }

    public static void serviceStop() {
        serviceStatus = ServiceStatus.STOP;
    }

    public static void serviceOnline() {
        serviceStatus = ServiceStatus.ONLINE;
    }

    public static void serviceOffline() {
        serviceStatus = ServiceStatus.OFFLINE;
    }

    @RequestMapping(value = "/health/status", method = RequestMethod.GET)
    @ResponseBody
    public String status(HttpServletRequest request, HttpServletResponse response) {
        if (serviceStatus == ServiceStatus.ONLINE) {
            response.setStatus(HttpServletResponse.SC_OK);
            return ServiceStatus.ONLINE.toString();
        } else {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return ObjectAssist.isNotNull(serviceStatus) ? serviceStatus.toString() : "";
        }
    }
}
