package mlcl.partistico.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mlcl.partistico.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
