package com.example.zhihu.bean;

import java.util.List;

/**
 * Created by 44744 on 2016/4/19.
 */
public class LeftEntity {

    /**
     * limit : 1000
     * subscribed : []
     * others : [{"color":8307764,"thumbnail":"http://pic4.zhimg.com/2c38a96e84b5cc8331a901920a87ea71.jpg","description":"内容由知乎用户推荐，海纳主题百万，趣味上天入地","id":12,"name":"用户推荐日报"},"..."]
     */

    private int limit;
    private List<?> subscribed;
    /**
     * color : 8307764
     * thumbnail : http://pic4.zhimg.com/2c38a96e84b5cc8331a901920a87ea71.jpg
     * description : 内容由知乎用户推荐，海纳主题百万，趣味上天入地
     * id : 12
     * name : 用户推荐日报
     */

    private List<OthersBean> others;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<?> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<?> subscribed) {
        this.subscribed = subscribed;
    }

    public List<OthersBean> getOthers() {
        return others;
    }

    public void setOthers(List<OthersBean> others) {
        this.others = others;
    }

    public static class OthersBean {
        private int color;
        private String thumbnail;
        private String description;
        private int id;
        private String name;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
