package pl.konstelacja.boardgamescores;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pl.konstelacja.boardgamescores.database.Game;

public class GamesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddGameActivity.class);
                startActivity(intent);
            }
        });

        List<Game> games = new ArrayList<>();
        games.add(new Game("Blood Rage", "Gra stategiczna."));
        games.add(new Game("Great Western Train", "Gra strategiczna."));
        games.add(new Game("Pociągi", "Gra rodzinna"));
        games.add(new Game("Tzolk'in", "Gra rodzinna"));
        games.add(new Game("SmallWorld", "Gra przygodowa"));

        GamesAdapter gamesAdapter = new GamesAdapter(games);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        RecyclerView recyclerView = view.findViewById(R.id.games_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gamesAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
