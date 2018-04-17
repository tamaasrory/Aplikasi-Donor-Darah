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
import app.stokdarah.models.InfoPendonorModel;

import java.util.ArrayList;

/**
 * Created by asrory on 02/02/18.
 */
public class InfoPendonorAdapter extends ArrayAdapter<InfoPendonorModel> implements View.OnClickListener{

    private ArrayList<InfoPendonorModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView info;
        ImageView icon;
    }

    public InfoPendonorAdapter(ArrayList<InfoPendonorModel> data, Context context) {
        super(context, R.layout.info_pendonor_list, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        InfoPendonorModel InfoPendonorModel=(InfoPendonorModel)object;

//        switch (v.getId())
//        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +InfoPendonorModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InfoPendonorModel InfoPendonorModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.info_pendonor_list, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.titleInfoPendonor);
            viewHolder.info = (TextView) convertView.findViewById(R.id.infoPendonor);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.iconInfoPendonor);

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

        viewHolder.title.setText(InfoPendonorModel.getTitle());
        viewHolder.info.setText(InfoPendonorModel.getInfo());
        viewHolder.icon.setImageResource(InfoPendonorModel.getIcon());

        // Return the completed view to render on screen
        return convertView;
    }
}