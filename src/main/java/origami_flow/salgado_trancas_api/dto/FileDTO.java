package origami_flow.salgado_trancas_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {

    @JsonProperty("asset_folder")
    private String assetFolder;
    private String signature;
    private String format;
    @JsonProperty("resource_type")
    private String resourceType;
    @JsonProperty("secure_url")
    private String secureUrl;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("asset_id")
    private String assetId;
    @JsonProperty("version_id")
    private String versionId;
    private String type;
    @JsonProperty("display_name")
    private String displayName;
    private int version;
    private String url;
    @JsonProperty("public_id")
    private String publicId;
    private List<Object> tags;
    @JsonProperty("original_filename")
    private String originalFilename;
    @JsonProperty("api_key")
    private String apiKey;
    private int bytes;
    private int width;
    private String etag;
    private boolean placeholder;
    private int height;
}
