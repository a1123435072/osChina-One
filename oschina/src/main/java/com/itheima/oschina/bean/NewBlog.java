package com.itheima.oschina.bean;

import java.util.List;

/**
 * Created by ywf on 2017/1/4.
 */
public class NewBlog {

    /**
     * code : 1
     * message : success
     * result : {"items":[{"author":"wangxuwei","body":"一、首先安装libiconv 如果自己编译安装会出现  错误，srclib/stdio.h ...","commentCount":0,"href":"https://my.oschina.net/u/2245781/blog/749893","id":749893,"original":true,"pubDate":"2016-09-21 20:43:54","recommend":false,"title":"编译apache web服务器出现undefined reference to `libiconv'解决","type":1,"viewCount":1},"\u2026\u2026"],"nextPageToken":"DBA816934CD0AA59","pageInfo":{"resultsPerPage":1,"totalResults":1000},"prevPageToken":"0997C855C600E421"}
     * time : 2016-09-21 21:31:18
     */

    private int code;
    private String message;
    private ResultBean result;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class ResultBean {
        /**
         * items : [{"author":"wangxuwei","body":"一、首先安装libiconv 如果自己编译安装会出现  错误，srclib/stdio.h ...","commentCount":0,"href":"https://my.oschina.net/u/2245781/blog/749893","id":749893,"original":true,"pubDate":"2016-09-21 20:43:54","recommend":false,"title":"编译apache web服务器出现undefined reference to `libiconv'解决","type":1,"viewCount":1},"\u2026\u2026"]
         * nextPageToken : DBA816934CD0AA59
         * pageInfo : {"resultsPerPage":1,"totalResults":1000}
         * prevPageToken : 0997C855C600E421
         */

        private String nextPageToken;
        private PageInfoBean pageInfo;
        private String prevPageToken;
        private List<ItemsBean> items;

        public String getNextPageToken() {
            return nextPageToken;
        }

        public void setNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
        }

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public String getPrevPageToken() {
            return prevPageToken;
        }

        public void setPrevPageToken(String prevPageToken) {
            this.prevPageToken = prevPageToken;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class PageInfoBean {
            /**
             * resultsPerPage : 1
             * totalResults : 1000
             */

            private int resultsPerPage;
            private int totalResults;

            public int getResultsPerPage() {
                return resultsPerPage;
            }

            public void setResultsPerPage(int resultsPerPage) {
                this.resultsPerPage = resultsPerPage;
            }

            public int getTotalResults() {
                return totalResults;
            }

            public void setTotalResults(int totalResults) {
                this.totalResults = totalResults;
            }
        }

        public static class ItemsBean {
            /**
             * author : wangxuwei
             * body : 一、首先安装libiconv 如果自己编译安装会出现  错误，srclib/stdio.h ...
             * commentCount : 0
             * href : https://my.oschina.net/u/2245781/blog/749893
             * id : 749893
             * original : true
             * pubDate : 2016-09-21 20:43:54
             * recommend : false
             * title : 编译apache web服务器出现undefined reference to `libiconv'解决
             * type : 1
             * viewCount : 1
             */

            private String author;
            private String body;
            private int commentCount;
            private String href;
            private int id;
            private boolean original;
            private String pubDate;
            private boolean recommend;
            private String title;
            private int type;
            private int viewCount;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isOriginal() {
                return original;
            }

            public void setOriginal(boolean original) {
                this.original = original;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public boolean isRecommend() {
                return recommend;
            }

            public void setRecommend(boolean recommend) {
                this.recommend = recommend;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }
        }
    }
}
