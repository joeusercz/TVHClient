package org.tvheadend.tvhclient.data.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import org.tvheadend.tvhclient.data.dao.ChannelDao;
import org.tvheadend.tvhclient.data.dao.ChannelTagDao;
import org.tvheadend.tvhclient.data.dao.ConnectionDao;
import org.tvheadend.tvhclient.data.dao.ProgramDao;
import org.tvheadend.tvhclient.data.dao.RecordingDao;
import org.tvheadend.tvhclient.data.dao.SeriesRecordingDao;
import org.tvheadend.tvhclient.data.dao.ServerProfileDao;
import org.tvheadend.tvhclient.data.dao.ServerStatusDao;
import org.tvheadend.tvhclient.data.dao.TagAndChannelDao;
import org.tvheadend.tvhclient.data.dao.TimerRecordingDao;
import org.tvheadend.tvhclient.data.entity.Channel;
import org.tvheadend.tvhclient.data.entity.ChannelTag;
import org.tvheadend.tvhclient.data.entity.Connection;
import org.tvheadend.tvhclient.data.entity.Program;
import org.tvheadend.tvhclient.data.entity.Recording;
import org.tvheadend.tvhclient.data.entity.SeriesRecording;
import org.tvheadend.tvhclient.data.entity.ServerProfile;
import org.tvheadend.tvhclient.data.entity.ServerStatus;
import org.tvheadend.tvhclient.data.entity.TagAndChannel;
import org.tvheadend.tvhclient.data.entity.TimerRecording;

@Database(
        entities = {
                TimerRecording.class,
                SeriesRecording.class,
                Recording.class,
                Program.class,
                Channel.class,
                ChannelTag.class,
                TagAndChannel.class,
                Connection.class,
                ServerProfile.class,
                ServerStatus.class
        },
        version = 5)
public abstract class AppRoomDatabase extends RoomDatabase {

    private static AppRoomDatabase instance;

    public static AppRoomDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppRoomDatabase.class) {
                instance = Room.databaseBuilder(context, AppRoomDatabase.class, "tvhclient")
                        .addMigrations(MIGRATION_1_2)
                        .addMigrations(MIGRATION_2_3)
                        .addMigrations(MIGRATION_3_4)
                        .addMigrations(MIGRATION_4_5)
                        .build();
            }
        }
        return instance;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE connections ADD COLUMN last_update INTEGER NOT NULL DEFAULT 0;");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE connections ADD COLUMN sync_required INTEGER NOT NULL DEFAULT 0;");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE channels ADD COLUMN display_number INTEGER NOT NULL DEFAULT 0;");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE recordings ADD COLUMN duplicate INTEGER NOT NULL DEFAULT 0;");
        }
    };

    public abstract TimerRecordingDao getTimerRecordingDao();

    public abstract SeriesRecordingDao getSeriesRecordingDao();

    public abstract RecordingDao getRecordingDao();

    public abstract ChannelDao getChannelDao();

    public abstract ChannelTagDao getChannelTagDao();

    public abstract TagAndChannelDao getTagAndChannelDao();

    public abstract ProgramDao getProgramDao();

    public abstract ConnectionDao getConnectionDao();

    public abstract ServerProfileDao getServerProfileDao();

    public abstract ServerStatusDao getServerStatusDao();

}
