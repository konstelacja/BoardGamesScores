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
import pl.konstelacja.boardgamescores.database.Game;
import pl.konstelacja.boardgamescores.database.GameDao;

public class DetailGameActivity extends AppCompatActivity {

    private static final String GAME_KEY = "gameId";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_detail);
        final TextView nameOfGame = findViewById(R.id.name_of_game);
        final TextView descriptionOfGame = findViewById(R.id.game_description);

        int gameId = getIntent().getIntExtra(GAME_KEY, -1);

        AppDatabase appDatabase = DatabaseProvider.create(getApplicationContext());
        GameDao gameDao = appDatabase.gameDao();

        final Disposable disposable = gameDao.getGameById(gameId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Game>() {
                    @Override
                    public void accept(Game game) {
                        nameOfGame.setText(game.getName());
                        descriptionOfGame.setText(game.getDescription());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public static Intent createIntent(Context context, Game game) {
        Intent intent = new Intent(context, DetailGameActivity.class);
        intent.putExtra(GAME_KEY, game.id);
        return intent;
    }
}

