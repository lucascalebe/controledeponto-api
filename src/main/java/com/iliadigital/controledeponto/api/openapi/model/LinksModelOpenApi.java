package com.iliadigital.controledeponto.api.openapi.model;

import io.swagger.annotations.ApiModel;

@ApiModel("Links")
public class LinksModelOpenApi {

    private LinkModel link;

    public LinkModel getLink() {
        return link;
    }

    public void setLink(LinkModel link) {
        this.link = link;
    }

    private class LinkModel {
        private String rel;
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
