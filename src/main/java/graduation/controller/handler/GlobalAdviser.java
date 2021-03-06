package graduation.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
// TODO: 31.05.2018 add more handlers
public class GlobalAdviser {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity globalHandler(HttpServletRequest req, Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
