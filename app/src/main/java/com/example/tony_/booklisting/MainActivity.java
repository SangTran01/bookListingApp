package com.example.tony_.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Book>> {

    private String JSON_RESPONSE = "https://www.googleapis.com/books/v1/volumes?q=Butterfly&maxResults=10&key=";
    private String BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    private String API_KEY = "";
    private int LOADER_ID_1 = 1;

    private BookAdapter mAdapter;
    private ListView listView;
    private TextView emptyTextView;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("MainActivity", "CREATED");
        setContentView(R.layout.activity_main);

        final EditText searchEditText = findViewById(R.id.search_edittext);
        Button searchButton = findViewById(R.id.search_btn);
        mProgress = findViewById(R.id.progress);
        emptyTextView = findViewById(R.id.no_books);
        listView = findViewById(R.id.list);
        listView.setEmptyView(emptyTextView);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = searchEditText.getText().toString().trim();
                Uri baseUri = Uri.parse(BASE_URL);
                Uri.Builder builder = baseUri.buildUpon();

                builder.appendQueryParameter("q",input);
                builder.appendQueryParameter("maxResults", "10");
                builder.appendQueryParameter("key", API_KEY);

                String completedUri = builder.toString();
                startBookSearch(completedUri);
            }
        });


        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(LOADER_ID_1, null, this);
        } else {
            mProgress.setVisibility(View.GONE);
            // Update empty state with no connection error message
            emptyTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.v("MainActivity", "LOADER CREATED");
        return new BookLoader(this, JSON_RESPONSE);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        Log.v("MainActivity", "LOADER FINISHED");
        //hide progress then display text or list
        mProgress.setVisibility(View.GONE);
        emptyTextView.setText(R.string.no_books_available);
        emptyTextView.setVisibility(View.VISIBLE);
        //updateUI
        mAdapter.clear();

        if (books != null && !books.isEmpty()) {
            Log.v("MainActivity", "LOADER we have books");
            listView.setVisibility(View.VISIBLE);
            listView.bringToFront();

            mAdapter.addAll(books);

            listView.setAdapter(mAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "Row " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.v("MainActivity", "LOADER RESET");
        mAdapter.clear();
    }

    public void startBookSearch(String SearchedURL) {
        Log.v("MainActivity", "LOADER clicked");

        mProgress.setVisibility(View.VISIBLE);
        mProgress.bringToFront();
        listView.setVisibility(View.GONE);
        emptyTextView.setText("");
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.v("MainActivity", "LOADER here1");

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            JSON_RESPONSE = SearchedURL;
            getLoaderManager().restartLoader(LOADER_ID_1, null, this);
        } else {
            Log.v("MainActivity", "LOADER here2");
            mProgress.setVisibility(View.GONE);
            // Update empty state with no connection error message
            emptyTextView.setText(R.string.no_internet_connection);
            emptyTextView.setVisibility(View.VISIBLE);
        }
    }
}
