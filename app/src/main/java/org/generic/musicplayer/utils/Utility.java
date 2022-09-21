package org.generic.musicplayer.utils;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Utility {
    private static FragmentInitializer fragmentInitializer;

    public static void setFragmentInitializer(FragmentInitializer initializer) {
        fragmentInitializer = initializer;
    }


    // transaction.beginTransaction() must be called before invoking this method
    public static void displayFragment(final String TAG, String debug, int navTitle, Class<? extends Fragment> fragment) {
        if (fragmentInitializer == null || !fragmentInitializer.isValid()) {
            throw new IllegalArgumentException("fragmentInitializer not initialized correctly");
        }

        Log.e(TAG, debug);
        Toolbar toolbar = fragmentInitializer.getToolbar();
        int container = fragmentInitializer.getContainerView();
        int transactionType = fragmentInitializer.getAddType();
        FragmentTransaction transaction = fragmentInitializer.getFragmentTransaction();
        DrawerLayout drawer = fragmentInitializer.getDrawerLayout();

        if (toolbar != null) {
            toolbar.setTitle(navTitle);
        }
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }

        switch (transactionType) {
            case FragmentInitializer.TYPE_REPLACE:
                transaction.replace(container, fragment, fragmentInitializer.getBundle());
                break;
            case FragmentInitializer.TYPE_ADD_TO_BACKSTACK:
                transaction.addToBackStack(debug);
                break;
            case FragmentInitializer.TYPE_BOOK_PAGE:
                transaction.replace(container, fragment, fragmentInitializer.getBundle());
                transaction.addToBackStack(debug);
                break;
        }

        transaction.commit();
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
