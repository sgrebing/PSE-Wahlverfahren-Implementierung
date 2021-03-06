/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pse.beast.toolbox;

import java.awt.image.BufferedImage;
import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author Niels
 */
public final class FileLoader {

    private FileLoader() {

    }

    /**
     *
     * @param file the file that will be read
     * @return A LinkedList of String elements which are in the same order as in
     * the file
     * @throws FileNotFoundException if the file is not found it throws an
     * exception
     * @throws IOException throws Exception
     */
    public static LinkedList<String> loadFileAsString(File file) throws FileNotFoundException, IOException {

        LinkedList<String> stringlist;
        InputStream inputStream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            stringlist = new LinkedList<>();
            String line;

            line = br.readLine();
            while (line != null) {
                stringlist.add(line);
                line = br.readLine();
            }
        
        return stringlist;
    }

    /**
     *
     * @param in the file to be read as an image
     * @return the image, if it was possible to read it. In case it couldn't be
     * read, the methode returns null
     */
    public static BufferedImage loadFileAsImage(File toRead) {
        BufferedImage toReturn = null;
        try {
            toReturn = ImageIO.read(toRead);
        } catch (IOException e) {
            ErrorLogger.log("The specified file: " + toRead.getAbsolutePath() + " couldn't be loaded");
        }

        return toReturn;
    }

    public static String getFileFromResDONOTUSE(String fileName) {
        return new File("./src/main/resources" + fileName).getAbsolutePath();
    }

    public synchronized static String getNewUniqueName(String pathToDir) {
        ArrayList<String> usedNames = new ArrayList<String>();

        File folder = new File(pathToDir);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    usedNames.add(listOfFiles[i].getName());
                }
            }
        }

        String newName = getRandomName(100);
        while (usedNames.contains(newName)) {
            newName = getRandomName(100);
        }

        return newName;
    }

    private static String getRandomName(int wordSize) {
        SecureRandom random = new SecureRandom();
        return new java.math.BigInteger(wordSize, random).toString(32);
    }
}
