package example.org.androidnightmode;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import example.org.androidnightmode.fragment.NewFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    case R.id.main_nav_item_random:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, NewFragment.getInstance(getString(R.string.navigation_random))).commit();
                        break;
                    case R.id.main_nav_item_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, NewFragment.getInstance(getString(R.string.navigation_settings))).commit();
                        break;
                }

                return true;
            }
        });
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
