package com.ayl.advert.dao;

import com.ayl.advert.model.AppInfo;

public interface AppInfoDao  {

    /**
     * 根据app_id查询
     * @param app_id
     * @return
     */
    public AppInfo get(Long app_id);

    /**
     * 保存
     * @param appInfo
     */
    public void save(AppInfo appInfo);


    /**
     * 修改
     * @param appInfo
     */
    public void update(AppInfo appInfo);
}
