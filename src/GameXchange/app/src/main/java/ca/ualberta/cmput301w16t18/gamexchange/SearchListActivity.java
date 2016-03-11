package ca.ualberta.cmput301w16t18.gamexchange;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class SearchListActivity extends AppCompatActivity {

    private ListView drawerListView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    //private CharSequence mTitle, mDrawerTitle;
    protected SearchListListViewArrayAdapter adapter;
    protected ListView listView;
    protected SearchListActivity searchListActivity;

    public GameList games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getIntent().getStringExtra(Constants.SEARCH_LIST_ACTIVITY_ACTION);
        games = new GameList();
        searchListActivity = this;
        if(type == null) {
            Log.d("Null Pointer", "Intent for SearchListActivity was started without the SEARCH_LIST_ACTIVITY_ACTION added");
        } else if(type.equals(Constants.BORROWED_GAMES)) {
            // Load Borrowed Games
            setTitle("Borrowed Games");
            //TODO: Make specific function for this
            ElasticSearcher.receiveAllGames(this, "SearchListActivity");
        } else if(type.equals(Constants.WISH_LIST)) {
            // Load Wish List
            setTitle("Wish List");
            //TODO: Make specific function for this
            ElasticSearcher.receiveAllGames(this, "SearchListActivity");
        } else {
            //Default to my Games
            setTitle("My Games");
            //TODO: Make specific function for this
            ElasticSearcher.receiveAllGames(this, "SearchListActivity");
        }
        setContentView(R.layout.activity_search_list);

        //Create Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.left_drawer);
        // set adapter for drawer ListView
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.navigation_drawer_items_section)));
        //Set onclick listener
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch(NullPointerException ex) {
            Log.d("Navigation Drawer", "Got a null pointer from get support action bar. " + ex.getMessage());
        }
        getSupportActionBar().setHomeButtonEnabled(true);

        //mTitle = mDrawerTitle = getTitle().toString();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //Initialize ListView
        listView = (ListView) findViewById(R.id.searchListActivityListView);
        adapter = new SearchListListViewArrayAdapter(this, games.getGames());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchListActivity.this, GameProfileViewActivity.class);
                String gameID = games.getGames().get(position).getId();
                intent.putExtra(Constants.GAME_ID, gameID);
                startActivity(intent);
            }
        });

        //Initialize Gesture detector

        CustomGestureDetector mDetector = new CustomGestureDetector(this, SearchListActivity.this, listView);
        listView.setOnTouchListener(mDetector);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        new MaterialShowcaseView.Builder(this)
                .setTarget(findViewById(R.id.tutorialTextView))
                .setDismissText("GOT IT")
                .setContentText("Touch a game to get details !!! " +
                        "Touch the hamburger icon in the top left corner to navigate around " +
                        "the app!! Also swipe left on a game to delete!!")
                .setDelay(1) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse("list view")// provide a unique ID used to ensure it is only shown once
                .show();
        TextView view = (TextView) findViewById(R.id.tutorialTextView);
        view.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        //Modified from http://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm-that-the-user-wishes-to-exit-an-android-activity
        new AlertDialog.Builder(this).setMessage(R.string.alert_dialog_message)
                .setPositiveButton(R.string.alert_dialog_logout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Constants.USER_ID = null;
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }



    public void setDisplayedList (GameList gameList) {
        games.clear();
        games.addAll(gameList);
        adapter.notifyDataSetChanged();
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(drawerListView);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addGame(Game mygame, String userId) {
        //implements US 01.01.01
        //add a game to a user's list
        games.add(mygame);
    }

    public void deleteGame(Game mygame) {
        //implements US 01.05.01
        //deletes a game from a user's list
        games.removeGame(mygame);
    }

    public void deleteGameByPosition(int position) {
        deleteGame(games.getGames().get(position));
        adapter.notifyDataSetChanged();
    }

    public void loadOwnedGames(String userId) {
        //implements US 01.02.01
        //pull list of games for specified user and display it
    }

    public void loadBorrowingGames(String userId) {
        //implements US 06.01.01
        //pull list of games that I am borrowing and display it
    }

    public void loadBorrowedGames(String userId) {
        //implements US 06.02.01
        //pull list of games that are borrowed from me and display it
    }

    public void searchGames(String searchString) {
        //implements US 04.01.01 and US 04.02.01
        //search for all available games given searchString
    }

    public void addToWatchlist(Game mygame) {
        //implements US 04.03.01
        //add a game to my watchlist
    }

    public void editButtonClicked() {

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            //Use position to navigate to the right dataset.
            //Added Switch cases to control where the buttons will go
            Intent intent;
            switch (position){
                case 0:
                    setTitle("My Games");
                    //TODO: Make specific function for this
                    ElasticSearcher.receiveAllGames(searchListActivity, "SearchListActivity");
                    mDrawerLayout.closeDrawers();
                    break;
                case 1:
                    setTitle("Borrowed Games");
                    //TODO: Make specific function for this
                    ElasticSearcher.receiveAllGames(searchListActivity, "SearchListActivity");
                    mDrawerLayout.closeDrawers();
                    break;
                case 2:
                    setTitle("Wish List");
                    //TODO: Make specific function for this
                    ElasticSearcher.receiveAllGames(searchListActivity, "SearchListActivity");
                    mDrawerLayout.closeDrawers();
                    break;
                case 3:
                    mDrawerLayout.closeDrawers();
                    Toast.makeText(SearchListActivity.this,"No searching for you",Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    intent = new Intent(SearchListActivity.this, UserProfileViewActivity.class);
                    startActivity(intent);
                    break;
                case 5:
                    Constants.USER_ID = null;
                    finish();
                    break;
            }
        }
    }
}
