package kr.ne.abc.lootbayapi.block.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "items")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // PK
    private String service_id;
    private String item_id;
    private String name;
    private String description;
    private String image_uri;
    private String nhid;
    private String idx;
    private String attributes;

    @Builder
    public Block(Long id, String service_id, String item_id, String name, String description, String image_uri, String nhid, String idx, String attributes) {
        this.id = id;
        this.service_id = service_id;
        this.item_id = item_id;
        this.name = name;
        this.description = description;
        this.image_uri = image_uri;
        this.nhid = nhid;
        this.idx = idx;
        this.attributes = attributes;
    }

    @Getter
    public static class ResponseDto {
        private String service_id;
        private String item_id;
        private String name;
        private String description;
        private String image_uri;
        private String attributes;

        public ResponseDto(Block block){
            this.service_id = block.getService_id();
            this.item_id = block.getItem_id();
            this.name = block.getName();
            this.description = block.getDescription();
            this.image_uri = block.getImage_uri();
            this.attributes = block.getAttributes();
        }
    }


}