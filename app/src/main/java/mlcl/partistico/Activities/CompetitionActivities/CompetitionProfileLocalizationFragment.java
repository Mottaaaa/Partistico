package mlcl.partistico.Activities.CompetitionActivities;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import Model.Utils;
import mlcl.partistico.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompetitionProfileLocalizationFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;

    public CompetitionProfileLocalizationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        double[] coordinatesArray = Utils.getInstance().getActiveCompetition().getCoordinatesArray();
        float zoomLevel = 21.0f; //This goes up to 21
        LatLng coordinates = new LatLng(coordinatesArray[0], coordinatesArray[1]);

        mMap.addMarker(new MarkerOptions().position(coordinates));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));
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

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String country = addresses.get(0).getCountryName();

                tvAddress.setText(Utils.getInstance().getActiveCompetition().getLocation());
                tvLocation.setText(city + ", " + country);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
