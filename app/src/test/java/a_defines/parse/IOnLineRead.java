package a_defines.parse;

public interface IOnLineRead {
    void onLineRead(String line);

    void onFail(Throwable e);
}
