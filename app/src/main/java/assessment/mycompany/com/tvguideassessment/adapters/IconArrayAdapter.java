package assessment.mycompany.com.tvguideassessment.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import assessment.mycompany.com.tvguideassessment.R;


/**
 * Created by Debasis on 8/27/2015.
 */
public class IconArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;
    private static final String TAG = IconArrayAdapter.class.getCanonicalName();

    public IconArrayAdapter(Context context, int resource, int textViewResourceId, String[] values) {
        super(context, resource, textViewResourceId, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_navigation_list_row_item, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.item_icon);
        //listDivider.setVisibility(View.INVISIBLE);
        //textView.setText(values[position]);

        // Change icon based on name
        String s = values[position];

        Log.d(TAG, s);

        if (s.equals(context.getString(R.string.title_section1))) {
            imageView.setImageResource(R.drawable.menu_trending_shows_default);
        } else if (s.equals(context.getString(R.string.title_section2))) {
            imageView.setImageResource(R.drawable.menu_full_schedule_default);
        } else if (s.equals(context.getString(R.string.title_section3))) {
            imageView.setImageResource(R.drawable.menu_search_for_shows_default);
        }

        return rowView;
    }
}
