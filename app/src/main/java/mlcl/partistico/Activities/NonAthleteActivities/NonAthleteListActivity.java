package mlcl.partistico.Activities.NonAthleteActivities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import Database.BDNonAthlete;
import Model.NonAthleteCustomListAdapter;
import Model.Utils;
import mlcl.partistico.R;

public class NonAthleteListActivity extends AppCompatActivity {

    final NonAthleteListActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_athlete_list);

        handleIntent(getIntent());

        new GetListTask().execute();
    }

    private void search(String query) {
        List<BDNonAthlete> nonAthletes;
        if (query.equals(""))
            nonAthletes = Utils.getInstance().getNonAthletes();
        else
            nonAthletes = Utils.getInstance().getNonAthleteByName(query);

        ListView list = (ListView) findViewById(R.id.list_nonAthlete);
        NonAthleteCustomListAdapter adapter = new NonAthleteCustomListAdapter(this, nonAthletes);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Utils.getInstance().setActiveAthlete((Integer) view.getTag());
                Intent intent = new Intent(NonAthleteListActivity.this, NonAthleteProfileActivity.class);
                startActivity(intent);

            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    search(s);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    if (s.equals("")) {
                        search(s);
                    }
                    return false;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    private class GetListTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListView list = (ListView) findViewById(R.id.list_nonAthlete);
                    NonAthleteCustomListAdapter adapter = new NonAthleteCustomListAdapter(activity, Utils.getInstance().getNonAthletes());
                    list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            Utils.getInstance().setActiveNonAthlete((Integer) view.getTag());
                            Intent intent = new Intent(NonAthleteListActivity.this, NonAthleteProfileActivity.class);
                            startActivity(intent);
                        }
                    });
                    adapter.notifyDataSetChanged();

                    ProgressBar progress = (ProgressBar) findViewById(R.id.progress_bar_nonAthleteList);
                    progress.setVisibility(View.INVISIBLE);
                }
            });
            return null;
        }
    }
}
