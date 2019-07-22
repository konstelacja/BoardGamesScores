package pl.konstelacja.boardgamescores.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player")
    Single<List<Player>> getAll();

    @Insert
    Completable insert(Player player);
}

