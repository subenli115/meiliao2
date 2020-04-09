package com.ziran.meiliao.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ziran.meiliao.ui.bean.AuthorBean;

import com.ziran.meiliao.entry.CourseEntry;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COURSE_ENTRY".
*/
public class CourseEntryDao extends AbstractDao<CourseEntry, Long> {

    public static final String TABLENAME = "COURSE_ENTRY";

    /**
     * Properties of entity CourseEntry.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Name = new Property(0, String.class, "name", false, "NAME");
        public final static Property CourseId = new Property(1, String.class, "courseId", false, "COURSE_ID");
        public final static Property Url = new Property(2, String.class, "url", false, "URL");
        public final static Property Picture = new Property(3, String.class, "picture", false, "PICTURE");
        public final static Property Id = new Property(4, Long.class, "id", true, "_id");
        public final static Property AuthorName = new Property(5, String.class, "authorName", false, "AUTHOR_NAME");
        public final static Property Detail = new Property(6, String.class, "detail", false, "DETAIL");
        public final static Property Title = new Property(7, String.class, "title", false, "TITLE");
        public final static Property Path = new Property(8, String.class, "path", false, "PATH");
        public final static Property FileSize = new Property(9, long.class, "fileSize", false, "FILE_SIZE");
        public final static Property WatchCount = new Property(10, int.class, "watchCount", false, "WATCH_COUNT");
        public final static Property IsCheckIsBuy = new Property(11, boolean.class, "isCheckIsBuy", false, "IS_CHECK_IS_BUY");
        public final static Property Price = new Property(12, String.class, "price", false, "PRICE");
        public final static Property IsBuy = new Property(13, boolean.class, "isBuy", false, "IS_BUY");
        public final static Property Status = new Property(14, int.class, "status", false, "STATUS");
        public final static Property Tag = new Property(15, int.class, "tag", false, "TAG");
        public final static Property Author = new Property(16, Long.class, "author", false, "AUTHOR");
    }

    private DaoSession daoSession;


    public CourseEntryDao(DaoConfig config) {
        super(config);
    }
    
    public CourseEntryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COURSE_ENTRY\" (" + //
                "\"NAME\" TEXT," + // 0: name
                "\"COURSE_ID\" TEXT," + // 1: courseId
                "\"URL\" TEXT," + // 2: url
                "\"PICTURE\" TEXT," + // 3: picture
                "\"_id\" INTEGER PRIMARY KEY ," + // 4: id
                "\"AUTHOR_NAME\" TEXT," + // 5: authorName
                "\"DETAIL\" TEXT," + // 6: detail
                "\"TITLE\" TEXT," + // 7: title
                "\"PATH\" TEXT," + // 8: path
                "\"FILE_SIZE\" INTEGER NOT NULL ," + // 9: fileSize
                "\"WATCH_COUNT\" INTEGER NOT NULL ," + // 10: watchCount
                "\"IS_CHECK_IS_BUY\" INTEGER NOT NULL ," + // 11: isCheckIsBuy
                "\"PRICE\" TEXT," + // 12: price
                "\"IS_BUY\" INTEGER NOT NULL ," + // 13: isBuy
                "\"STATUS\" INTEGER NOT NULL ," + // 14: status
                "\"TAG\" INTEGER NOT NULL ," + // 15: tag
                "\"AUTHOR\" INTEGER);"); // 16: author
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COURSE_ENTRY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CourseEntry entity) {
        stmt.clearBindings();
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(1, name);
        }
 
        String courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindString(2, courseId);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(3, url);
        }
 
        String picture = entity.getPicture();
        if (picture != null) {
            stmt.bindString(4, picture);
        }
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(5, id);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(6, authorName);
        }
 
        String detail = entity.getDetail();
        if (detail != null) {
            stmt.bindString(7, detail);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(8, title);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(9, path);
        }
        stmt.bindLong(10, entity.getFileSize());
        stmt.bindLong(11, entity.getWatchCount());
        stmt.bindLong(12, entity.getIsCheckIsBuy() ? 1L: 0L);
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(13, price);
        }
        stmt.bindLong(14, entity.getIsBuy() ? 1L: 0L);
        stmt.bindLong(15, entity.getStatus());
        stmt.bindLong(16, entity.getTag());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CourseEntry entity) {
        stmt.clearBindings();
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(1, name);
        }
 
        String courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindString(2, courseId);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(3, url);
        }
 
        String picture = entity.getPicture();
        if (picture != null) {
            stmt.bindString(4, picture);
        }
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(5, id);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(6, authorName);
        }
 
        String detail = entity.getDetail();
        if (detail != null) {
            stmt.bindString(7, detail);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(8, title);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(9, path);
        }
        stmt.bindLong(10, entity.getFileSize());
        stmt.bindLong(11, entity.getWatchCount());
        stmt.bindLong(12, entity.getIsCheckIsBuy() ? 1L: 0L);
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(13, price);
        }
        stmt.bindLong(14, entity.getIsBuy() ? 1L: 0L);
        stmt.bindLong(15, entity.getStatus());
        stmt.bindLong(16, entity.getTag());
    }

    @Override
    protected final void attachEntity(CourseEntry entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4);
    }    

    @Override
    public CourseEntry readEntity(Cursor cursor, int offset) {
        CourseEntry entity = new CourseEntry( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // name
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // courseId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // url
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // picture
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // id
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // authorName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // detail
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // title
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // path
            cursor.getLong(offset + 9), // fileSize
            cursor.getInt(offset + 10), // watchCount
            cursor.getShort(offset + 11) != 0, // isCheckIsBuy
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // price
            cursor.getShort(offset + 13) != 0, // isBuy
            cursor.getInt(offset + 14), // status
            cursor.getInt(offset + 15) // tag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CourseEntry entity, int offset) {
        entity.setName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setCourseId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPicture(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setAuthorName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDetail(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTitle(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPath(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFileSize(cursor.getLong(offset + 9));
        entity.setWatchCount(cursor.getInt(offset + 10));
        entity.setIsCheckIsBuy(cursor.getShort(offset + 11) != 0);
        entity.setPrice(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setIsBuy(cursor.getShort(offset + 13) != 0);
        entity.setStatus(cursor.getInt(offset + 14));
        entity.setTag(cursor.getInt(offset + 15));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CourseEntry entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CourseEntry entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CourseEntry entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getAuthorBeanDao().getAllColumns());
            builder.append(" FROM COURSE_ENTRY T");
            builder.append(" LEFT JOIN AUTHOR_BEAN T0 ON T.\"AUTHOR\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected CourseEntry loadCurrentDeep(Cursor cursor, boolean lock) {
        CourseEntry entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        AuthorBean author = loadCurrentOther(daoSession.getAuthorBeanDao(), cursor, offset);
        entity.setAuthor(author);

        return entity;    
    }

    public CourseEntry loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<CourseEntry> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<CourseEntry> list = new ArrayList<CourseEntry>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<CourseEntry> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<CourseEntry> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
