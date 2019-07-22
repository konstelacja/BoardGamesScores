package pl.konstelacja.boardgamescores.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Player {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String surname;

    @ColumnInfo
    private String nickName;

    public Player(String name, String surname, String nickName) {
        this.name = name;
        this.surname = surname;
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNickName() {
        return nickName;
    }
}
