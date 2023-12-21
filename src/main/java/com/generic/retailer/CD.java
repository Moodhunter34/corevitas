package com.generic.retailer;

import java.util.List;

public final class CD {

    private String title;
    private String artist;
    private int releaseYear;
    private List<String> tracks;
    private double price;

    public CD(){
        this.price = 10.0;
    }

    public CD(String title, String artist, int releaseYear, List<String> tracks, double price) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.tracks = tracks;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "CD{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", releaseYear=" + releaseYear +
                ", tracks=" + tracks +
                ", price=" + price +
                '}';
    }
}
