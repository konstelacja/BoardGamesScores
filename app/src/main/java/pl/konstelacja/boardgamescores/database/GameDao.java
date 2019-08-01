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

    @Query("SELECT * FROM game WHERE id = :gameId")
    Single<Game> getGameById(int gameId);

    @Insert
    Completable insert(Game game);
}
