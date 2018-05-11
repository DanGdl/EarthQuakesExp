package android.content.res;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class Resources {
    private final Map<Integer, String> STRING_RES = new HashMap<>();

    public Resources(){
        // todo Dan: read string res
    }

    public String getString(int id){
        return STRING_RES.get(id);
    }

    public final class Theme {}
}
