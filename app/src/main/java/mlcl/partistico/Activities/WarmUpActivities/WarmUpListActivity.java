package mlcl.partistico.Activities.WarmUpActivities;

import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import Database.BDWarmUp;
import Model.CustomListAdapters.WarmUpCustomListAdapter;
import Model.Utils;
import mlcl.partistico.R;

public class WarmUpListActivity extends AppCompatActivity {

    ListView list;
    final Activity activity = this;
    Dialog dialog;
    EditText name;
    int warmupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warm_up_list);

        handleIntent(getIntent());

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_warmup_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        new GetListTask().execute();
    }

    private void search(String query) {
        List<BDWarmUp> warmUps;

        if (query.equals(""))
            warmUps = Utils.getInstance().getBDWarmUps();
        else
            warmUps = Utils.getInstance().getBDWarmUpByName(query);

        ListView list = (ListView) findViewById(R.id.list_warm_ups);
        WarmUpCustomListAdapter adapter = new WarmUpCustomListAdapter(this, warmUps);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Utils.getInstance().setActiveCompetition((Integer) view.getTag());
                Intent intent = new Intent(WarmUpListActivity.this, WarmUpProfileActivity.class);
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

    public void addPopup(View view){
        name = dialog.findViewById(R.id.warmup_name);
        dialog.show();
        Button accept = (Button) dialog.findViewById(R.id.accept);
        Button reject = (Button) dialog.findViewById(R.id.reject);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().equals("")) {
                    Utils.getInstance().insertBDWarmUp(new BDWarmUp(name.getText().toString()));
                    new GetListTask().execute();
                    dialog.dismiss();
                }
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                dialog.dismiss();
            }
        });
    }

    public void editPopup(View view){
        LinearLayout linearLayout1 = (LinearLayout)view.getParent();
        FrameLayout frameLayout1 = (FrameLayout) linearLayout1.getParent();
        LinearLayout linearLayout2 = (LinearLayout)frameLayout1.getParent();
        Utils.getInstance().setActiveWarmup((Integer) linearLayout2.getTag());
        BDWarmUp temp = Utils.getInstance().getActiveWarmup();
        warmupID = temp.getId();
        name = dialog.findViewById(R.id.warmup_name);
        name.setText(temp.getName());
        dialog.show();
        Button accept = (Button) dialog.findViewById(R.id.accept);
        Button reject = (Button) dialog.findViewById(R.id.reject);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("")) {
                    Utils.getInstance().updateBDWarmup(warmupID, name.getText().toString());
                    new GetListTask().execute();
                    dialog.dismiss();
                }
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                dialog.dismiss();
            }
        });
    }

    public void delete(View view){

        LinearLayout linearLayout1 = (LinearLayout)view.getParent();
        FrameLayout frameLayout1 = (FrameLayout) linearLayout1.getParent();
        LinearLayout linearLayout2 = (LinearLayout)frameLayout1.getParent();
        Utils.getInstance().setActiveWarmup((Integer) linearLayout2.getTag());
        int id = Utils.getInstance().getActiveWarmup().getId();
        Utils.getInstance().deleteBDWarmup(id);
        new GetListTask().execute();

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
                    ListView list = (ListView) findViewById(R.id.list_warm_ups);
                    WarmUpCustomListAdapter adapter = new WarmUpCustomListAdapter(activity, Utils.getInstance().getBDWarmUps());
                    list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            Utils.getInstance().setActiveWarmup((Integer) view.getTag());

                            //Intent intent = new Intent(WarmUpListActivity.this, WarmUpProfileActivity.class);
                            //startActivity(intent);

                        }
                    });
                    adapter.notifyDataSetChanged();

                    ProgressBar progress = (ProgressBar) findViewById(R.id.progress_bar_warm_upsList);
                    progress.setVisibility(View.INVISIBLE);
                }
            });

            return null;
        }
    }

}
