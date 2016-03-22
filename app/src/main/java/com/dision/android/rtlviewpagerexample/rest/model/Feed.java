package com.dision.android.rtlviewpagerexample.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class Feed implements Parcelable {

    @Element(name = "channel")
    private Channel channel;

    public Feed() {
        // default
    }

    private Feed(Parcel in) {
        readFromParcel(in);
    }


    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(channel, flags);
    }

    private void readFromParcel(Parcel in) {
        this.channel = in.readParcelable(Channel.class.getClassLoader());
    }

    public static final Parcelable.Creator<Feed> CREATOR = new Parcelable.Creator<Feed>() {

        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };
}
