package a_defines.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class ResFilesUtil {

    public static void readFileAsString(File file, IOnLineRead listener) {
        try {
            BufferedReader bis = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bis.readLine()) != null){
                if(listener != null && !line.isEmpty()){
                    try {
                        listener.onLineRead(line);
                    }
                    catch (Exception e){
                        System.err.println("ResFilesUtil got Exception " + e.getMessage());
                    }
                }
            }
            bis.close();
        }
        catch (Throwable e){
            e.printStackTrace();
            if(listener != null){
                listener.onFail(e);
            }
        }
    }

    /**
     * @param resMap - where to place merge results
     * @param RparseResult1 - parsed data from R.java
     * @param parseResult2 - parsed data from resource file
     */
    public static void merge(Map<Integer, String> resMap, Map<String, Integer> RparseResult1,
                             Map<String, String> parseResult2) {
        for(Map.Entry<String, String> e : parseResult2.entrySet()){
            if(RparseResult1.containsKey(e.getKey())){
                resMap.put(RparseResult1.get(e.getKey()), e.getValue());
            }
        }
    }
}
