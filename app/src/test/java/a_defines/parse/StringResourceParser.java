package a_defines.parse;

public class StringResourceParser extends ResourceParser<String, String> implements IOnLineRead {
    private final String ST_TAG = "<string name=\"";
    private final int ST_TAG_LENGTH = ST_TAG.length();

    private final String MID_TAG = "\">";
    private final int MID_TAG_LENGTH = MID_TAG.length();

    private final String EN_TAG1 = "/>";
    private final String EN_TAG2 = "</string>";

    @Override
    public void onLineRead(String line) {
        if(line.contains("resources")){
            return;
        }
        int stIndex;
        int endIndex;
        boolean hasMiddleTag = true;
        if((stIndex = line.indexOf(ST_TAG)) != -1){
            stIndex += ST_TAG_LENGTH;
        }

        endIndex = line.indexOf(MID_TAG);
        if(endIndex == -1){
            hasMiddleTag = false;
            endIndex = line.indexOf(EN_TAG1);
        }
        String key = getSubSequence(line, stIndex, endIndex);


        if(hasMiddleTag){
            stIndex = endIndex + MID_TAG_LENGTH;
        }
        endIndex = line.indexOf(EN_TAG2);
        String value = getSubSequence(line, stIndex, endIndex);

        MAP.put("string_" + key, value);
    }

    @Override
    public void onFail(Throwable e) {}
}
