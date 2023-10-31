package springboot.app.exception;

public class SocioException extends Exception{

    public SocioException(String msg, Throwable cause){
        super(msg, cause);
    };

    public SocioException(String msg){
        super(msg);
    };
}
