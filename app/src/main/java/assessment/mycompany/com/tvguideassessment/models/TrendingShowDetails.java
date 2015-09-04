package assessment.mycompany.com.tvguideassessment.models;

/**
 * Created by Debasis on 8/24/2015.
 */
public class TrendingShowDetails {
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShowDetailsUrl() {
        return showDetailsUrl;
    }

    public void setShowDetailsUrl(String showDetailsUrl) {
        this.showDetailsUrl = showDetailsUrl;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getAiringChannelName() {
        return airingChannelName;
    }

    public void setAiringChannelName(String airingChannelName) {
        this.airingChannelName = airingChannelName;
    }

    private String imageUrl;
    private String showDetailsUrl;
    private String showName;
    private String airingChannelName;
}
