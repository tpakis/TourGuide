package ai.thanasakis.uda.tourguide.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    private VenuesListViews baseActivity;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.venues_list, container, false);
        baseActivity = (VenuesListViews) getActivity();

        final ArrayList<Venue> Venues = baseActivity.VenuesHistory;
        VenueAdapter venueAdapter = new VenueAdapter(getActivity(), Venues);
        ListView listView = (ListView) rootView.findViewById(R.id.listofvenues);
        listView.setAdapter(venueAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Venue venueSelected = Venues.get(position);
                Intent venueIntent = new Intent(baseActivity, VenueActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("venueSelected", venueSelected);
                venueIntent.putExtras(bundle);
                startActivity(venueIntent);
            }
        });
        return rootView;
    }


}