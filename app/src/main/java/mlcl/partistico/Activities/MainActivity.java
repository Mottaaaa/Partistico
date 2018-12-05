package mlcl.partistico.Activities;

import android.content.Intent;
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

        Utils.getInstance().setContext(getApplicationContext());
        Utils.getInstance().populateDB();
        List<BDClub> teste = Utils.getInstance().getDBClubs();
        System.out.println(teste.size());
        List<BDAthlete> t2 = Utils.getInstance().getDBAthletes();
        System.out.println(t2.size());

        /*
        //inserção
        DatabaseAdapter dbAdapter = new DatabaseAdapter(getApplicationContext());
        dbAdapter.open();
        BDClub club1 = new BDClub("Teste",BitmapFactory.decodeResource(getResources(), R.drawable.hitler));
        dbAdapter.insertClub(club1);
        dbAdapter.close();
        dbAdapter.open();
        List<BDClub> clubs = dbAdapter.getClubs();
        dbAdapter.close();
        */

    }

    public void atheleteAction(View view){

        Intent intent = new Intent(this, AthleteListActivity.class);
        startActivity(intent);
    }

    public void atheleteProfileAction(View view){

        Intent intent = new Intent(this, AthleteProfileActivity.class);
        startActivity(intent);
    }
}
