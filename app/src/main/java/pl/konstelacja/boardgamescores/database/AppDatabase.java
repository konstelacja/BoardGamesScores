package pl.konstelacja.boardgamescores.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Game.class, Player.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GameDao gameDao();

    public abstract PlayerDao playerDao();
}
