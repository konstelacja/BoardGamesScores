package pl.konstelacja.boardgamescores;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.konstelacja.boardgamescores.database.AppDatabase;
import pl.konstelacja.boardgamescores.database.DatabaseProvider;
import pl.konstelacja.boardgamescores.database.Game;
import pl.konstelacja.boardgamescores.database.GameDao;

public class GamesFragment extends Fragment {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddGameActivity.class);
                startActivity(intent);
            }
        });

        AppDatabase appDatabase = DatabaseProvider.create(getContext().getApplicationContext());
        GameDao gameDao = appDatabase.gameDao();

        final Disposable disposable = gameDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Game>>() {
                    @Override
                    public void accept(List<Game> games) {
                        GamesAdapter gamesAdapter = new GamesAdapter(games, new RecyclerViewClickListener() {
                            @Override
                            public void onClick(Game game) {
                                Toast.makeText(getContext(), "Kliknąłeś " + game.getName(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

                        RecyclerView recyclerView = view.findViewById(R.id.games_list);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(gamesAdapter);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
                        recyclerView.addItemDecoration(dividerItemDecoration);
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }
}
