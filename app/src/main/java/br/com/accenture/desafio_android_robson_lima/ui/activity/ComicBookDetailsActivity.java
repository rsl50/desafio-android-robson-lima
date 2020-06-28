package br.com.accenture.desafio_android_robson_lima.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.accenture.desafio_android_robson_lima.R;
import br.com.accenture.desafio_android_robson_lima.model.ComicBookResult;
import br.com.accenture.desafio_android_robson_lima.util.Util;

import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.KEY_COMICBOOK;

public class ComicBookDetailsActivity extends AppCompatActivity {

    private ImageView comicBookImage;
    private TextView comicBookName;
    private TextView comicBookPrice;
    private TextView comicBookDesciption;
    private ArrayList<ComicBookResult> comicBookResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_book_details);

        setTitle("Revista mais cara do personagem");

        initializeDetailScreen();
        loadReceivedHeroData();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.go_up, R.anim.go_down);
    }

    private void initializeDetailScreen() {
        comicBookImage = findViewById(R.id.image_comicBook);
        comicBookName = findViewById(R.id.text_comicbook_name);
        comicBookPrice = findViewById(R.id.text_comicbook_price);
        comicBookDesciption = findViewById(R.id.text_comicbook_description);
    }

    private void loadReceivedHeroData() {
        Intent receivedData = getIntent();
        if (receivedData.hasExtra(KEY_COMICBOOK)) {
            comicBookResults = receivedData.getParcelableArrayListExtra(KEY_COMICBOOK);

            List<Double> priceList = getPriceList();
            Double highestPrice = Collections.max(priceList);
            int highestPriceIndex = priceList.indexOf(highestPrice);

            comicBookName.setText(comicBookResults.get(highestPriceIndex).getTitle());
            comicBookDesciption.setText(Util.getDescription(comicBookResults.get(highestPriceIndex).getDescription()));

            comicBookPrice.setText(DecimalFormat.getCurrencyInstance().format(highestPrice));
            loadComicBookImage(comicBookResults.get(highestPriceIndex));
        }
    }

    private List<Double> getPriceList() {
        List<Double> priceList = new ArrayList<>();
        for (int i = 0; i < comicBookResults.size(); i++) {
            for (int j = 0; j < comicBookResults.get(i).getPrices().size(); j++) {
                priceList.add(comicBookResults.get(i).getPrices().get(j).getPrice());
            }
        }
        return priceList;
    }

    private void loadComicBookImage(ComicBookResult comic) {
        Util.getImage(this, comicBookImage, comic.getThumbnail().getThumbnailResource());
    }
}