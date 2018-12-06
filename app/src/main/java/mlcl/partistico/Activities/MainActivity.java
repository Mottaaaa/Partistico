package mlcl.partistico.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import Database.BDAthlete;
import Database.BDClub;
import Model.Utils;
import mlcl.partistico.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PopulateDBTask populateTask = new PopulateDBTask();
        populateTask.execute();

    }

    public void atheleteAction(View view) {

        Intent intent = new Intent(this, AthleteListActivity.class);
        startActivity(intent);
    }

    public void nonAthleteAction(View view) {
        /*
        Intent intent = new Intent(this, NonAthleteListActivity.class);
        startActivity(intent);
        */
    }

    private class PopulateDBTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Utils.getInstance().setContext(getApplicationContext());
            Utils.getInstance().populateDB();

            return null;
        }

    }
}
