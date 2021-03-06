package mlcl.partistico.Activities.ExerciseActivities;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import Model.Utils;
import mlcl.partistico.R;

public class ExerciseAirplaneProfileActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    //private TextView outputX;

    private LineGraphSeries<DataPoint> series;

    private GraphView graph;
    private int index = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_airplane_profile);

        getSupportActionBar().setTitle(Utils.getInstance().getActiveExercise().getName());

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //outputX = (TextView) findViewById(R.id.textViewX);

        graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<>();

        graph.getViewport().setScrollable(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(50);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(180);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graph.getGridLabelRenderer().setPadding(0);
        graph.getGridLabelRenderer().setGridColor(255);
        graph.addSeries(series);

        VideoView videoView = (VideoView) findViewById(R.id.vv_video);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_exercise_airplane;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
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
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
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

        if (inclination < 25 || inclination > 155) {
            // device is flat
            //outputX.setText("Flat");
        } else {
            // device is not flat
            //outputX.setText("Not Flat");
        }

        if (inclination <= 90) {
            series.appendData((new DataPoint(index, inclination + 90)), true, 100);
        } else {
            series.appendData((new DataPoint(index, inclination - 90)), true, 100);
        }

        //********graph.getViewport().setMinY(inclination - 10);
        //********graph.getViewport().setMaxY(inclination + 10);

        graph.onDataChanged(false, false);
        index++;
    }
}



