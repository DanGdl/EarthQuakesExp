package a_defines.parse;

import java.util.HashMap;
import java.util.Map;

public class ResourceParser<K, V> {
    protected final Map<K, V> MAP = new HashMap<>();

    protected String getSubSequence(String line, int stIndex, int endIndex) {
        if(stIndex != -1 && endIndex != -1){
            return line.substring(stIndex, endIndex);
        }
        else{
            return "";
        }
    }

    public Map<K, V> getMap(){
        return MAP;
    }
}
