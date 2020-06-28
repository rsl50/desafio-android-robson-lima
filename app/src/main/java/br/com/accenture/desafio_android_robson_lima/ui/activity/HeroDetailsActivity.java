package br.com.accenture.desafio_android_robson_lima.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.accenture.desafio_android_robson_lima.R;
import br.com.accenture.desafio_android_robson_lima.model.ComicBook;
import br.com.accenture.desafio_android_robson_lima.model.ComicBookResult;
import br.com.accenture.desafio_android_robson_lima.model.HeroResult;
import br.com.accenture.desafio_android_robson_lima.retrofit.HeroRetrofit;
import br.com.accenture.desafio_android_robson_lima.retrofit.service.HeroService;
import br.com.accenture.desafio_android_robson_lima.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.KEY_COMICBOOK;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.KEY_DETAILS;

public class HeroDetailsActivity extends AppCompatActivity {

    private ImageView heroImage;
    private TextView heroName;
    private TextView heroDesciption;
    private Button buttonInfo;
    private HeroResult heroReceived;
    private ArrayList<ComicBookResult> comicBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_details);

        setTitle("Detalhes do Personagem");

        initializeDetailScreen();
        loadReceivedHeroData();
    }

    private void initializeDetailScreen() {
        heroImage = findViewById(R.id.image_hero);
        heroName = findViewById(R.id.text_hero_name);
        heroDesciption = findViewById(R.id.text_hero_description);
        buttonInfo = findViewById(R.id.button_info);
    }

    private void loadReceivedHeroData() {
        Intent receivedData = getIntent();
        if (receivedData.hasExtra(KEY_DETAILS)) {
            heroReceived = receivedData.getParcelableExtra(KEY_DETAILS);

            heroName.setText(heroReceived.getName());
            heroDesciption.setText((Util.getDescription(heroReceived.getDescription())));

            new Thread() {
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Util.showDialog(HeroDetailsActivity.this, getString(R.string.loading_message));
                            loadHeroImage(heroReceived);
                            getHeroComics(heroReceived.getId());
                            Util.hideDialog();
                        }
                    });
                }
            }.start();
        }
    }

    private void loadHeroImage(HeroResult hero) {
        Util.getImage(this, heroImage, hero.getThumbnail().getThumbnailResource());
    }

    private void goToComicBookDetails(ArrayList<ComicBookResult> comic) {
        Intent intent = new Intent(HeroDetailsActivity.this, ComicBookDetailsActivity.class);
        intent.putParcelableArrayListExtra(KEY_COMICBOOK, comic);
        startActivity(intent);
        finish();
    }

    private void getHeroComics(int heroId) {
        HeroService service = new HeroRetrofit().getHeroService();
        Call<ComicBook> call = service.getComicBooks(heroId);

        call.enqueue(new Callback<ComicBook>() {
            @Override
            public void onResponse(Call<ComicBook> call, Response<ComicBook> response) {
                if (response.isSuccessful()) {
                    comicBookList = response.body().getData().getComicBookResult();

                    buttonInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goToComicBookDetails(comicBookList);
                        }
                    });

                    Util.hideDialog();
                } else {
                    Log.e("DesafioAndroidApp", "Response code: " + response.code() + "Response: "+  response.message());
                }
            }

            @Override
            public void onFailure(Call<ComicBook> call, Throwable t) {
                Log.e("DesafioAndroidApp", "Call Failure:" + t.getMessage());
            }
        });
    }
}
