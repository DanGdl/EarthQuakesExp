package android.support.v4.app;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class FragmentManager {
    private final Deque<FragmentTransaction> STACK = new ArrayDeque<>();
    private final AppCompatActivity activity;

    public FragmentManager(AppCompatActivity activity) {
        this.activity = activity;
    }

    public android.support.v4.app.FragmentTransaction beginTransaction(){
        return new FragmentTransaction(this){};
    }

    void onCommitTransaction(FragmentTransaction transaction) {
        // todo Dan: create stack to put transaction here
        if(transaction.isAddToBackStack()){
            STACK.push(transaction);
        }
        else{
            STACK.poll();
            STACK.push(transaction);
        }
        Fragment fragment = transaction.getFragment();
        fragment.onAttach(activity);
        activity.onAttachFragment(fragment);
    }

    public boolean isEmpty() {
        return STACK.isEmpty();
    }

    public FragmentTransaction peek() {
        return STACK.peek();
    }
}
