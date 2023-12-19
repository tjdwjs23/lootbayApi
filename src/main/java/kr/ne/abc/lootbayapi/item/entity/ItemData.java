package kr.ne.abc.lootbayapi.item.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ItemData {

    private int id;
    @JsonProperty("service_id")
    private String serviceId;
    @JsonProperty("item_id")
    private String itemId;
    private String name;
    private String description;
    @JsonProperty("image_uri")
    private String imageUri;
    private String nhid;
    private int idx;
    private List<Attribute> attributes;

    @Getter
    @Setter
    public static class Attribute {
        @JsonProperty("trait_type")
        private String traitType;
        private String value;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
