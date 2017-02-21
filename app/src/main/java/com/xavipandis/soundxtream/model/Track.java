package com.xavipandis.soundxtream.model;

/**
 * Created by xavi on 18/02/2017.
 */

public class Track {

    private Long id;
    private String name;
    private String url;
    private String label;
    private String date_posted;
    private String artwork;
    private String description;
    private String typeSong;
    private String bpm;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(String date_posted) {
        this.date_posted = date_posted;
    }

    public String getArtwork() {
        return artwork;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeSong() {
        return typeSong;
    }

    public void setTypeSong(String typeSong) {
        this.typeSong = typeSong;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public Track(Long id, String name, String url, String label, String date_posted, String artwork, String description, String typeSong, String bpm) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.label = label;
        this.date_posted = date_posted;
        this.artwork = artwork;
        this.description = description;
        this.typeSong = typeSong;
        this.bpm = bpm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (id != null ? !id.equals(track.id) : track.id != null) return false;
        if (name != null ? !name.equals(track.name) : track.name != null) return false;
        if (url != null ? !url.equals(track.url) : track.url != null) return false;
        if (label != null ? !label.equals(track.label) : track.label != null) return false;
        if (date_posted != null ? !date_posted.equals(track.date_posted) : track.date_posted != null)
            return false;
        if (artwork != null ? !artwork.equals(track.artwork) : track.artwork != null) return false;
        if (description != null ? !description.equals(track.description) : track.description != null)
            return false;
        if (typeSong != null ? !typeSong.equals(track.typeSong) : track.typeSong != null)
            return false;
        return bpm != null ? bpm.equals(track.bpm) : track.bpm == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (date_posted != null ? date_posted.hashCode() : 0);
        result = 31 * result + (artwork != null ? artwork.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (typeSong != null ? typeSong.hashCode() : 0);
        result = 31 * result + (bpm != null ? bpm.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", label='" + label + '\'' +
                ", date_posted='" + date_posted + '\'' +
                ", artwork='" + artwork + '\'' +
                ", description='" + description + '\'' +
                ", typeSong='" + typeSong + '\'' +
                ", bpm='" + bpm + '\'' +
                ", user=" + user +
                '}';
    }
}
