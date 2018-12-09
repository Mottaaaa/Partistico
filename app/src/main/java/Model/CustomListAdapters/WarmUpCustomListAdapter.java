package Model.CustomListAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import Database.BDWarmUp;
import mlcl.partistico.R;

public class WarmUpCustomListAdapter extends ArrayAdapter<BDWarmUp> {

    private final Activity context;
    private List<BDWarmUp> warmUps;
    private Button btnDelete;
    private Button btnEdit;

    public WarmUpCustomListAdapter(Activity context, List<BDWarmUp> warmUps) {
        super(context, R.layout.warm_up_list_item, warmUps);

        this.warmUps = warmUps;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.warm_up_list_item, null, true);

        TextView number = (TextView) rowView.findViewById(R.id.tv_number);
        TextView name = (TextView) rowView.findViewById(R.id.tv_name);

        number.setText("" + (position + 1));
        name.setText(warmUps.get(position).getName());

        rowView.setTag(warmUps.get(position).getId());

        return rowView;
    }

}
