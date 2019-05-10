package pl.konstelacja.boardgamescores;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayerActivity extends AppCompatActivity {

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
                    Player player = new Player(namePlayer,surnamePlayer,nicknamePlayer);
                    finish();
                }else {
                    Context context = getApplicationContext();
                    CharSequence text = "Please input name and surname...";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}
