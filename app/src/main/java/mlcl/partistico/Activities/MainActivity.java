package mlcl.partistico.Activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import Model.Utils;
import mlcl.partistico.Activities.AthleteActivities.AthleteListActivity;
import mlcl.partistico.Activities.CompetitionActivities.CompetitionListActivity;
import mlcl.partistico.Activities.NonAthleteActivities.NonAthleteListActivity;
import mlcl.partistico.Activities.ExerciseActivities.ExerciseProfileActivity;
import mlcl.partistico.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Descomentar para testar o Splash Screen
        /*
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);


        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);

        PopulateDBTask populateTask = new PopulateDBTask();
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

    public void exerciseAction(View view) {
        Intent intent = new Intent(this, ExerciseProfileActivity.class);
        startActivity(intent);
    }

    private class PopulateDBTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //getApplicationContext().deleteDatabase("Database.db");
            Utils.getInstance().setContext(getApplicationContext());
            //FirebaseAdapter.createTempFiles();
            //new FirebaseAdapter(getApplicationContext(),null).populate();
            //Utils.getInstance().populateDB();
            Utils.getInstance().populateExercises();
            Utils.getInstance().getDataFromFirebase();


            return null;
        }
    }
}
