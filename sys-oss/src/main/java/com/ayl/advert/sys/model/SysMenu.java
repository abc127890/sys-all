package com.ayl.advert.sys.model;

import java.util.List;

/**
 * @author wfd
 */
public class SysMenu {

    private String id;

    private String name;

    private byte leaf;

    private byte expand;

    private byte level;

    private String icon;

    private String url;

    private String parent_id;

    private List<SysMenu> childrens;

    private byte rank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getLeaf() {
        return leaf;
    }

    public void setLeaf(byte leaf) {
        this.leaf = leaf;
    }

    public byte getExpand() {
        return expand;
    }

    public void setExpand(byte expand) {
        this.expand = expand;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public byte getRank() {
        return rank;
    }

    public void setRank(byte rank) {
        this.rank = rank;
    }

    public List<SysMenu> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<SysMenu> childrens) {
        this.childrens = childrens;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SysMenu [id=" + id + ", name=" + name + ", leaf=" + leaf + ", expand=" + expand + ", level=" + level
                + ", icon=" + icon + ", url=" + url + ", parent_id=" + parent_id + ", childrens=" + childrens + ", rank="
                + rank + "]";
    }

}
