package org.generic.musicplayer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import org.generic.musicplayer.R;
import org.generic.musicplayer.fragment.HomeFragment;
import org.generic.musicplayer.fragment.MyMusicFragment;
import org.generic.musicplayer.fragment.WatchFragment;
import org.generic.musicplayer.utils.FragmentInitializer;
import org.jetbrains.annotations.NotNull;

import static org.generic.musicplayer.utils.Utility.displayFragment;
import static org.generic.musicplayer.utils.Utility.setFragmentInitializer;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MainActivity";
    Context context;

    Toolbar toolbarNavigation;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("test", 1);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.container_view, HomeFragment.class, bundle)
                    .commit();
        }
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        toolbarNavigation = findViewById(R.id.toolbar_navigation_top);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        initializeToolbar();
    }

    private void initializeToolbar() {
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, toolbarNavigation,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setSupportActionBar(toolbarNavigation);
        getSupportActionBar().setBackgroundDrawable(
                AppCompatResources.getDrawable(context, R.color.colorPrimary)
        );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager
                .beginTransaction()
                .setReorderingAllowed(true);

        FragmentInitializer initializer = new FragmentInitializer.Builder()
                .setToolbar(toolbarNavigation)
                .setContainerView(R.id.container_view)
                .setFragmentTransaction(transaction)
                .setDrawerLayout(drawer)
                .setAddType(FragmentInitializer.TYPE_BOOK_PAGE)
                .build();

        setFragmentInitializer(initializer);

        switch (item.getItemId()) {
            case R.id.home: {
                if (toolbarNavigation.getTitle().toString().equalsIgnoreCase("Home")) {
                    clearBackStackEntries(fragmentManager, transaction);
                    break;
                }
                initializer = initializer.getBuilder().setAddType(FragmentInitializer.TYPE_REPLACE).build();
                setFragmentInitializer(initializer);
                displayFragment(TAG, HomeFragment.TAG, R.string.home, HomeFragment.class);
                break;
            }
            case R.id.my_music: {
                displayFragment(TAG, MyMusicFragment.TAG, R.string.my_music, MyMusicFragment.class);
                break;
            }
            case R.id.watch: {
                displayFragment(TAG, WatchFragment.TAG, R.string.watch, WatchFragment.class);
                break;
            }
            default: {
                Log.e(TAG, "default");
                toolbarNavigation.setTitle(R.string.app_name);
                drawer.closeDrawer(GravityCompat.START);
                transaction.commit();
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Log.e(TAG, String.format("entry count -> %s", fragmentManager != null ? "" + fragmentManager.getBackStackEntryCount() : "null"));
//
//        if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
//            int backStacks = fragmentManager.getBackStackEntryCount();
//            for (int i = 0; i < backStacks; i++) {
//                fragmentManager.popBackStack();
//            }
//            navigationView.getMenu().getItem(0).setChecked(true);
//            toolbarNavigation.setTitle(R.string.home);
//            return;
//        }

        super.onBackPressed();
    }

    private void clearBackStackEntries(FragmentManager fragmentManager, FragmentTransaction transaction) {
        if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
            int backStacks = fragmentManager.getBackStackEntryCount();
            for (int i = 0; i < backStacks; i++) {
                fragmentManager.popBackStack();
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        transaction.commit();
    }
}