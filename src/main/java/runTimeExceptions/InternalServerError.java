package runTimeExceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class InternalServerError extends Exception{

    @Getter
    private HttpStatus httpStatus;
    public InternalServerError(){
        this.httpStatus = HttpStatus.valueOf(500);
    }
}
