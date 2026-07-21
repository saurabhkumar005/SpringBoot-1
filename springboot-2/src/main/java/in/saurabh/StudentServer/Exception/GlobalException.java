package in.saurabh.StudentServer.Exception;

import in.saurabh.StudentServer.DTO.ExceptionResponseDTO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(EmailAlredyExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleEmailExistException(EmailAlredyExistException e, HttpServletRequest req){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                req.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDTO> handleRuntimeExecutionException(RuntimeException e, HttpServletRequest req){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                req.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDTO);
    }

    // 3. Handles CHECKED exceptions (like file system errors or network dropouts)
    @ExceptionHandler({IOException.class, Exception.class})
    public ResponseEntity<String> handleCheckedAndGeneralExceptions(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Checked Exception Triggered: " + e.getMessage());
    }

}
