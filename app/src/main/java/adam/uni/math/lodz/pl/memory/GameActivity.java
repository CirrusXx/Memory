package adam.uni.math.lodz.pl.memory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    private String pathOfThePicture;
    private int IdOfSelectedPicture;
    private ArrayList<Integer> listOfTheShuffledNumbers = new ArrayList<>();
    private int counterForSelectedPictures = 0;
    private String tagOfTheFirstClickedPicture;
    private String tagOfTheSecondClickedPicture;
    private int idOfTheFirstClickedPicture;
    private int idOfTheSecondClickedPicture;
    private boolean ifPicturesMatched = true;
    private int counterOfTheMatchedPictures=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setWinTextOff();
        generateRandomNumberList();
    }

    public void LoadImageFromStorageOnClick(View view) {
        try {
            ImageButton clickedImageButton = (ImageButton) view;
            String resourceNameOfSelectedPicture = getResources().getResourceEntryName(clickedImageButton.getId());
            String lastIndexOfThePictureId = String.valueOf(resourceNameOfSelectedPicture.charAt(7));
            int numberOfThePictureFromIndex = Integer.parseInt(lastIndexOfThePictureId);
            setPathOfThePicture(listOfTheShuffledNumbers.get(numberOfThePictureFromIndex));
            setIdOfSelectedPicture(numberOfThePictureFromIndex);
            File fileOfThePicture = new File(getPathOfThePicture(), "profile.jpg");
            Bitmap bitmapOfTheSelectedPicture = BitmapFactory.decodeStream(new FileInputStream(fileOfThePicture));
            ImageButton selectedImageButton = findViewById(getIdOfSelectedPicture());
            selectedImageButton.setTag((listOfTheShuffledNumbers.get(numberOfThePictureFromIndex) / 2));
            selectedImageButton.setImageBitmap(bitmapOfTheSelectedPicture);
            setTagAndIdOfTheClickedPicture();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTagAndIdOfTheClickedPicture() {
        if (counterForSelectedPictures == 0) {
            setImagesOnNull();
            ImageButton firstImage = findViewById(IdOfSelectedPicture);
            setTagOfTheFirstClickedPicture(firstImage.getTag().toString());
            setIdOfTheFirstClickedPicture(firstImage.getId());
            firstImage.setClickable(false);
            counterForSelectedPictures++;
        } else {
            ImageButton secondImage = findViewById(IdOfSelectedPicture);
            setTagOfTheSecondClickedPicture(secondImage.getTag().toString());
            setIdOfTheSecondClickedPicture(secondImage.getId());
            counterForSelectedPictures = 0;
            CheckIfTagsOfThePicturesAreEquals();
        }
    }

    private void CheckIfTagsOfThePicturesAreEquals() {
        if (tagOfTheFirstClickedPicture.equals(tagOfTheSecondClickedPicture)) {
            makeImagesGone();
            ifPicturesMatched = true;
        } else ifPicturesMatched = false;
    }

    private void makeImagesGone() {
        ImageButton image1 = findViewById(idOfTheFirstClickedPicture);
        image1.setVisibility(View.GONE);
        ImageButton image2 = findViewById(idOfTheSecondClickedPicture);
        image2.setVisibility(View.GONE);
        counterOfTheMatchedPictures++;
        checkIfAllPicturesMatched();
    }

    private void checkIfAllPicturesMatched(){
        if(counterOfTheMatchedPictures ==4)
        {
            setWinTextOn();
        }
    }

    private void setImagesOnNull() {
        if (!ifPicturesMatched) {
            ImageButton image1 = findViewById(idOfTheFirstClickedPicture);
            image1.setImageDrawable(null);
            image1.setClickable(true);
            ImageButton image2 = findViewById(idOfTheSecondClickedPicture);
            image2.setImageDrawable(null);
        }
    }

    private void generateRandomNumberList() {
        for (int i = 0; i <= 7; ++i) listOfTheShuffledNumbers.add(i);
        Collections.shuffle(listOfTheShuffledNumbers);
    }

    private void setPathOfThePicture(int i) {
        pathOfThePicture = getIntent().getStringArrayListExtra("listOfPaths").get(i);
    }

    private void setIdOfSelectedPicture(int i) {
        IdOfSelectedPicture = getResources().getIdentifier("picture" + i, "id", getPackageName());
    }

    private String getPathOfThePicture() {
        return pathOfThePicture;
    }

    private int getIdOfSelectedPicture() {
        return IdOfSelectedPicture;
    }

    private void setTagOfTheFirstClickedPicture(String tag) {
        tagOfTheFirstClickedPicture = tag;
    }

    private void setTagOfTheSecondClickedPicture(String tag) {
        tagOfTheSecondClickedPicture = tag;
    }

    private void setIdOfTheFirstClickedPicture(int id) {
        idOfTheFirstClickedPicture = id;
    }

    private void setIdOfTheSecondClickedPicture(int id) {
        idOfTheSecondClickedPicture = id;
    }

    private void setWinTextOn(){
        TextView winTextView = findViewById(R.id.textForCompletingAGame);
        winTextView.setVisibility(View.VISIBLE);
    }

    private void setWinTextOff(){
        TextView winTextView = findViewById(R.id.textForCompletingAGame);
        winTextView.setVisibility(View.INVISIBLE);
    }
}


