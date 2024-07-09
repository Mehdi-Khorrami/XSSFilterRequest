package ir.ma.filter.xss.service;


import jakarta.servlet.http.HttpServletRequest;

public interface XSSRequestWrapperStrategy {
    HttpServletRequest wrapRequest(HttpServletRequest request);
}
