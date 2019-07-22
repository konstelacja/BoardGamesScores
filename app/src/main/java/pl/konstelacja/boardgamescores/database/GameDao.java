package pl.konstelacja.boardgamescores.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface GameDao {

    @Query("SELECT * FROM game")
    Single<List<Game>> getAll();

    @Insert
    Completable insert(Game game);
}
