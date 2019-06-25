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

public class PlayersFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_players, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.go);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPlayerActivity.class);
                startActivity(intent);
            }
        });

        List<Player> players = new ArrayList<>();
        players.add(new Player("Anna", "Krzysztof", "Bombelek"));
        players.add(new Player("Jakub", "Kowalski", "Bibuś"));
        players.add(new Player("Jan", "Nowak", "Jasiek"));
        players.add(new Player("Marcin", "Stępień", "Marcin"));
        players.add(new Player("Paweł", "Stępień", "Pawcio"));
        players.add(new Player("Michał", "Pawelec", "Młody"));
        players.add(new Player("Maria", "Klimek", "Mery"));
        players.add(new Player("Albert", "Nowak", "Alberto"));
        players.add(new Player("Klara", "Kowalczyk", "Klarcia"));
        players.add(new Player("Kajetan", "Kowal", "Kajtek"));

        PlayersAdapter playersAdapter = new PlayersAdapter(players);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        RecyclerView recyclerView = view.findViewById(R.id.players_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(playersAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
