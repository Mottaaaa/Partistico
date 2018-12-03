package mlcl.partistico.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import Model.Athlete;
import Model.AthleteCustomListAdapter;
import mlcl.partistico.R;

public class AthleteListActivity extends AppCompatActivity {

    ListView list;
    Athlete[] athletes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_list);

        athletes = new Athlete[2];
        athletes[0] = new Athlete("BOSTA","BOSTA","BOSTA","BOSTA");
        athletes[1] = new Athlete("BOSTA","BOSTA","BOSTA","BOSTA");

        AthleteCustomListAdapter adapter = new AthleteCustomListAdapter(this, athletes);
        list = (ListView) findViewById(R.id.athleteList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //String Slecteditem = itemname[+position];
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }


}
