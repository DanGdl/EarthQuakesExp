package android.app;

import android.content.Context;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class ProgressDialog {

    private boolean isShowing = false;
    private CharSequence title;
    private CharSequence message;

    public ProgressDialog(Context context) {
    }

    public void setTitle(CharSequence title){
        this.title = title;
    }

    public void setMessage(CharSequence message){
        this.message = message;
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void show() {
        isShowing = true;
    }

    public void dismiss(){
        isShowing = false;
    }
}
