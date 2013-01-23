/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxmiller.exception;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Max
 */
public class RepositoryException extends Exception {

    public RepositoryException() {
    }

    public RepositoryException(Exception ex) {
        super(ex);

    }

    public RepositoryException(String message) {
        super(message);

    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        logger.severe(this.getMessage());
        for(StackTraceElement t:this.getStackTrace()){
            logger.severe(t.toString());
          //  logger.severe(t.getClassName());
         ///   logger.severe(t.getMethodName());
           logger.severe(t.getFileName());
        }
        logger.severe(this.getLocalizedMessage());
        logger.severe(this.toString());
    }
    private static Logger logger;

    static {
        try {
            boolean append = true;
            FileHandler fh = null;
            try {
                fh = new FileHandler("//B2TI/arquivo.log", append);
            } catch (IOException ex) {
                try {
                    fh = new FileHandler("C://B2TI/arquivo.log", append);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //fh.setFormatter(new XMLFormatter());
            fh.setFormatter(new SimpleFormatter());
            logger = Logger.getLogger(RepositoryException.class.getName());
            logger.addHandler(fh);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
