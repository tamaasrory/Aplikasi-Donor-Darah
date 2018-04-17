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
import app.stokdarah.models.DetailStokDarahModel;

import java.util.ArrayList;

/**
 * Created by asrory on 02/02/18.
 */
public class DetailStokDarahAdapter extends ArrayAdapter<DetailStokDarahModel> implements View.OnClickListener{

    private ArrayList<DetailStokDarahModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView namaProduk,stokProduk;
    }

    public DetailStokDarahAdapter(ArrayList<DetailStokDarahModel> data, Context context) {
        super(context, R.layout.detail_stokdarah_list, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DetailStokDarahModel DetailStokDarahModel=(DetailStokDarahModel)object;

//        switch (v.getId())
//        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +DetailStokDarahModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DetailStokDarahModel DetailStokDarahModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.detail_stokdarah_list, parent, false);
            viewHolder.namaProduk = (TextView) convertView.findViewById(R.id.namaProduk);
            viewHolder.stokProduk = (TextView) convertView.findViewById(R.id.stokProduk);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom : R.anim.drop_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.namaProduk.setText(DetailStokDarahModel.getNamaProduk());
        viewHolder.stokProduk.setText(DetailStokDarahModel.getStok());
        // Return the completed view to render on screen
        return convertView;
    }
}