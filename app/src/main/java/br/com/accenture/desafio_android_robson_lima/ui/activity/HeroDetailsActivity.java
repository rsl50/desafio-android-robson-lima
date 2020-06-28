package br.com.accenture.desafio_android_robson_lima.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.accenture.desafio_android_robson_lima.R;
import br.com.accenture.desafio_android_robson_lima.model.HeroResult;
import br.com.accenture.desafio_android_robson_lima.util.Util;

import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.KEY_DETAILS;

public class HeroDetailsActivity extends AppCompatActivity {

    private ImageView heroImage;
    private TextView heroName;
    private TextView heroDesciption;
    private Button buttonInfo;
    private HeroResult heroReceived;

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
}
