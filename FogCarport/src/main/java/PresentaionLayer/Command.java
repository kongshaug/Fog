package PresentaionLayer;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
  //      commands.put( "login", new CMD_Login() );

    }

    static Command from( HttpServletRequest request ) {
        String commandName = request.getParameter( "command" );
        if ( commands == null ) {
            initCommands();
        }
        // make defult unknown kommand
        return commands.getOrDefault(commandName,  null );
        
    }

    abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws Exception;

}
