package com.dision.android.rtlviewpager.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class Channel implements Parcelable {

    @ElementList(inline = true)
    private List<FeedItem> feedItems;

    public Channel() {
        // default
    }

    private Channel(Parcel in) {
        readFromParcel(in);
    }

    public List<FeedItem> getFeedItems() {
        return feedItems;
    }

    public void setFeedItems(List<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(feedItems);
    }

    private void readFromParcel(Parcel in) {
        this.feedItems = in.createTypedArrayList(FeedItem.CREATOR);
    }

    public static final Parcelable.Creator<Channel> CREATOR = new Parcelable.Creator<Channel>() {

        @Override
        public Channel createFromParcel(Parcel in) {
            return new Channel(in);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };
}
