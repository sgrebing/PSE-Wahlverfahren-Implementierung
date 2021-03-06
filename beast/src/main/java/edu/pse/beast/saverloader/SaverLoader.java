package edu.pse.beast.saverloader;

/**
 * Interface that allows the FileChooser to polymorphically save and load files.
 * @author NikolaiLMS
 */
public interface SaverLoader {
    /**
     * Creates an object from a given, by createSaveString() generated, saveString
     * @param saveString the saveString
     * @return the object
     * @throws Exception if the saveString does not contain a valid format
     */
    Object createFromSaveString(String saveString) throws Exception;
    /**
     * Creates a String from a given object, that can then be saved to a file and later given to
     * createFromSaveString() to retrieve the saved object.
     * @param object the Object to save
     * @return the saveString
     * @throws Exception if the Object could not be saved (contains null fields)
     */
    String createSaveString(Object object) throws Exception;
}
