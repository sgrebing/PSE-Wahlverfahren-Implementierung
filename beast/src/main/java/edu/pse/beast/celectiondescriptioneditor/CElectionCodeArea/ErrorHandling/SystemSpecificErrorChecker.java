package edu.pse.beast.celectiondescriptioneditor.CElectionCodeArea.ErrorHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import edu.pse.beast.codearea.ErrorHandling.CodeError;
import edu.pse.beast.toolbox.ErrorLogger;
import edu.pse.beast.toolbox.FileLoader;
import edu.pse.beast.toolbox.FileSaver;
import edu.pse.beast.toolbox.SuperFolderFinder;
import edu.pse.beast.toolbox.ThreadedBufferedReader;

/**
 * this is the superclass for system specific error checkers 
 * that gets implementen for the needed operating systems
 * @author Lukas
 *
 */
public abstract class SystemSpecificErrorChecker {
    
    private final String pathToTempFolder = "/core/c_tempfiles/";
    
    /**
     * checks the code for errors
     * @param toCheck the code to check
     * @return all errors found in a list
     */
    public List<CodeError> checkCodeForErrors(List<String> toCheck) {
        
        List<String> result = new ArrayList<String>();
        
        List<String> errors = new ArrayList<String>();
        
        String absolutePath = SuperFolderFinder.getSuperFolder() + pathToTempFolder;
        
        String pathToNewFile = absolutePath + FileLoader.getNewUniqueName(absolutePath);
        //pathToNewFile = pathToNewFile.replaceAll("%20", " ");
        //create two links to files, so in case an object file gets created we can delete it afterwards too
        File cFile = new File(pathToNewFile + ".c");

        File objFile = new File(pathToNewFile + ".obj");
        
        //write the code to the file
        FileSaver.writeStringLinesToFile(toCheck, cFile);
        
        
        Process process = checkCodeFileForErrors(cFile);
        
        if (process != null) {
            CountDownLatch latch = new CountDownLatch(2);
            ThreadedBufferedReader outReader = new ThreadedBufferedReader(
                    new BufferedReader(new InputStreamReader(process.getInputStream())), result, latch);
            ThreadedBufferedReader errReader = new ThreadedBufferedReader(
                    new BufferedReader(new InputStreamReader(process.getErrorStream())), errors, latch);

            
            //wait for the process to finish;
            try {
                process.waitFor();
                //wait for the readers to finish reading
                latch.await();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
            //parse the errors out of the returned lists
            List<CodeError> toReturn = parseError(result, errors);
            
            //deletes the temporary file, so it doesn't clog up the filesystem
            cFile.delete();            
            objFile.delete();
            
            return toReturn;
        } else {
            ErrorLogger.log("Process couldn't be started");
            return null;
        }
    }
    
    /**
     * checks a file for errors. Has to be implemented systemspecific
     * @param toCheck the file to check
     * @return a process that is currently checking the file
     */
    protected abstract Process checkCodeFileForErrors(File toCheck);
    
    /**
     * parses the system specific outputs from the process to the commong "CodeError" format
     * @param result the result list from the previously started process
     * @param errors the error list from the previously started process
     * @return a list of all found coderrors in the list
     */
    protected abstract List<CodeError> parseError(List<String> result, List<String> errors);
}
