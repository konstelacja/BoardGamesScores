package pl.konstelacja.boardgamescores;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import pl.konstelacja.boardgamescores.database.DatabaseProvider;
import pl.konstelacja.boardgamescores.database.Game;

public class AddGameActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        final EditText name = findViewById(R.id.name_of_game);
        final EditText description = findViewById(R.id.game_description);
        Button submit = findViewById(R.id.game_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String gameDescription = description.getText().toString();

                if (nameText.length() > 0) {
                    Game game = new Game(nameText, gameDescription);

                    Disposable disposable = DatabaseProvider.create(getApplicationContext())
                            .gameDao()
                            .insert(game)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action() {
                                @Override
                                public void run() throws Exception {
                                    finish();
                                }
                            });

                    compositeDisposable.add(disposable);
                } else {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, R.string.error_in_input_game, duration);
                    toast.show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
