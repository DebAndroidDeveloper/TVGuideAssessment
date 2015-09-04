package assessment.mycompany.com.tvguideassessment.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import assessment.mycompany.com.tvguideassessment.R;
import assessment.mycompany.com.tvguideassessment.models.TrendingShowDetails;

/**
 * Created by Priya on 8/27/2015.
 */
public class TrendingShowsListAdapter extends BaseAdapter {
    private Context context;
    private List<TrendingShowDetails> trendingShowList;
    private static final String TAG = TrendingShowsListAdapter.class.getCanonicalName();
    private ImageLoader imageLoader;

    public TrendingShowsListAdapter(Context currentContext, List<TrendingShowDetails> trendingShowNameList) {
        this.context = currentContext;
        this.trendingShowList = trendingShowNameList;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(currentContext).build();
        this.imageLoader = ImageLoader.getInstance();
        this.imageLoader.init(config);
    }

    @Override
    public int getCount() {
        return trendingShowList.size();
    }

    @Override
    public Object getItem(int position) {
        return trendingShowList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return trendingShowList.indexOf(trendingShowList.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.layout_list_row_item_trending_shows, parent, false);
        ImageView trendingShowImageView = (ImageView) convertView.findViewById(R.id.trending_show_imageview);
        TextView trendingShowNameTextView = (TextView) convertView.findViewById(R.id.trending_show_name_textView);
        TextView trendingShowAirTimeTextView = (TextView) convertView.findViewById(R.id.trending_show_air_channel_textView);

        //TODO : fetch the image and set into imageview
        String showImageUrl = trendingShowList.get(position).getImageUrl();
        String showName = trendingShowList.get(position).getShowName();
        String airTime = trendingShowList.get(position).getAiringChannelName();

        trendingShowNameTextView.setText(showName);
        trendingShowAirTimeTextView.setText(airTime);
        imageLoader.displayImage(showImageUrl, trendingShowImageView);
        return convertView;
    }
}
