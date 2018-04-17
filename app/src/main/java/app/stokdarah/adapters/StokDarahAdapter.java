package app.stokdarah.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.stokdarah.R;
import app.stokdarah.models.StokDarahModel;

import java.util.ArrayList;

/**
 * Created by asrory on 02/02/18.
 */
public class StokDarahAdapter extends ArrayAdapter<StokDarahModel> implements View.OnClickListener {

    private ArrayList<StokDarahModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView golDarah;
        TextView kataJumlah;
        TextView jumlahStok;
        TextView status;
        ImageView readMore;
    }

    public StokDarahAdapter(ArrayList<StokDarahModel> data, Context context) {
        super(context, R.layout.stok_darah_list, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        StokDarahModel dataModel = (StokDarahModel) object;

//        switch (v.getId())
//        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        StokDarahModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.stok_darah_list, parent, false);
            viewHolder.golDarah = (TextView) convertView.findViewById(R.id.golDarah);
            viewHolder.kataJumlah = (TextView) convertView.findViewById(R.id.kataJumlah);
            viewHolder.jumlahStok = (TextView) convertView.findViewById(R.id.jumlahStok);
            viewHolder.status = (TextView) convertView.findViewById(R.id.status);
            viewHolder.readMore = (ImageView) convertView.findViewById(R.id.readMore);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom : R.anim.drop_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.golDarah.setText(dataModel.getGolDarah());
        viewHolder.kataJumlah.setText(dataModel.getKataJumlah());
        viewHolder.jumlahStok.setText(dataModel.getJumlahStok());
        viewHolder.status.setText(dataModel.getStatus());
        viewHolder.readMore.setOnClickListener(this);
        viewHolder.readMore.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}