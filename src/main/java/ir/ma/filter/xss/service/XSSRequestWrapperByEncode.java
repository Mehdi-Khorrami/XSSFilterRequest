package ir.ma.filter.xss.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.owasp.encoder.Encode;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@Slf4j
public class XSSRequestWrapperByEncode extends HttpServletRequestWrapper implements XSSRequestWrapperStrategy {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public XSSRequestWrapperByEncode(HttpServletRequest request) {
        super(request);
    }


    @Override
    public HttpServletRequest wrapRequest(HttpServletRequest request) {
        return new XSSRequestWrapperByEncode(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ServletInputStream originalInputStream = super.getInputStream();
        String requestBody = new String(originalInputStream.readAllBytes());

        String sanitizedBody = sanitizeInput(requestBody);

        return new ServletInputStream() {
            private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    sanitizedBody.getBytes()
            );

            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = sanitizeInput(values[i]);
        }
        return encodedValues;
    }

    private String sanitizeInput(String string) {
        log.warn("xss attack : {} ", string);
        return Encode.forHtml(string);
    }


}
