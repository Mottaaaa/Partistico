package mlcl.partistico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

public class AthleteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_list);

        //getActionBar().setTitle("@string/athelete_list_activity");
        //getSupportActionBar().setTitle("@string/athelete_list_activity");  // provide compatibility to all the versions
    }
}
