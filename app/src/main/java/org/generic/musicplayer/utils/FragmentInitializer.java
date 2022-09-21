package org.generic.musicplayer.utils;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

public class FragmentInitializer {
    public static final int TYPE_REPLACE = 0;
    public static final int TYPE_ADD_TO_BACKSTACK = 1;

    /**
     * This custom type make a fragment manager behave like a book page.
     * When you start reading a book, you first get to page 1 up to page n
     * then when you want to go back, you need to traverse the page from n-1
     * (improving this documentation soon :P)
     */
    public static final int TYPE_BOOK_PAGE = 2;

    private Toolbar toolbar;
    private int containerView;
    private int addType;
    private FragmentTransaction fragmentTransaction;
    private DrawerLayout drawerLayout;
    private Bundle bundle;
    private FragmentInitializer.Builder builder;

    private FragmentInitializer() {}

    public Toolbar getToolbar() {
        return toolbar;
    }

    public int getContainerView() {
        return containerView;
    }

    public FragmentTransaction getFragmentTransaction() {
        return fragmentTransaction;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public int getAddType() {
        return addType;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public Builder getBuilder() {
        return builder;
    }

    public boolean isValid() {
        return fragmentTransaction != null;
    }

    public static class Builder {

        private FragmentInitializer fragmentInitializer;

        public Builder() {
            fragmentInitializer = new FragmentInitializer();
        }

        public FragmentInitializer.Builder setToolbar(Toolbar toolbar) {
            fragmentInitializer.toolbar = toolbar;
            return this;
        }

        public FragmentInitializer.Builder setContainerView(int containerView) {
            fragmentInitializer.containerView = containerView;
            return this;
        }

        public FragmentInitializer.Builder setFragmentTransaction(FragmentTransaction fragmentTransaction) {
            fragmentInitializer.fragmentTransaction = fragmentTransaction;
            return this;
        }

        public FragmentInitializer.Builder setDrawerLayout(DrawerLayout drawerLayout) {
            fragmentInitializer.drawerLayout = drawerLayout;
            return this;
        }

        public FragmentInitializer.Builder setAddType(int addType) {
            fragmentInitializer.addType = addType;
            return this;
        }

        public FragmentInitializer.Builder setBundle(Bundle bundle) {
            fragmentInitializer.bundle = bundle;
            return this;
        }

        public FragmentInitializer build() {
            fragmentInitializer.builder = this;
            return fragmentInitializer;
        }

    }
}
