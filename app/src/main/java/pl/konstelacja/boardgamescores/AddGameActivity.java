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

public class AddGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        final EditText name = findViewById(R.id.game_of_name);
        final EditText description = findViewById(R.id.game_description);
        Button submit = findViewById(R.id.game_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String gameDescription = description.getText().toString();

                if (nameText.length() > 0) {
                    Game game = new Game(nameText, gameDescription);
                    finish();
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Please input game of name...";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}
