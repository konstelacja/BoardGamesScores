package pl.konstelacja.boardgamescores.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM game")
    List<Game> getAll();

    @Insert
    void insert(Game game);
}
