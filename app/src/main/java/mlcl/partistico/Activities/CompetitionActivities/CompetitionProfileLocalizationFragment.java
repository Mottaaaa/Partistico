package mlcl.partistico.Activities.CompetitionActivities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import Model.GpsTracker;
import Model.Utils;
import mlcl.partistico.Activities.MainActivity;
import mlcl.partistico.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompetitionProfileLocalizationFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private GpsTracker gpsTracker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_competition_profile_localization, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        ImageButton button = (ImageButton) view.findViewById(R.id.btn_directions);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directionsAction(v);
            }
        });

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        double[] coordinatesArray = Utils.getInstance().getActiveCompetition().getCoordinatesArray();
        float zoomLevel = 12.0f; //This goes up to 21
        LatLng coordinates = new LatLng(coordinatesArray[0], coordinatesArray[1]);

        mMap.addMarker(new MarkerOptions().position(coordinates));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, zoomLevel));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if (view != null) {

            try {

                double[] coordinates = Utils.getInstance().getActiveCompetition().getCoordinatesArray();

                TextView tvAddress = (TextView) getView().findViewById(R.id.tv_address);
                TextView tvLocation = (TextView) getView().findViewById(R.id.tv_location);

                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(coordinates[0], coordinates[1], 1);

                String city = addresses.get(0).getLocality();
                String country = addresses.get(0).getCountryName();

                tvAddress.setText(Utils.getInstance().getActiveCompetition().getLocation());
                tvLocation.setText(city + ", " + country);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void directionsAction(View view) {

        String[] originCoordinates = getLocation(view);
        double[] destinationCoordinates = Utils.getInstance().getActiveCompetition().getCoordinatesArray();
        String strUri = "";

        if (!originCoordinates[0].equals("0.0") && !originCoordinates[1].equals("0.0")) {

            strUri = "https://www.google.com/maps/dir/?api=1";
            strUri += "&origin=" + originCoordinates[0] + "," + originCoordinates[1];
            strUri += "&destination=" + destinationCoordinates[0] + "," + destinationCoordinates[1];
        } else {

            strUri = "http://www.google.com/maps/place/";
            strUri += destinationCoordinates[0] + "," + destinationCoordinates[1];
        }

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
        startActivity(intent);
    }

    public String[] getLocation(View view) {

        String[] arr = new String[2];
        gpsTracker = new GpsTracker(Utils.getInstance().getContext());
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            arr[0] = String.valueOf(latitude);
            arr[1] = String.valueOf(longitude);
        } else {
            gpsTracker.showSettingsAlert();
        }

        return arr;
    }

}
