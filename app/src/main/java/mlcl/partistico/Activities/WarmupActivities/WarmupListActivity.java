package mlcl.partistico.Activities.WarmupActivities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import java.security.CodeSigner;

import Database.BDWarmup;
import Model.Utils;
import Model.WarmupCustomListAdapter;
import mlcl.partistico.R;

public class WarmupListActivity extends AppCompatActivity {

    final WarmupListActivity activity = this;
    private View layout;
    private PopupWindow popup;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warmup_list);


        popup = new PopupWindow(this);
        layout = getLayoutInflater().inflate(R.layout.add_warmup_popup, null);
        popup.setContentView(layout);
        popup.setFocusable(true);
        popup.setOutsideTouchable(false);
        popup.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popup.setHeight(WindowManager.LayoutParams.MATCH_PARENT);


        addBtn = (Button) findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAction(v);
            }
        });

        new GetListTask().execute();
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
                    //search(s);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    if (s.equals("")) {
                        //search(s);
                    }
                    return false;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);

    }

    private void addAction(View view){
        popup.showAtLocation(layout, Gravity.CENTER, 50, 50);
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

    private class GetListTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListView list = (ListView) findViewById(R.id.list_warmup);
                    WarmupCustomListAdapter adapter = new WarmupCustomListAdapter(activity, Utils.getInstance().getWarmups());
                    list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            Utils.getInstance().setActiveWarmup((Integer) view.getTag());
                            //Intent intent = new Intent(WarmupCustomListAdapter.this, NonAthleteProfileActivity.class);
                            //startActivity(intent);
                        }
                    });
                    adapter.notifyDataSetChanged();

                    ProgressBar progress = (ProgressBar) findViewById(R.id.progress_bar_warmupList);
                    progress.setVisibility(View.INVISIBLE);
                }
            });
            return null;
        }
    }

    private class InsertListTask extends AsyncTask<BDWarmup, Void, Void> {


        @Override
        protected Void doInBackground(BDWarmup... warmups) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
            return null;
        }
    }
}
