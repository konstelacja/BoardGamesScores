package pl.konstelacja.boardgamescores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.konstelacja.boardgamescores.database.Player;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {

    private List<Player> players;
    private PlayersRecyclerViewClickListener listener;

    public PlayersAdapter(List<Player> list, PlayersRecyclerViewClickListener listener) {
        this.players = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.players_item_row, parent, false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = players.get(position);
        holder.bind(player);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nickname;

        private Player player;
        private PlayersRecyclerViewClickListener listener;

        public ViewHolder(View view, PlayersRecyclerViewClickListener listener) {
            super(view);
            nickname = view.findViewById(R.id.player_nickname);
            this.listener = listener;

            view.setOnClickListener(this);
        }

        void bind(Player player){
            this.player = player;
            nickname.setText(player.getNickname());
        }

        @Override
        public void onClick(View v) {
            listener.onClick(player);
        }
    }
}
