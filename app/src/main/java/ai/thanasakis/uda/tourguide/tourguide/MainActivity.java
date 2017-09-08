package ai.thanasakis.uda.tourguide.tourguide;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import ai.thanasakis.uda.tourguide.tourguide.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final Typeface awsomefont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        Typeface impact = Typeface.createFromAsset(getAssets(), "impact.ttf");

        binding.textHeadLine1.setTypeface(impact);
        binding.textHeadLine2.setTypeface(impact);
        binding.textHeadLine2.setGravity(Gravity.TOP | Gravity.END);
        getSupportActionBar().hide();

        final String[] categories = new String[]{getResources().getString(R.string.category1ForDB), getResources().getString(R.string.category2ForDB), getResources().getString(R.string.category3ForDB), getResources().getString(R.string.category4ForDB), getResources().getString(R.string.category5ForDB)};

        String[] playlists = new String[]{getResources().getString(R.string.category1), getResources().getString(R.string.category2), getResources().getString(R.string.category3), getResources().getString(R.string.category4), getResources().getString(R.string.category5)};
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll(Arrays.asList(playlists));
        binding.listMain.setAdapter(new ArrayAdapter<String>(this, R.layout.main_menu_item, planetList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Check if the existing view is being reused, otherwise inflate the view
                View listItemView = convertView;
                if (listItemView == null) {
                    listItemView = LayoutInflater.from(getContext()).inflate(
                            R.layout.main_menu_item, parent, false);
                }

                String currentItem = getItem(position);
                String[] itemParts = currentItem.split(",");
                TextView textIcon = (TextView) listItemView.findViewById(R.id.text_icon);
                textIcon.setTypeface(awsomefont);
                textIcon.setText(itemParts[0]);
                TextView textItem = (TextView) listItemView.findViewById(R.id.text_item);
                textItem.setText(itemParts[1]);
                return listItemView;

            }
        });
        binding.listMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent venueslistIntent = new Intent(MainActivity.this, VenuesListViews.class);
                venueslistIntent.putExtra("CategoryNum", position);
                startActivity(venueslistIntent);
            }
        });
    }
}
