package br.com.accenture.desafio_android_robson_lima.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.OFFSET_VALUE;

public class HeroesListActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Listagem de Personagens";
    private HeroesListAdapter adapter;
    private RecyclerView heroesList;
    private List<HeroResult> heroResult;
    private boolean isLoading = false;
    private int offsetCounter = OFFSET_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);

        setTitle(TITLE_APPBAR);
        
        configureRecyclerView();
        initScrollListener();
        getDataFromApi(0);
    }

    private void configureRecyclerView() {
        heroesList = findViewById(R.id.list_heroes_recyclerview);
        GridLayoutManager grid = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        grid.setOrientation(GridLayoutManager.VERTICAL);
        heroesList.setLayoutManager(grid);
        heroesList.setAdapter(adapter);
    }

    private void getDataFromApi(int offset) {
        HeroService service = new HeroRetrofit().getHeroService();
        Call<Hero> call;

        Util.showDialog(this, getString(R.string.loading_message));

        if (offset == 0) {
            call = service.getHeroesList();
        } else {
            call = service.getHeroesListOffset(offset);
        }

        call.enqueue(new Callback<Hero>() {
            @Override
            public void onResponse(Call<Hero> call, Response<Hero> response) {
                if (response.isSuccessful()) {
                    if (offset == 0) {
                        heroResult = response.body().getHeroData().getHeroResult();
                        adapter = new HeroesListAdapter(HeroesListActivity.this, heroResult);
                        heroesList.setAdapter(adapter);
                    } else {
                        heroResult.add(null);
                        adapter.notifyItemInserted(heroResult.size() - 1);

                        heroResult.remove(heroResult.size() - 1);
                        int scrollPosition = heroResult.size();
                        adapter.notifyItemRemoved(scrollPosition);

                        int currentSize = scrollPosition;
                        int nextLimit = currentSize + OFFSET_VALUE;
                        if (nextLimit < response.body().getHeroData().getTotal()) {
                            int index = 0;
                            while (currentSize < nextLimit) {
                                heroResult.add(response.body().getHeroData().getHeroResult().get(index));
                                currentSize++;
                                index++;
                            }

                            offsetCounter += nextLimit;

                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(HeroesListActivity.this, R.string.reached_hero_list_end, Toast.LENGTH_LONG).show();
                        }

                        isLoading = false;
                    }
                } else {
                    Util.commErrorDialog(HeroesListActivity.this, response.message(), Util.getApiError(response.code()));
                    Log.e(getString(R.string.LOG_TAG_APP), getString(R.string.response_code) + response.code() + getString(R.string.response_string)+  response.message());
                }

                Util.hideDialog();
            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {
                Util.hideDialog();

                if (!Util.isNetworkAvailable(HeroesListActivity.this)) {
                    boolean choice = Util.commErrorDialog(HeroesListActivity.this, getString(R.string.no_connection), getString(R.string.retry_connection));
                    if (choice) {
                        Log.e(getString(R.string.LOG_TAG_APP), "Opção SIM");
                        getDataFromApi(offsetCounter);
                    }
                }
                Log.e(getString(R.string.LOG_TAG_APP), getString(R.string.api_call_failure) + t.getMessage());
            }
        });
    }

    private void initScrollListener() {
        heroesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager grid = (GridLayoutManager) heroesList.getLayoutManager();

                if (!isLoading) {
                    if ((grid != null) && (grid.findLastCompletelyVisibleItemPosition() == heroResult.size() - 1)) {
                        //Reached bottom of the list
                        getDataFromApi(offsetCounter);
                        isLoading = true;
                    }
                }
            }
        });
    }
}