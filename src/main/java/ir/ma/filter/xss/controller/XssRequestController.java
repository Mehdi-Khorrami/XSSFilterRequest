package ir.ma.filter.xss.controller;

import ir.ma.filter.xss.model.dto.XssRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/xss")
public class XssRequestController {

    @PostMapping("/request")
    public ResponseEntity<XssRequestDTO.Response> getXssRequestFromClient(@RequestBody XssRequestDTO.Request request) {
        System.out.println(request.toString());
        XssRequestDTO.Response response = XssRequestDTO.Response.builder().message("message received").build();
        return ResponseEntity.ok(response);
    }
}
