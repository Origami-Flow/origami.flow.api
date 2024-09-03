package origami_flow.salgado_trancas_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LivroApiExternoDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<String> authors;

    @JsonProperty("publishedDate")
    private String publishedDate;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("language")
    private String language;

    @JsonProperty("pageCount")
    private int pageCount;

    @JsonProperty("previewLink")
    private String previewLink;

    @JsonProperty("infoLink")
    private String infoLink;

    @JsonProperty("dowloadLinkPdf")
    private String downloadLinkPdf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getDownloadLinkPdf() {
        return downloadLinkPdf;
    }

    public void setDownloadLinkPdf(String downloadLinkPdf) {
        this.downloadLinkPdf = downloadLinkPdf;
    }
}
