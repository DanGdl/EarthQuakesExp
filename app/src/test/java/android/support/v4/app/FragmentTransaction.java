package android.support.v4.app;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public abstract class FragmentTransaction {

    private final FragmentManager FM;
    private Fragment fragment;
    private String tag;
    private int containerViewId;
    private boolean addToBackStack = false;
    private String transactionTag;

    FragmentTransaction(FragmentManager fragmentManager) {
        this.FM = fragmentManager;
    }

    public FragmentTransaction add(Fragment fragment, String tag) {
        this.fragment = fragment;
        this.tag = tag;
        return this;
    }

    public FragmentTransaction add(int containerViewId, Fragment fragment) {
        this.fragment = fragment;
        this.containerViewId = containerViewId;
        return this;
    }

    public FragmentTransaction add(int containerViewId, Fragment fragment, @Nullable String tag) {
        this.fragment = fragment;
        this.tag = tag;
        this.containerViewId = containerViewId;
        return this;
    }

    public FragmentTransaction replace(int containerViewId, Fragment fragment) {
        this.fragment = fragment;
        this.containerViewId = containerViewId;
        return this;
    }

    public FragmentTransaction replace(int containerViewId, Fragment fragment, @Nullable String tag) {
        this.fragment = fragment;
        this.tag = tag;
        this.containerViewId = containerViewId;
        return this;
    }

    public FragmentTransaction remove(Fragment fragment) {
        this.fragment = fragment;
        return this;
    }

    public FragmentTransaction hide(Fragment fragment) {
        return this;
    }

    public FragmentTransaction show(Fragment fragment) {
        return this;
    }

    public FragmentTransaction detach(Fragment fragment) {
        return this;
    }

    public FragmentTransaction attach(Fragment fragment) {
        return this;
    }

    public boolean isEmpty() {
        return false;
    }

    public FragmentTransaction setCustomAnimations(int enter, int exit) {
        return this;
    }

    public FragmentTransaction setCustomAnimations(int enter, int exit, int popEnter, int popExit) {
        return this;
    }

    public FragmentTransaction addSharedElement(View sharedElement, String name) {
        return this;
    }

    public FragmentTransaction setTransition(int transit) {
        return this;
    }

    public FragmentTransaction setTransitionStyle(int styleRes) {
        return this;
    }

    public FragmentTransaction addToBackStack(@Nullable String name) {
        addToBackStack = true;
        transactionTag = name;
        return this;
    }

    public boolean isAddToBackStackAllowed() {
        return false;
    }

    public FragmentTransaction disallowAddToBackStack() {
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int res) {
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(CharSequence text) {
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(int res) {
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(CharSequence text) {
        return this;
    }

    public FragmentTransaction setAllowOptimization(boolean allowOptimization) {
        return this;
    }

    public int commit() {
        FM.onCommitTransaction(this);
        return 0;
    }

    public int commitAllowingStateLoss() {
        FM.onCommitTransaction(this);
        return 0;
    }

    public void commitNow() {
        FM.onCommitTransaction(this);
    }

    public void commitNowAllowingStateLoss() {
        FM.onCommitTransaction(this);
    }

    public boolean isAddToBackStack() {
        return addToBackStack;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
