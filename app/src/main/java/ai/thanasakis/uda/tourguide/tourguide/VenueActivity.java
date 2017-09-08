package ai.thanasakis.uda.tourguide.tourguide;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ai.thanasakis.uda.tourguide.tourguide.databinding.VenueDetailsBinding;


/**
 * Created by programbench on 6/21/2017.
 */

public class VenueActivity extends AppCompatActivity {

    VenueDetailsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.venue_details);
        getSupportActionBar().hide();
        Intent myIntent = getIntent();
        Bundle bundle = myIntent.getExtras();
        Venue venueSelected = (Venue) bundle.getSerializable("venueSelected");
        final String venueName =venueSelected.getVenueName();
        final String venueDescription = venueSelected.getVenueDescription();
        final String venuePhone = venueSelected.getVenuePhone();
        final String venueSite = venueSelected.getVenueSite();
        final Double venueLatitude = venueSelected.getVenueLatitude();
        final Double venueLongtitude = venueSelected.getVenueLongtitude();
        final int venueImageResourceId = venueSelected.getImageResourceId();
        final Typeface awsomefont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        Typeface impact = Typeface.createFromAsset(getAssets(), "impact.ttf");

        binding.call.setTypeface(awsomefont);
        binding.showMap.setTypeface(awsomefont);
        binding.site.setTypeface(awsomefont);
        binding.textNameDetails.setTypeface(impact);
        binding.textNameDetails.setText(venueName);
        binding.textDetails.setText(venueDescription);
        if (venueImageResourceId > -1) {
            binding.waveHeadDetails.setImageSource(venueImageResourceId);
        }
        binding.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + venuePhone));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        binding.showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uriBegin = "geo:" + venueLatitude + "," + venueLongtitude;
                String query = venueLatitude + "," + venueLongtitude + "(" + venueName + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        binding.site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(venueSite);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
