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
import pl.konstelacja.boardgamescores.database.Player;
import pl.konstelacja.boardgamescores.database.PlayerDao;

public class PlayersFragment extends Fragment {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_players, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.go);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPlayerActivity.class);
                startActivity(intent);
            }
        });

        AppDatabase appDatabase = DatabaseProvider.create(getContext().getApplicationContext());
        PlayerDao playerDao = appDatabase.playerDao();

        final Disposable disposable = playerDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Player>>() {
                               @Override
                               public void accept(List<Player> players) {
                                   PlayersAdapter playersAdapter = new PlayersAdapter(players, new PlayersRecyclerViewClickListener() {
                                       @Override
                                       public void onClick(Player player) {
                                           Toast.makeText(getContext(), "Kliknąłeś " + player.getNickName(),Toast.LENGTH_SHORT).show();
                                       }
                                   });
                                   LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

                                   RecyclerView recyclerView = view.findViewById(R.id.players_list);
                                   recyclerView.setLayoutManager(layoutManager);
                                   recyclerView.setAdapter(playersAdapter);

                                   DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                                           layoutManager.getOrientation());
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

