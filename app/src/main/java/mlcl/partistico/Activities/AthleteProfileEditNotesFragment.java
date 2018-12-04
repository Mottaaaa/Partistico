package mlcl.partistico.Activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mlcl.partistico.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AthleteProfileEditNotesFragment extends Fragment {


    public AthleteProfileEditNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_athlete_profile_edit_notes, container, false);
    }

}
