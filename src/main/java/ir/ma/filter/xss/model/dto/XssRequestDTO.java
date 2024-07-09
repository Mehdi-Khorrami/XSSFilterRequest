package ir.ma.filter.xss.model.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public abstract class XssRequestDTO {

    @Getter
    @Setter
    @Builder
    public static class Request {

        private String name;

        private String lastName;

        private String cellPhone;
    }


    @Getter
    @Setter
    @Builder
    public static class Response {

        private String message;

    }
}
