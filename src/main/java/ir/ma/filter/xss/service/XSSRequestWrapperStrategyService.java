package ir.ma.filter.xss.service;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class XSSRequestWrapperStrategyService {
    private XSSRequestWrapperStrategy strategy;

    public void setStrategy(XSSRequestWrapperStrategy strategy) {
        this.strategy = strategy;
    }

    public HttpServletRequest processStrategy(HttpServletRequest request) {
        return strategy.wrapRequest(request);
    }
}
