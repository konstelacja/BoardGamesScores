package pl.konstelacja.boardgamescores.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseProvider {

    private static AppDatabase database;

    private DatabaseProvider() {
    }

    public synchronized static AppDatabase create(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, AppDatabase.class, "app-database").build();
        }
        return database;
    }
}
