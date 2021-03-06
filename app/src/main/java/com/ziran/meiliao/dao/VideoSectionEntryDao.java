package com.ziran.meiliao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ziran.meiliao.entry.VideoSectionEntry;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VIDEO_SECTION_ENTRY".
*/
public class VideoSectionEntryDao extends AbstractDao<VideoSectionEntry, Long> {

    public static final String TABLENAME = "VIDEO_SECTION_ENTRY";

    /**
     * Properties of entity VideoSectionEntry.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property RootCourseId = new Property(1, String.class, "rootCourseId", false, "ROOT_COURSE_ID");
        public final static Property MFree = new Property(2, boolean.class, "mFree", false, "M_FREE");
        public final static Property Tag = new Property(3, int.class, "tag", false, "TAG");
        public final static Property IsCur = new Property(4, boolean.class, "isCur", false, "IS_CUR");
        public final static Property Duration = new Property(5, String.class, "duration", false, "DURATION");
        public final static Property Title = new Property(6, String.class, "title", false, "TITLE");
        public final static Property IsHis = new Property(7, boolean.class, "isHis", false, "IS_HIS");
        public final static Property Url = new Property(8, String.class, "url", false, "URL");
    }


    public VideoSectionEntryDao(DaoConfig config) {
        super(config);
    }
    
    public VideoSectionEntryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VIDEO_SECTION_ENTRY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ROOT_COURSE_ID\" TEXT," + // 1: rootCourseId
                "\"M_FREE\" INTEGER NOT NULL ," + // 2: mFree
                "\"TAG\" INTEGER NOT NULL ," + // 3: tag
                "\"IS_CUR\" INTEGER NOT NULL ," + // 4: isCur
                "\"DURATION\" TEXT," + // 5: duration
                "\"TITLE\" TEXT," + // 6: title
                "\"IS_HIS\" INTEGER NOT NULL ," + // 7: isHis
                "\"URL\" TEXT);"); // 8: url
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VIDEO_SECTION_ENTRY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VideoSectionEntry entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String rootCourseId = entity.getRootCourseId();
        if (rootCourseId != null) {
            stmt.bindString(2, rootCourseId);
        }
        stmt.bindLong(3, entity.getMFree() ? 1L: 0L);
        stmt.bindLong(4, entity.getTag());
        stmt.bindLong(5, entity.getIsCur() ? 1L: 0L);
 
        String duration = entity.getDuration();
        if (duration != null) {
            stmt.bindString(6, duration);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(7, title);
        }
        stmt.bindLong(8, entity.getIsHis() ? 1L: 0L);
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(9, url);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VideoSectionEntry entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String rootCourseId = entity.getRootCourseId();
        if (rootCourseId != null) {
            stmt.bindString(2, rootCourseId);
        }
        stmt.bindLong(3, entity.getMFree() ? 1L: 0L);
        stmt.bindLong(4, entity.getTag());
        stmt.bindLong(5, entity.getIsCur() ? 1L: 0L);
 
        String duration = entity.getDuration();
        if (duration != null) {
            stmt.bindString(6, duration);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(7, title);
        }
        stmt.bindLong(8, entity.getIsHis() ? 1L: 0L);
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(9, url);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VideoSectionEntry readEntity(Cursor cursor, int offset) {
        VideoSectionEntry entity = new VideoSectionEntry( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // rootCourseId
            cursor.getShort(offset + 2) != 0, // mFree
            cursor.getInt(offset + 3), // tag
            cursor.getShort(offset + 4) != 0, // isCur
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // duration
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // title
            cursor.getShort(offset + 7) != 0, // isHis
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // url
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VideoSectionEntry entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRootCourseId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMFree(cursor.getShort(offset + 2) != 0);
        entity.setTag(cursor.getInt(offset + 3));
        entity.setIsCur(cursor.getShort(offset + 4) != 0);
        entity.setDuration(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTitle(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setIsHis(cursor.getShort(offset + 7) != 0);
        entity.setUrl(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VideoSectionEntry entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VideoSectionEntry entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VideoSectionEntry entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
