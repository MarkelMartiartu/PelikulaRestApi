package dambi.pelikularest.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pelikula {

    @Id
    private Integer id;
    private String title;
    private Integer year;
    private List<String> cast;
    private String extract;
    private List<String> genres;
    private Thumbnail thumbnail;

    public Pelikula(Integer id, String title, Integer year, List<String> cast, String extract, List<String> genres,
            Thumbnail thumbnail) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.extract = extract;
        this.genres = genres;
        this.thumbnail = thumbnail;
    }

    public static class Thumbnail {
        private String image;
        private int width;
        private int height;

        public Thumbnail(String image, int width, int height) {
            this.image = image;
            this.width = width;
            this.height = height;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

}
