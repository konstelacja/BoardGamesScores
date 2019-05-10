package pl.konstelacja.boardgamescores;

public class Player {

    private String name;
    private String surname;
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
