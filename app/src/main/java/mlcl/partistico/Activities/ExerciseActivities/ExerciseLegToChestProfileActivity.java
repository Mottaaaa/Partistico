package mlcl.partistico.Activities.ExerciseActivities;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import Model.Utils;
import mlcl.partistico.R;

public class ExerciseLegToChestProfileActivity extends AppCompatActivity implements SensorEventListener {

    private float[] mGravity = new float[3];
    private float[] mGeomagnetic = new float[3];
    private float azimuth = 0f;
    private float currentAzimuth = 0f;
    private SensorManager sensorManager;

    private LineGraphSeries<DataPoint> seriesHorizontal;
    private LineGraphSeries<DataPoint> seriesVertical;

    private GraphView graphHorizontal;
    private GraphView graphVertical;
    private int indexHorizontal = 1;
    private int indexVertical = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_leg_to_chest_profile);

        getSupportActionBar().setTitle(Utils.getInstance().getActiveExercise().getName());

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        graphHorizontal = (GraphView) findViewById(R.id.graph_horizontal);
        graphVertical = (GraphView) findViewById(R.id.graph_vertical);

        seriesHorizontal = new LineGraphSeries<>();
        seriesVertical = new LineGraphSeries<>();

        //set up horizontal graph
        graphHorizontal.getViewport().setScrollable(true);
        graphHorizontal.getViewport().setXAxisBoundsManual(true);
        graphHorizontal.getViewport().setMinX(0);
        graphHorizontal.getViewport().setMaxX(100);
        graphHorizontal.getViewport().setYAxisBoundsManual(true);
        graphHorizontal.getViewport().setMinY(0);
        graphHorizontal.getViewport().setMaxY(180);
        graphHorizontal.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graphHorizontal.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graphHorizontal.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graphHorizontal.getGridLabelRenderer().setPadding(0);
        graphHorizontal.getGridLabelRenderer().setGridColor(255);
        graphHorizontal.addSeries(seriesHorizontal);

        //set up vertical graph
        graphVertical.getViewport().setScrollable(true);
        graphVertical.getViewport().setXAxisBoundsManual(true);
        graphVertical.getViewport().setYAxisBoundsManual(true);
        graphVertical.getViewport().setMinX(0);
        graphVertical.getViewport().setMaxX(50);
        //graphVertical.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        //graphVertical.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graphVertical.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graphVertical.getGridLabelRenderer().setPadding(0);
        graphVertical.getGridLabelRenderer().setGridColor(255);
        graphVertical.addSeries(seriesVertical);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
        sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //rotation
        final float alpha = 0.97f;
        synchronized (this) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                mGravity[0] = alpha * mGravity[0] + (1 - alpha) * event.values[0];
                mGravity[1] = alpha * mGravity[1] + (1 - alpha) * event.values[1];
                mGravity[2] = alpha * mGravity[2] + (1 - alpha) * event.values[2];

                //inclination
                float[] g = new float[3];
                g = event.values.clone();

                double norm_Of_g = Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2]);

                // Normalize the accelerometer vector
                g[0] = (float) (g[0] / norm_Of_g);
                g[1] = (float) (g[1] / norm_Of_g);
                g[2] = (float) (g[2] / norm_Of_g);

                //inclination can be calculated as
                int inclination = (int) Math.round(Math.toDegrees(Math.acos(g[2])));

                //if (inclination < 25 || inclination > 155) {
                // device is flat
                //outputX.setText("Flat");
                //} else {
                // device is not flat
                //outputX.setText("Not Flat");
                //}

                seriesHorizontal.appendData((new DataPoint(indexHorizontal, inclination)), true, 100);
                graphHorizontal.onDataChanged(false, false);
                indexHorizontal++;
            }
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                mGeomagnetic[0] = alpha * mGeomagnetic[0] + (1 - alpha) * event.values[0];
                mGeomagnetic[1] = alpha * mGeomagnetic[1] + (1 - alpha) * event.values[1];
                mGeomagnetic[2] = alpha * mGeomagnetic[2] + (1 - alpha) * event.values[2];
            }

            float r[] = new float[9];
            float i[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(r, i, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(r, orientation);

                seriesVertical.appendData((new DataPoint(indexVertical, orientation[0])), true, 100);

                graphVertical.getViewport().setMinY(orientation[0] - 1);
                graphVertical.getViewport().setMaxY(orientation[0] + 1);

                graphVertical.onDataChanged(false, false);
                indexVertical++;
            }
        }
    }
}
