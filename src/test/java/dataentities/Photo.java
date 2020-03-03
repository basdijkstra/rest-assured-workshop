package dataentities;

import lombok.Data;

@Data
public class Photo {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo() {
    }
}
