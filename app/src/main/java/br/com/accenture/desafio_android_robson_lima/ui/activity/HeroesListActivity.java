package br.com.accenture.desafio_android_robson_lima.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.com.accenture.desafio_android_robson_lima.R;
import br.com.accenture.desafio_android_robson_lima.model.Hero;
import br.com.accenture.desafio_android_robson_lima.model.HeroResult;
import br.com.accenture.desafio_android_robson_lima.retrofit.HeroRetrofit;
import br.com.accenture.desafio_android_robson_lima.retrofit.service.HeroService;
import br.com.accenture.desafio_android_robson_lima.ui.adapter.HeroesListAdapter;
import br.com.accenture.desafio_android_robson_lima.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroesListActivity extends AppCompatActivity {

    private HeroesListAdapter adapter;
    private RecyclerView heroesList;
    private List<HeroResult> heroResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);

        configureRecyclerView();
        Util.showDialog(this, getString(R.string.loading_message));

        getDataFromApi();
    }

    private void configureRecyclerView() {
        heroesList = findViewById(R.id.list_heroes_recyclerview);
        GridLayoutManager grid = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        grid.setOrientation(GridLayoutManager.VERTICAL);
        heroesList.setLayoutManager(grid);
        heroesList.setAdapter(adapter);
    }

    private void getDataFromApi() {
        HeroService service = new HeroRetrofit().getHeroService();
        Call<Hero> call = service.getHeroesList();

        call.enqueue(new Callback<Hero>() {
            @Override
            public void onResponse(Call<Hero> call, Response<Hero> response) {
                Util.hideDialog();

                if (response.isSuccessful()) {
                    heroResult = response.body().getHeroData().getHeroResult();
                    adapter = new HeroesListAdapter(HeroesListActivity.this, heroResult);
                    heroesList.setAdapter(adapter);
                } else {
                    Log.e(getString(R.string.LOG_TAG_APP), getString(R.string.response_code) + response.code() + getString(R.string.response_string)+  response.message());
                }

            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {
                Util.hideDialog();

                Log.e(getString(R.string.LOG_TAG_APP), getString(R.string.api_call_failure) + t.getMessage());
            }
        });
    }
}