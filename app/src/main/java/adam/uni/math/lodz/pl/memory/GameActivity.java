package adam.uni.math.lodz.pl.memory;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Random r = new Random();
    private String path;
    private int viewID;
    ArrayList<Integer> number = new ArrayList<>();
    int counter = 0;
    String tag1;
    String tag2;
    int id1;
    int id2;
    boolean ifTrue = true;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        generateRandomNumberList();
    }

    public void LoadImageFromStorageOnClick(View view) {
        try {
            ImageButton clickedImageButton = (ImageButton) view;
            String resourceName = getResources().getResourceEntryName(clickedImageButton.getId());
            String id = String.valueOf(resourceName.charAt(7));
            int i = Integer.parseInt(id);
            setPath(number.get(i));
            setViewID(i);
            File f = new File(getPath(), "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageButton img = findViewById(getViewID());
            img.setTag((number.get(i) / 2));
            img.setImageBitmap(b);
            checkTag();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("SetTextI18n")
    public void checkTag() {


        if (counter == 0) {
            checkIfFalse();
            ImageButton image = findViewById(viewID);
            setTag1(image.getTag().toString());
            setId1(image.getId());
            image.setClickable(false);
            counter++;
        } else {
            ImageButton image = findViewById(viewID);
            setTag2(image.getTag().toString());
            setId2(image.getId());
            counter = 0;
            CheckIfTagsOfThePicturesAreEquals();
        }
    }

    private void CheckIfTagsOfThePicturesAreEquals() {
        if (tag1.equals(tag2)) {
            makeImagesInvisible();
            ifTrue = true;
        } else ifTrue = false;
    }

    private void makeImagesInvisible() {
        ImageButton image1 = findViewById(id1);
        image1.setVisibility(View.GONE);
        ImageButton image2 = findViewById(id2);
        image2.setVisibility(View.GONE);
    }


    public void checkIfFalse() {
        if (!ifTrue) {
            ImageButton image1 = findViewById(id1);
            image1.setImageDrawable(null);
            image1.setClickable(true);
            ImageButton image2 = findViewById(id2);
            image2.setImageDrawable(null);
        }
    }

    public void generateRandomNumberList() {
        for (int i = 0; i <= 7; ++i) number.add(i);
        Collections.shuffle(number);
    }

    private void setPath(int i) {
        path = getIntent().getStringArrayListExtra("listOfPaths").get(i);
    }

    private void setViewID(int i) {
        viewID = getResources().getIdentifier("picture" + i, "id", getPackageName());
    }

    private String getPath() {
        return path;
    }

    private int getViewID() {
        return viewID;
    }

    public void setTag1(String tag) {
        tag1 = tag;
    }

    public void setTag2(String tag) {
        tag2 = tag;
    }

    public void setId1(int id) {
        id1 = id;
    }

    public void setId2(int id) {
        id2 = id;
    }


}


