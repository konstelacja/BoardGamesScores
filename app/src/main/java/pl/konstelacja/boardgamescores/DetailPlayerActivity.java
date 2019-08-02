package pl.konstelacja.boardgamescores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.konstelacja.boardgamescores.database.AppDatabase;
import pl.konstelacja.boardgamescores.database.DatabaseProvider;
import pl.konstelacja.boardgamescores.database.Player;
import pl.konstelacja.boardgamescores.database.PlayerDao;

public class DetailPlayerActivity extends AppCompatActivity {

    private static final String PLAYER_KEY = "playerId";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_detail);
        final TextView playerName = findViewById(R.id.player_name);
        final TextView playerSurname = findViewById(R.id.player_surname);
        final TextView playerNickname = findViewById(R.id.player_nickname);

        int playerId = getIntent().getIntExtra(PLAYER_KEY, -1);

        AppDatabase appDatabase = DatabaseProvider.create(getApplicationContext());
        PlayerDao playerDao = appDatabase.playerDao();

        final Disposable disposable = playerDao.getPlayerById(playerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Player>() {
                    @Override
                    public void accept(Player player) {
                        playerName.setText(player.getName());
                        playerSurname.setText(player.getSurname());
                        playerNickname.setText(player.getNickname());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public static Intent createIntent(Context context, Player player) {
        Intent intent = new Intent(context, DetailPlayerActivity.class);
        intent.putExtra(PLAYER_KEY, player.id);
        return intent;
    }
}
