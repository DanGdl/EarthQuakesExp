package a_defines.parse;

public class RFileParser extends ResourceParser<String, Integer> implements IOnLineRead {
    private final String ST_CLASS_TAG = "public static final class ";
    private final int ST_CLASS_TAG_LENGTH = ST_CLASS_TAG.length();

    private final String EN_CLASS_TAG = " {";

    private final String ST_TAG = "public static final int ";
    private final int ST_TAG_LENGTH = ST_TAG.length();

    private final String MID_TAG = "=";
    private final int MID_TAG_LENGTH = MID_TAG.length();

    private final String EN_TAG = ";";

    private String currentResType = "";

    @Override
    public void onLineRead(String line) {
        if(line.contains("*") || line.contains("[]")){
            return;
        }
        int stIndex;
        int endIndex;
        if((stIndex = line.indexOf(ST_CLASS_TAG)) != -1){
            stIndex += ST_CLASS_TAG_LENGTH;
        }

        endIndex = line.indexOf(EN_CLASS_TAG);
        if(stIndex != -1 && endIndex != -1){
            currentResType = getSubSequence(line, stIndex, endIndex);
        }

        if((stIndex = line.indexOf(ST_TAG)) != -1){
            stIndex += ST_TAG_LENGTH;
        }

        endIndex = line.indexOf(MID_TAG);
        if(stIndex == -1 && endIndex == -1){
            return;
        }
        String key = getSubSequence(line, stIndex, endIndex);

        stIndex = endIndex + MID_TAG_LENGTH;
        endIndex = line.indexOf(EN_TAG);
        String value = getSubSequence(line, stIndex, endIndex);

        MAP.put(currentResType + "_" + key, Integer.parseInt(value)); // todo Max parse 0x numbers
    }

    @Override
    public void onFail(Throwable e) {}
}
