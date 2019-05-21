/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PresentaionLayer;

/**
 *
 * @author benja
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author tha Purpose: Show how to use the java.util.logging API. Suggestion:
 * Make a Logger object for each class that needs to be logged. Make the logger
 * object static and final so all instances of the class will use the same
 * logger instance. Logger.getLogger(LoggingExamples.class.getName()); It is
 * common practice to use the class name including package name as name for the
 * Logger. The name of the Logger to create is passed as string parameter to the
 * Logger.getLogger() method. logger.addHandler() is how we add handlers that
 * can write to console (Default) and a textfile, a network server...
 */
public class logger {

//    static final Logger LOGGER = DefaultLogger.getLogger(false, true);
    static final Logger LOGGER = Logger.getLogger(logger.class.getName());

    public void run(String message) throws IOException {
      
         try{
            // see: http://tutorials.jenkov.com/java-logging/handlers.html
            FileHandler handler = new FileHandler("C:\\Users\\benja\\OneDrive\\Dokumenter\\NetBeansProjects\\Fog\\Fog\\FogCarport\\src\\main\\java\\PresentaionLayer\\errorsLog.txt", true);
            handler.setFormatter(new SimpleFormatter());
            handler.setFormatter(new VerySimpleFormatter());
            LOGGER.addHandler(handler);
          LOGGER.log(Level.OFF, "Only this message will be logged");
           //Log a message: First anounce sevirity level, then the message and then a list of objects to be inserted in the message.
        LOGGER.log(Level.SEVERE, "This is the {0} to be {1}", new Object[]{message, "logged"});
        //Log a Throwable
        LOGGER.log(Level.WARNING, "Message to be logged", new RuntimeException("ERROR"));
        handler.close();
        } 
         
         catch (IOException | SecurityException ex) {
            ex.printStackTrace();
        }  
    }

    private class VerySimpleFormatter extends Formatter {

        private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

        @Override
        public String format(final LogRecord record) {
            return String.format(
                    "%1$s %2$-7s %3$s\n",
                    new SimpleDateFormat(PATTERN).format(
                            new Date(record.getMillis())),
                    record.getLevel().getName(),
                    formatMessage(record));
        }
    }
}
