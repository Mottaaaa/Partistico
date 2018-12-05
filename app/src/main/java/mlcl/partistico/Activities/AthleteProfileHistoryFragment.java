package mlcl.partistico.Activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import Model.Utils;
import mlcl.partistico.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AthleteProfileHistoryFragment extends Fragment {



    public AthleteProfileHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_athlete_profile_history, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if (view != null) {
            TextView history = (TextView) getView().findViewById(R.id.lbl_history);
            history.setText(Utils.getInstance().getActiveAthlete().getHistory());
        }
    }
}
