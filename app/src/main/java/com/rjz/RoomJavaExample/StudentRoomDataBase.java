package com.rjz.RoomJavaExample;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

// 3 of 5
@Database(entities = {Student.class}, version = 2, exportSchema = false)
public abstract class StudentRoomDataBase extends RoomDatabase {

    // Room migration version "1" to "2".
    // added one more column for age.
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'student_table' ADD COLUMN 'age' INTEGER NOT NULL DEFAULT 0");
            Log.e("!_@_@", "Migration called");
        }
    };
    private static StudentRoomDataBase INSTANCE;

    static StudentRoomDataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            /* synchronized (StudentRoomDataBase.class)*/
            {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentRoomDataBase.class, "stud_database")
                            .addMigrations(MIGRATION_1_2) // when want to update table use migration.
                            .allowMainThreadQueries()
                            // TODO NOTE for .allowMainThreadQueries() 1B of 2B
                            // if not want to make query on main thread then
                            // remove this line. you have to use Asynktask for it.
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract StudentDao getStudentDao();
}
