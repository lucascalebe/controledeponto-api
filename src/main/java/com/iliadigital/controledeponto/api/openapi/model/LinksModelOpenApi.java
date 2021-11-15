package com.iliadigital.controledeponto.api.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Links")
public class LinksModelOpenApi {

    private LinkModel link;

    public LinkModel getLink() {
        return link;
    }

    public void setLink(LinkModel link) {
        this.link = link;
    }

    private static class LinkModel {

        @ApiModelProperty(example = "self")
        private String rel;

        @ApiModelProperty(example = "http://localhost:8080/v1/batidas/1")
        private String href;

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
