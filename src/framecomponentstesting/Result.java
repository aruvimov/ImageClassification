/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framecomponentstesting;

import static framecomponentstesting.FileManager.getExtension;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;

/**
 *
 * @author Alisa
 */
public class Result implements Comparable {

    ArrayList<Result> children=null;
    String dirName;
    double[] highestList;
    //ArrayList<Double> highestList = ArrayList<>();
    ArrayList<ClassifyData> classifyDataList;
    String imagePath;
    public static int sortOn=0;

    public Result(ArrayList<Result> children, String dirName) {
        this.children = children;
        this.dirName = dirName;
    }

    Result(ArrayList<ClassifyData> classifyDataList, String imagePath, File startDir) {
        highestList = new double[classifyDataList.size()];
        this.classifyDataList=classifyDataList;
        this.imagePath=imagePath;
        this.dirName = startDir.getName();
        if (isClass(startDir)) {
            for (int i = 0; i < highestList.length; i++) {
                highestList[i]=randomDouble();
            }
        } else {
        children = new ArrayList<>();
        for (File next : startDir.listFiles()) {
            if (next.isDirectory()) {
                children.add(new Result(classifyDataList, imagePath, next));
            }
        }
        Collections.sort(children);
        for (int i = 0; i < highestList.length; i++) {
                highestList[i]=children.get(0).highestList[i];
            }
        }
        
    }

   
    public String getDirName() {
        return dirName;
    }
    public ArrayList<Result> getChildren() {
        return children;
    }


    @Override
    public int compareTo(Object o) {
        Result that = (Result) o;
        double thatHigh = that.highestList[sortOn];
        double thisHigh = this.highestList[sortOn];
        if (thatHigh> thisHigh) {
            return 1;
        } else if (thatHigh < thisHigh) {
            return -1;
        } else {
            return 0;
        }
    }

    

 

    private double randomDouble() {
        return Math.random() * 100.0;
    }
 
    private boolean isClass(File dir) {
        if (dir.list() == null) {
            System.out.println("");
        }
        for (String list : dir.list()) {
            if (isImage(list)) {
                return true;
            }
        }
        return false;
    }
    
    public double[] getHighestList() {
        return highestList;
    }
     public static boolean isImage(String name) {
        //System.out.println("Is this an image? Name: " + name);

        String imageExtensions = ".tiff .png .jpeg .jpg";
        String ext = getExtension(name);
        if (ext.equals("")) {
            return false;
        }
        if (imageExtensions.contains(ext)) {
            return true;
        }

        return false;
    }

     String bestGuess() {
         if ( children==null) {
             return dirName+" ("+highestList[sortOn]+")";
         }
         else {
             Collections.sort(children);
             return children.get(0).bestGuess();
         }
     }
    static void setSortOn(int s) {
         sortOn=s;
     }


}
