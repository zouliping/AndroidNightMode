package example.org.androidnightmode;

import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import example.org.androidnightmode.fragment.NewFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private SharedPreferences config;

    private NightModeHelper mNightModeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNightModeHelper = new NightModeHelper(this, R.style.AppTheme_Light);

        config = getSharedPreferences("config", MODE_PRIVATE);

        int themeId = getThemeId();
        if (themeId != 0) {
            setTheme(themeId);
        }

        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_nav_view);
        setUpNavigationView(navigationView);

        navigationView.getMenu().performIdentifierAction(R.id.main_nav_item_new, 0);
    }

    private void setUpNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.main_nav_item_new:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, NewFragment.getInstance(getString(R.string.navigation_new))).commit();
                        break;
                    case R.id.main_nav_item_history:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, NewFragment.getInstance(getString(R.string.navigation_history))).commit();
                        break;
                    case R.id.main_nav_item_switch_night_mode_1:
                        changeNightModeUseUiModeManager();
                        break;
                    case R.id.main_nav_item_switch_night_mode_2:
                        changeNightModeChangeTheme();
                        break;
                    case R.id.main_nav_item_switch_night_mode_3:
                        mNightModeHelper.toggle();
                        break;
                }

                return true;
            }
        });
    }

    private void changeNightModeUseUiModeManager() {
        boolean isNightMode = getMode();
        isNightMode = !isNightMode;
        UiModeManager uiManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
        setMode(isNightMode);

        if (isNightMode) {
            uiManager.enableCarMode(0);
            uiManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
        }else {
            uiManager.disableCarMode(0);
            uiManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
        }
    }

    private void setMode(boolean isNightMode) {
        SharedPreferences.Editor editor = config.edit();
        editor.putBoolean("is_night_mode", isNightMode);
        editor.commit();
    }

    private boolean getMode() {
        return config.getBoolean("is_night_mode", false);
    }

    private void changeNightModeChangeTheme() {
        boolean isNightMode = getMode();
        isNightMode = !isNightMode;
        setMode(isNightMode);
        int themeId;

        if (isNightMode) {
            themeId = R.style.AppTheme_Dark;
        }else {
            themeId = R.style.AppTheme_Light;
        }

        setThemeId(themeId);
        this.recreate();
    }

    private void setThemeId(int themeId) {
        SharedPreferences.Editor editor = config.edit();
        editor.putInt("theme_id", themeId);
        editor.commit();
    }

    private int getThemeId() {
        return config.getInt("theme_id", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
