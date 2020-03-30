package exception;

public class RobotWorldException extends Exception{

    public RobotWorldException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
