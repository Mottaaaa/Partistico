package mlcl.partistico.Activities.ExerciseActivities;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import mlcl.partistico.R;

public class ExerciseProfileActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    private Sensor sensor;
    TextView outputX;
    TextView outputY;
    TextView outputZ;

    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_profile);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        outputX = (TextView) findViewById(R.id.textViewX);
        outputY = (TextView) findViewById(R.id.textViewY);
        outputZ = (TextView) findViewById(R.id.textViewZ);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), sensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] g = new float[3];
        g = event.values.clone();

        double norm_Of_g = Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2]);

        // Normalize the accelerometer vector
        g[0] = (float) (g[0] / norm_Of_g);
        g[1] = (float) (g[1] / norm_Of_g);
        g[2] = (float) (g[2] / norm_Of_g);

        //inclination can be calculated as
        int inclination = (int) Math.round(Math.toDegrees(Math.acos(g[2])));

        if (inclination < 25 || inclination > 155)
        {
            // device is flat
            outputX.setText("Flat");
        }
        else
        {
            // device is not flat
            outputX.setText("Not Flat");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
