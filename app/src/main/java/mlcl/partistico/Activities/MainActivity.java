package mlcl.partistico.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import Database.FirebaseAdapter;
import Model.Utils;
import mlcl.partistico.Activities.AthleteActivities.AthleteListActivity;
import mlcl.partistico.Activities.CompetitionActivities.CompetitionListActivity;
import mlcl.partistico.Activities.NonAthleteActivities.NonAthleteListActivity;
import mlcl.partistico.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PopulateDBTask populateTask = new PopulateDBTask();
        FirebaseAdapter fb = new FirebaseAdapter(getApplicationContext());

        populateTask.execute();

    }

    public void atheleteAction(View view) {

        Intent intent = new Intent(this, AthleteListActivity.class);
        startActivity(intent);
    }

    public void nonAthleteAction(View view) {
        Intent intent = new Intent(this, NonAthleteListActivity.class);
        startActivity(intent);
    }

    public void competitionAction(View view) {
        Intent intent = new Intent(this, CompetitionListActivity.class);
        startActivity(intent);
    }

    private class PopulateDBTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Utils.getInstance().setContext(getApplicationContext());
            Utils.getInstance().populateDB();

            //para tirar
            FirebaseAdapter f = new FirebaseAdapter(getApplicationContext());
            f.bosta();

            return null;
        }

    }
}
