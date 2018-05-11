package android.content.res;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import a_defines.Defines;
import a_defines.parse.RFileParser;
import a_defines.parse.ResFilesUtil;
import a_defines.parse.StringResourceParser;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class Resources {
    private final Map<Integer, String> STRING_RES = new HashMap<>();

    public Resources(){
        File resDir = new File(Defines.PROJECT_PATH + "app/src/main/res/");

        RFileParser rParser = new RFileParser();
        ResFilesUtil.readFileAsString(new File(Defines.PROJECT_PATH
                + "app/build/generated/source/r/debug/" + Defines.PACKAGE_ADDRESS + "R.java"), rParser);

        StringResourceParser sParser = new StringResourceParser();
        ResFilesUtil.readFileAsString(new File(resDir, "values/strings.xml"), sParser);

        ResFilesUtil.merge(STRING_RES, rParser.getMap(), sParser.getMap());
    }

    public String getString(int id){
        return STRING_RES.get(id);
    }

    public final class Theme {}
}
