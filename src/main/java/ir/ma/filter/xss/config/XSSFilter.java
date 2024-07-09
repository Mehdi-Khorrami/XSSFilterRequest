package ir.ma.filter.xss.config;


import ir.ma.filter.xss.service.XSSRequestWrapperByException;
import ir.ma.filter.xss.service.XSSRequestWrapperStrategyService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@WebFilter("/*")
public class XSSFilter implements Filter {

    private final XSSRequestWrapperStrategyService strategyService;

    @Autowired
    public XSSFilter(XSSRequestWrapperStrategyService strategyService) {
        this.strategyService = strategyService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        strategyService.setStrategy(new XSSRequestWrapperByException(httpServletRequest));
        HttpServletRequest wrappedRequest = strategyService.processStrategy(httpServletRequest);
        filterChain.doFilter(wrappedRequest, servletResponse);
    }
}
