package adam.uni.math.lodz.pl.memory;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game extends AppCompatActivity {

    Random r = new Random();
    private String path;
    private int viewID;
    ArrayList<Integer> number = new ArrayList<>();
    int counter = 0;
    String tag1;
    String tag2;
    int id1;
    int id2;
    Button btn1;
    Button btn2;
    boolean ifTrue=true;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        generateRandomNumberList();
        loadImageFromStorage();
    }

    public void loadImageFromStorage() {
        try {
            for (int i = 0; i <= 7; i++) {
                setPath(i);
                setViewID(number.get(i));
                File f = new File(getPath(), "profile.jpg");
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                ImageButton img = findViewById(getViewID());
                img.setTag((i / 2));
                img.setImageBitmap(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setPath(int i) {
        path = getIntent().getStringArrayListExtra("listOfPaths").get(i);
    }

    public void setViewID(int i) {
        viewID = getResources().getIdentifier("picture" + i, "id", getPackageName());
    }

    public String getPath() {
        return path;
    }

    public int getViewID() {
        return viewID;
    }

    public void generateRandomNumberList() {
        for (int i = 0; i <= 7; ++i) number.add(i);
        Collections.shuffle(number);
    }

    @SuppressLint("SetTextI18n")
    public void checkTagOnClick(View view) {


        if (counter == 0) {
            checkIfFalse();
            btn1 = (Button) view;
            int ButtonIdInt = Integer.valueOf((String) btn1.getText());
            setViewID(ButtonIdInt);
            ImageButton image = findViewById(viewID);
            btn1.setVisibility(View.INVISIBLE);
            setTag1(image.getTag().toString());
            setId1(image.getId());
            counter++;
        } else {
            btn2 = (Button) view;
            int ButtonIdInt = Integer.valueOf((String) btn2.getText());
            setViewID(ButtonIdInt);
            ImageButton image = findViewById(viewID);
            btn2.setVisibility(View.INVISIBLE);
            setTag2(image.getTag().toString());
            setId2(image.getId());
            counter = 0;
            if (tag1.equals(tag2)) {
                makeImagesInvisible();
                ifTrue=true;
            }
            else ifTrue=false;
        }
    }

    private void makeImagesInvisible() {
        ImageButton image1 = findViewById(id1);
        image1.setVisibility(View.GONE);
        ImageButton image2 = findViewById(id2);
        image2.setVisibility(View.GONE);
        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);

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
    public void checkIfFalse()
    {
        if(!ifTrue)
        {
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
        }
    }

}


