package br.com.accenture.desafio_android_robson_lima.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.accenture.desafio_android_robson_lima.R;
import br.com.accenture.desafio_android_robson_lima.model.HeroResult;
import br.com.accenture.desafio_android_robson_lima.util.Util;

public class HeroesListAdapter extends RecyclerView.Adapter<HeroesListAdapter.HeroViewHolder>{

    private final Context context;

    private List<HeroResult> heroes;

    public HeroesListAdapter(Context context, List heroes) {
        this.context = context;
        this.heroes = heroes;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_hero, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull HeroViewHolder holder, int position){
        HeroResult hero = heroes.get(position);
        holder.bind(hero);
    }

    @Override
    public int getItemCount () { return heroes.size(); }

    class HeroViewHolder extends RecyclerView.ViewHolder {
        private final TextView heroName;
        private final ImageView heroImage;
        private final ConstraintLayout parentLayout;
        private HeroResult hero;

        public HeroViewHolder(@NonNull View itemView) {
            super(itemView);
            heroName = itemView.findViewById(R.id.label_hero_name);
            heroImage = itemView.findViewById(R.id.image_hero);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

        public void bind(HeroResult heroResult) {
            this.hero = heroResult;

            heroName.setText(hero.getName());
            heroImage.setImageResource(R.drawable.ic_launcher_background);
            Util.getImage(context, heroImage, hero.getThumbnail().getThumbnailResource());
        }
    }
}
