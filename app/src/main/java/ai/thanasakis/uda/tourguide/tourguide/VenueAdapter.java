package ai.thanasakis.uda.tourguide.tourguide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by programbench on 6/12/2017.
 */

public class VenueAdapter extends ArrayAdapter<Venue> {
    //We initialize the ArrayAdapter's internal storage for the context and the list.
    public VenueAdapter(Activity context, ArrayList<Venue> androidWords) {
        super(context, 0, androidWords);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.venue_list_item, parent, false);
        }

        Venue currentVenue = getItem(position);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        if (currentVenue.hasImage()) {
            imageView.setImageResource(currentVenue.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.venue_name_list);
        defaultTextView.setText(currentVenue.getVenueName());
        TextView secTextView = (TextView) listItemView.findViewById(R.id.venue_description_list);
        secTextView.setText(currentVenue.getVenueDescription());
        return listItemView;
    }
}
