package pl.konstelacja.boardgamescores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {

    private List<Player> players;

    public PlayersAdapter(List<Player> list) {
        this.players = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.players_item_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = players.get(position);
        holder.name.setText(player.getName());
        holder.surname.setText(player.getSurname());
        holder.nickname.setText(player.getNickName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView surname;
        public TextView nickname;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.player_name);
            surname = view.findViewById(R.id.player_surname);
            nickname = view.findViewById(R.id.player_nickname);
        }

    }
}
