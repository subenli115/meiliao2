package com.ziran.meiliao.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.ziran.meiliao.entry.AlbumEntry;
import com.ziran.meiliao.entry.CachePageEntry;
import com.ziran.meiliao.entry.CourseEntry;
import com.ziran.meiliao.entry.DownloadData;
import com.ziran.meiliao.entry.ExercisePageEntry;
import com.ziran.meiliao.entry.ExerciseProgressEntry;
import com.ziran.meiliao.entry.FolderEntry;
import com.ziran.meiliao.entry.GainSpreadDayEmtry;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.entry.SearchEntry;
import com.ziran.meiliao.entry.UserInfo;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.ui.bean.AuthorBean;

import com.ziran.meiliao.dao.AlbumEntryDao;
import com.ziran.meiliao.dao.CachePageEntryDao;
import com.ziran.meiliao.dao.CourseEntryDao;
import com.ziran.meiliao.dao.DownloadDataDao;
import com.ziran.meiliao.dao.ExercisePageEntryDao;
import com.ziran.meiliao.dao.ExerciseProgressEntryDao;
import com.ziran.meiliao.dao.FolderEntryDao;
import com.ziran.meiliao.dao.GainSpreadDayEmtryDao;
import com.ziran.meiliao.dao.MusicEntryDao;
import com.ziran.meiliao.dao.SearchEntryDao;
import com.ziran.meiliao.dao.UserInfoDao;
import com.ziran.meiliao.dao.VideoSectionEntryDao;
import com.ziran.meiliao.dao.AuthorBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig albumEntryDaoConfig;
    private final DaoConfig cachePageEntryDaoConfig;
    private final DaoConfig courseEntryDaoConfig;
    private final DaoConfig downloadDataDaoConfig;
    private final DaoConfig exercisePageEntryDaoConfig;
    private final DaoConfig exerciseProgressEntryDaoConfig;
    private final DaoConfig folderEntryDaoConfig;
    private final DaoConfig gainSpreadDayEmtryDaoConfig;
    private final DaoConfig musicEntryDaoConfig;
    private final DaoConfig searchEntryDaoConfig;
    private final DaoConfig userInfoDaoConfig;
    private final DaoConfig videoSectionEntryDaoConfig;
    private final DaoConfig authorBeanDaoConfig;

    private final AlbumEntryDao albumEntryDao;
    private final CachePageEntryDao cachePageEntryDao;
    private final CourseEntryDao courseEntryDao;
    private final DownloadDataDao downloadDataDao;
    private final ExercisePageEntryDao exercisePageEntryDao;
    private final ExerciseProgressEntryDao exerciseProgressEntryDao;
    private final FolderEntryDao folderEntryDao;
    private final GainSpreadDayEmtryDao gainSpreadDayEmtryDao;
    private final MusicEntryDao musicEntryDao;
    private final SearchEntryDao searchEntryDao;
    private final UserInfoDao userInfoDao;
    private final VideoSectionEntryDao videoSectionEntryDao;
    private final AuthorBeanDao authorBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        albumEntryDaoConfig = daoConfigMap.get(AlbumEntryDao.class).clone();
        albumEntryDaoConfig.initIdentityScope(type);

        cachePageEntryDaoConfig = daoConfigMap.get(CachePageEntryDao.class).clone();
        cachePageEntryDaoConfig.initIdentityScope(type);

        courseEntryDaoConfig = daoConfigMap.get(CourseEntryDao.class).clone();
        courseEntryDaoConfig.initIdentityScope(type);

        downloadDataDaoConfig = daoConfigMap.get(DownloadDataDao.class).clone();
        downloadDataDaoConfig.initIdentityScope(type);

        exercisePageEntryDaoConfig = daoConfigMap.get(ExercisePageEntryDao.class).clone();
        exercisePageEntryDaoConfig.initIdentityScope(type);

        exerciseProgressEntryDaoConfig = daoConfigMap.get(ExerciseProgressEntryDao.class).clone();
        exerciseProgressEntryDaoConfig.initIdentityScope(type);

        folderEntryDaoConfig = daoConfigMap.get(FolderEntryDao.class).clone();
        folderEntryDaoConfig.initIdentityScope(type);

        gainSpreadDayEmtryDaoConfig = daoConfigMap.get(GainSpreadDayEmtryDao.class).clone();
        gainSpreadDayEmtryDaoConfig.initIdentityScope(type);

        musicEntryDaoConfig = daoConfigMap.get(MusicEntryDao.class).clone();
        musicEntryDaoConfig.initIdentityScope(type);

        searchEntryDaoConfig = daoConfigMap.get(SearchEntryDao.class).clone();
        searchEntryDaoConfig.initIdentityScope(type);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        videoSectionEntryDaoConfig = daoConfigMap.get(VideoSectionEntryDao.class).clone();
        videoSectionEntryDaoConfig.initIdentityScope(type);

        authorBeanDaoConfig = daoConfigMap.get(AuthorBeanDao.class).clone();
        authorBeanDaoConfig.initIdentityScope(type);

        albumEntryDao = new AlbumEntryDao(albumEntryDaoConfig, this);
        cachePageEntryDao = new CachePageEntryDao(cachePageEntryDaoConfig, this);
        courseEntryDao = new CourseEntryDao(courseEntryDaoConfig, this);
        downloadDataDao = new DownloadDataDao(downloadDataDaoConfig, this);
        exercisePageEntryDao = new ExercisePageEntryDao(exercisePageEntryDaoConfig, this);
        exerciseProgressEntryDao = new ExerciseProgressEntryDao(exerciseProgressEntryDaoConfig, this);
        folderEntryDao = new FolderEntryDao(folderEntryDaoConfig, this);
        gainSpreadDayEmtryDao = new GainSpreadDayEmtryDao(gainSpreadDayEmtryDaoConfig, this);
        musicEntryDao = new MusicEntryDao(musicEntryDaoConfig, this);
        searchEntryDao = new SearchEntryDao(searchEntryDaoConfig, this);
        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);
        videoSectionEntryDao = new VideoSectionEntryDao(videoSectionEntryDaoConfig, this);
        authorBeanDao = new AuthorBeanDao(authorBeanDaoConfig, this);

        registerDao(AlbumEntry.class, albumEntryDao);
        registerDao(CachePageEntry.class, cachePageEntryDao);
        registerDao(CourseEntry.class, courseEntryDao);
        registerDao(DownloadData.class, downloadDataDao);
        registerDao(ExercisePageEntry.class, exercisePageEntryDao);
        registerDao(ExerciseProgressEntry.class, exerciseProgressEntryDao);
        registerDao(FolderEntry.class, folderEntryDao);
        registerDao(GainSpreadDayEmtry.class, gainSpreadDayEmtryDao);
        registerDao(MusicEntry.class, musicEntryDao);
        registerDao(SearchEntry.class, searchEntryDao);
        registerDao(UserInfo.class, userInfoDao);
        registerDao(VideoSectionEntry.class, videoSectionEntryDao);
        registerDao(AuthorBean.class, authorBeanDao);
    }
    
    public void clear() {
        albumEntryDaoConfig.clearIdentityScope();
        cachePageEntryDaoConfig.clearIdentityScope();
        courseEntryDaoConfig.clearIdentityScope();
        downloadDataDaoConfig.clearIdentityScope();
        exercisePageEntryDaoConfig.clearIdentityScope();
        exerciseProgressEntryDaoConfig.clearIdentityScope();
        folderEntryDaoConfig.clearIdentityScope();
        gainSpreadDayEmtryDaoConfig.clearIdentityScope();
        musicEntryDaoConfig.clearIdentityScope();
        searchEntryDaoConfig.clearIdentityScope();
        userInfoDaoConfig.clearIdentityScope();
        videoSectionEntryDaoConfig.clearIdentityScope();
        authorBeanDaoConfig.clearIdentityScope();
    }

    public AlbumEntryDao getAlbumEntryDao() {
        return albumEntryDao;
    }

    public CachePageEntryDao getCachePageEntryDao() {
        return cachePageEntryDao;
    }

    public CourseEntryDao getCourseEntryDao() {
        return courseEntryDao;
    }

    public DownloadDataDao getDownloadDataDao() {
        return downloadDataDao;
    }

    public ExercisePageEntryDao getExercisePageEntryDao() {
        return exercisePageEntryDao;
    }

    public ExerciseProgressEntryDao getExerciseProgressEntryDao() {
        return exerciseProgressEntryDao;
    }

    public FolderEntryDao getFolderEntryDao() {
        return folderEntryDao;
    }

    public GainSpreadDayEmtryDao getGainSpreadDayEmtryDao() {
        return gainSpreadDayEmtryDao;
    }

    public MusicEntryDao getMusicEntryDao() {
        return musicEntryDao;
    }

    public SearchEntryDao getSearchEntryDao() {
        return searchEntryDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    public VideoSectionEntryDao getVideoSectionEntryDao() {
        return videoSectionEntryDao;
    }

    public AuthorBeanDao getAuthorBeanDao() {
        return authorBeanDao;
    }

}
