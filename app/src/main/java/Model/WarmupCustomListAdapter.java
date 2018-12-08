package Model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Database.BDWarmup;
import mlcl.partistico.R;

public class WarmupCustomListAdapter extends ArrayAdapter<BDWarmup> {
    private final Activity context;
    private List<BDWarmup> warmups;

    public WarmupCustomListAdapter(Activity context, List<BDWarmup> warmups) {
        super(context, R.layout.warmup_list_item, warmups);

        this.warmups = warmups;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.athlete_list_item, null, true);

        ImageView icon = (ImageView) rowView.findViewById(R.id.iv_icon);
        TextView name = (TextView) rowView.findViewById(R.id.lbl_name);

        //Como colocar o número desenhado aqui?
        //icon.setImageBitmap();

        name.setText(warmups.get(position).getName());


        rowView.setTag(warmups.get(position).getId());

        return rowView;

    }
}
