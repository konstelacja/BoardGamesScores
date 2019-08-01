package pl.konstelacja.boardgamescores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.konstelacja.boardgamescores.database.Game;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private RecyclerViewClickListener listener;
    private List<Game> games;

    public GamesAdapter(List<Game> list, RecyclerViewClickListener listener) {
        this.games = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_item_row, parent, false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = games.get(position);
        holder.bind(game);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;

        private Game game;
        private RecyclerViewClickListener listener;

        ViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            name = view.findViewById(R.id.name_of_game);
            this.listener = listener;

            view.setOnClickListener(this);
        }

        void bind(Game game) {
            this.game = game;
            name.setText(game.getName());
        }

        @Override
        public void onClick(View v) {
            listener.onClick(game);
        }
    }
}
