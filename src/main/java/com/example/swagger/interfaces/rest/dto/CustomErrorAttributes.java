package com.example.swagger.interfaces.rest.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered {

    private static final String ERROR_ATTRIBUTE = CustomErrorAttributes.class.getName() + ".ERROR";

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        storeErrorAttributes(request, ex);
        return null;
    }

    private void storeErrorAttributes(HttpServletRequest request, Exception ex) {
        request.setAttribute(ERROR_ATTRIBUTE, ex);
    }

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
            boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
        addStatus(errorAttributes, requestAttributes);
        addPath(errorAttributes, requestAttributes);
        return errorAttributes;
    }

    private void addStatus(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
        Integer status = getAttribute(requestAttributes, "javax.servlet.error.status_code");
        if (status == null) {
            errorAttributes.put("status", 999);
            errorAttributes.put("reason", "None");
            return;
        }
        errorAttributes.put("status", status);
        try {
            errorAttributes.put("reason", HttpStatus.valueOf(status).getReasonPhrase());
        } catch (Exception ex) {
            // Unable to obtain a reason
            errorAttributes.put("reason", "Http Status " + status);
        }
    }
    private void addPath(Map<String, Object> errorAttributes,
            RequestAttributes requestAttributes) {
        String path = getAttribute(requestAttributes, "javax.servlet.error.request_uri");
        if (path != null) {
            errorAttributes.put("path", path);
        }
    }
    @Override
    public Throwable getError(RequestAttributes requestAttributes) {
        Throwable exception = getAttribute(requestAttributes, ERROR_ATTRIBUTE);
        if (exception == null) {
            exception = getAttribute(requestAttributes, "javax.servlet.error.exception");
        }
        return exception;
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

}
