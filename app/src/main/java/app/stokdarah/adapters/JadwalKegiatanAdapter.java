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
import app.stokdarah.models.JadwalKegiatanModel;
import app.stokdarah.R;

import java.util.ArrayList;

/**
 * Created by asrory on 02/02/18.
 */
public class JadwalKegiatanAdapter extends ArrayAdapter<JadwalKegiatanModel> implements View.OnClickListener{

    private ArrayList<JadwalKegiatanModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView info,tanggal,lokasi,alamat;
        ImageView readMore;
    }

    public JadwalKegiatanAdapter(ArrayList<JadwalKegiatanModel> data, Context context) {
        super(context, R.layout.jadwal_kegiatan_list, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        JadwalKegiatanModel JadwalKegiatanModel=(JadwalKegiatanModel)object;

//        switch (v.getId())
//        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +JadwalKegiatanModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        JadwalKegiatanModel JadwalKegiatanModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.jadwal_kegiatan_list, parent, false);
            viewHolder.info = (TextView) convertView.findViewById(R.id.infoKegiatan);
            viewHolder.tanggal = (TextView) convertView.findViewById(R.id.tanggalKegiatan);
            viewHolder.lokasi = (TextView) convertView.findViewById(R.id.lokasiKegiatan);
            viewHolder.alamat = (TextView) convertView.findViewById(R.id.alamatKegiatan);

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

        assert JadwalKegiatanModel != null;
        viewHolder.info.setText(JadwalKegiatanModel.getInfo());
        viewHolder.tanggal.setText(JadwalKegiatanModel.getTanggal());
        viewHolder.lokasi.setText(JadwalKegiatanModel.getLokasi());
        viewHolder.alamat.setText(JadwalKegiatanModel.getAlamat());
        // Return the completed view to render on screen
        return convertView;
    }
}