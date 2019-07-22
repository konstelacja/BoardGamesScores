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
import pl.konstelacja.boardgamescores.database.Player;

public class AddPlayerActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        final EditText name = findViewById(R.id.player_name);
        final EditText surname = findViewById(R.id.player_surname);
        final EditText nickname = findViewById(R.id.player_nickname);
        Button submit = findViewById(R.id.submit_player);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePlayer = name.getText().toString();
                String surnamePlayer = surname.getText().toString();
                String nicknamePlayer = nickname.getText().toString();

                if (namePlayer.length() > 0 && surnamePlayer.length() > 0) {
                    Player player = new Player(namePlayer, surnamePlayer, nicknamePlayer);

                    Disposable disposable = DatabaseProvider.create(getApplicationContext())
                            .playerDao()
                            .insert(player)
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
                    Toast toast = Toast.makeText(context, R.string.error_in_input_player, duration);
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
