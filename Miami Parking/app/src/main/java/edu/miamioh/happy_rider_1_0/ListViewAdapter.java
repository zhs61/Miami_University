package edu.miamioh.happy_rider_1_0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<ParkingLotNames> parkingLotNamesList = null;
    private ArrayList<ParkingLotNames> arraylist;

    public ListViewAdapter(Context context, List<ParkingLotNames> parkingLotNamesList) {
        mContext = context;
        this.parkingLotNamesList = parkingLotNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<ParkingLotNames>();
        this.arraylist.addAll(parkingLotNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return parkingLotNamesList.size();
    }

    @Override
    public ParkingLotNames getItem(int position) {
        return parkingLotNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(parkingLotNamesList.get(position).getParkingLotName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        parkingLotNamesList.clear();
        if (charText.length() == 0) {
            parkingLotNamesList.addAll(arraylist);
        } else {
            for (ParkingLotNames wp : arraylist) {
                if (wp.getParkingLotName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    parkingLotNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}