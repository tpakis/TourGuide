package ai.thanasakis.uda.tourguide.tourguide;

import java.io.Serializable;

/**
 * Created by programbench on 6/7/2017.
 */

public class Venue implements Serializable {

    private static final int NO_IMAGE_PROVIDED = -1;
    private int mid;
    private String mVenueName;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private String mVenueDescription;
    private String mVenuePhone;
    private String mVenueSite;
    private double mVenueLongtitude;
    private double mVenueLatitude;
    private String mVenueCategory;

    public Venue(int id, String VenueName, String VenueCategory, String VenueDescription, int ImageResourceId, String VenuePhone, double VenueLongtitude, double VenueLatitude) {
        mid = id;
        mVenueName = VenueName;
        mVenueDescription = VenueDescription;
        mImageResourceId = ImageResourceId;
        mVenueCategory = VenueCategory;
        mVenuePhone = VenuePhone;
        mVenueLatitude = VenueLatitude;
        mVenueLongtitude = VenueLongtitude;

    }

    public Venue(int id, String VenueName, String VenueCategory, String VenueDescription, String VenuePhone, double VenueLongtitude, double VenueLatitude) {
        mid = id;
        mVenueName = VenueName;
        mVenueDescription = VenueDescription;
        mVenueCategory = VenueCategory;
        mVenuePhone = VenuePhone;
        mVenueLatitude = VenueLatitude;
        mVenueLongtitude = VenueLongtitude;
        mImageResourceId = NO_IMAGE_PROVIDED;
    }

    public Venue() {
        mid = -1;
        mVenueName = "";
        mVenueDescription = "";
        mImageResourceId = NO_IMAGE_PROVIDED;
        mVenueCategory = "";
        mVenuePhone = "";
        mVenueLatitude = NO_IMAGE_PROVIDED;
        mVenueLongtitude = NO_IMAGE_PROVIDED;

    }

    public int getVenueID() {
        return mid;
    }

    public void setVenueID(int mVenueID) {
        this.mid = mVenueID;
    }


    public String getVenueName() {
        return mVenueName;
    }

    public void setVenueName(String mVenueName) {
        this.mVenueName = mVenueName;
    }

    public String getVenueDescription() {
        return mVenueDescription;
    }

    public void setVenueDescription(String mVenueDescription) {
        this.mVenueDescription = mVenueDescription;
    }

    public String getVenuePhone() {
        return mVenuePhone;
    }

    public void setVenuePhone(String mVenuePhone) {
        this.mVenuePhone = mVenuePhone;
    }

    public String getVenueSite() {
        return mVenueSite;
    }

    public void setVenueSite(String mVenueSite) {
        this.mVenueSite = mVenueSite;
    }

    public double getVenueLongtitude() {
        return mVenueLongtitude;
    }

    public void setVenueLongtitude(double mVenueLongtitude) {
        this.mVenueLongtitude = mVenueLongtitude;
    }

    public double getVenueLatitude() {
        return mVenueLatitude;
    }

    public void setVenueLatitude(double mVenueLatitude) {
        this.mVenueLatitude = mVenueLatitude;
    }

    public String getVenueCategory() {
        return mVenueCategory;
    }

    public void setVenueCategory(String mVenueCategory) {
        this.mVenueCategory = mVenueCategory;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public void setImageResourceId(int mImageResourceId) {
        this.mImageResourceId = mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mVenueName='" + mVenueName + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mVenueCategory='" + mVenueCategory + '\'' +
                ", mVenueDescription=" + mVenueDescription +
                ", mVenuePhone=" + mVenuePhone +
                ", mVenueSite=" + mVenueSite +
                ", mVenueLongtitude=" + mVenueLongtitude +
                ", mVenueLatitude=" + mVenueLatitude +
                '}';
    }
}