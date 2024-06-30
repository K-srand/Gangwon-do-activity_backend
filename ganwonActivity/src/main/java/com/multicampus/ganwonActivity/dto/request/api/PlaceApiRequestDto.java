package com.multicampus.ganwonActivity.dto.request.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PlaceApiRequestDto {
    private Response response;

    @Data
    @NoArgsConstructor
    public static class Response {
        private Header header;
        private Body body;
    }

    @Data
    @NoArgsConstructor
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    @NoArgsConstructor
    public static class Body {
        private Items items;
    }

    @Data
    @NoArgsConstructor
    public static class Items {
        private List<Item> item;
    }

    @Data
    @NoArgsConstructor
    public static class Item {
        private String addr1;
        private String addr2;
//      private int areacode;
//      private String booktour;
        private String cat1;
        private String cat2;
        private String cat3;
        private int contentid;
        private int contentTypeid;
//        private String createdtime;
        private String firstimage;
        private String firstimage2;
//        private String cpyrightDivCd;
        private double mapx;
        private double mapy;
//        private int mlevel;
//        private String modifiedtime;
//        private String sigungucode;
        private String tel;
        private String title;
//        private String zipcode;
    }
}
