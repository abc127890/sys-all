package com.ayl.advert.service;

import com.ayl.advert.model.AppInfo;

/**
 * @author wfd
 */
public interface AppInfoService {

    /**
     * 根据app_id查询
     *
     * @param app_id
     * @return
     */
    public AppInfo get(Long app_id);

    /**
     * 保存
     *
     * @param appInfo
     */
    public void save(AppInfo appInfo);

    /**
     * 修改
     *
     * @param appInfo
     */
    public void update(AppInfo appInfo);

    /**
     * 从商城获取并更新
     *
     * @param app_id
     * @return
     */
    public AppInfo getAndUpdateFromStore(Long app_id);
}
