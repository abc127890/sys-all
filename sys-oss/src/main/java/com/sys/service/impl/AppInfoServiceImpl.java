package com.sys.service.impl;

import com.ayl.advert.common.response.BusinessException;
import com.ayl.advert.common.response.Result;
import com.ayl.advert.model.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author wfd
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Autowired
    private AppInfoDao appInfoDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public AppInfo get(Long app_id) {
        return appInfoDao.get(app_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(AppInfo appInfo) {
        appInfoDao.save(appInfo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(AppInfo appInfo) {
        appInfoDao.update(appInfo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AppInfo getAndUpdateFromStore(Long app_id) {

        ItunesAppInfo.Info info = null;
        try {
            info = ItunesAppInfoUtil.get(app_id + "");
        } catch (Exception e) {
            throw new BusinessException(Result.Code.SYSTEM_ERROR, "获取app信息失败");
        }

        AppInfo appInfo = new AppInfo();
        appInfo.setApp_id(app_id);
        appInfo.setBundle_id(info.getBundleId());
        appInfo.setFile_size(info.getFileSizeBytes().longValue());
        appInfo.setSize_name(FileSizeUtil.getFormatSize(appInfo.getFile_size().doubleValue()));
        appInfo.setFree(info.getPrice() > 0 ? 0 : 1);
        appInfo.setPrice(info.getPrice());
        appInfo.setIcon(info.getArtworkUrl100());
        appInfo.setItunes_url(info.getTrackViewUrl());
        appInfo.setName(info.getAppName());
        appInfo.setUpdate_time(new Date());

        if (appInfoDao.get(app_id) == null) {
            appInfoDao.save(appInfo);
        } else {
            appInfoDao.update(appInfo);
        }
        return appInfo;
    }


}
