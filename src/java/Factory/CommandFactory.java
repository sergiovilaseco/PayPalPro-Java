package Factory;

import Commands.Command;
import Commands.DoProDirect.doProDirect;




public class CommandFactory {

    public Command createCommand(String action) {
        Command command = null;

        // Selecting the action
        switch (action) {
            // User login
            case "doprodirect":
                command = new doProDirect();
                break;
            default:
                command = null;
                break;
        }

        //Return the instantiated Command object to the calling code...
        return command;// may be null
    }
}