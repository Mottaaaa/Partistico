package mlcl.partistico.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import Model.AthleteCustomListAdapter;
import Model.Utils;
import mlcl.partistico.R;

public class AthleteListActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_list);


        AthleteCustomListAdapter adapter = new AthleteCustomListAdapter(this);
        list = (ListView) findViewById(R.id.athleteList);
        list.setAdapter(adapter);
        //Utils.getInstance().setActivity(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                Utils.getInstance().setActiveAthlete((Integer) view.getTag());
                Intent intent = new Intent(AthleteListActivity.this, AthleteProfileActivity.class);
                startActivity(intent);

                //String Slecteditem = itemname[+position];
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }


}
