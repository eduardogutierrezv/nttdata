package nttdata.usertest.exception;

public class UserErrorCallExcepcion extends Exception{

    public UserErrorCallExcepcion(){
        super();
    }

    public UserErrorCallExcepcion(String message){
        super(message);
    }

    public UserErrorCallExcepcion(String message, Exception ex){
        super(message, ex);
    }

}
