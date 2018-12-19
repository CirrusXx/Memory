package adam.uni.math.lodz.pl.memory;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     *
     */
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int counterOfAddedPictures = 1;
    private String pathOfTheFirstImage;
    private String pathOfTheSecondImage;
    private String pathOfTheThirdImage;
    private String pathOfTheFourthImage;
    String idOfTheSelectedPicture;
    private List<String> pathList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPlayButtonOff();
    }

    public void takePhotoOnClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        idOfTheSelectedPicture = getIdOfTheSelectedPicture(view);
        counterOfAddedPictures = Character.getNumericValue(idOfTheSelectedPicture.charAt(lengthOfId(idOfTheSelectedPicture)));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            int viewID = getResources().getIdentifier("image" + counterOfAddedPictures, "id", getPackageName());
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton image = findViewById(viewID);
            image.setImageBitmap(imageBitmap);
            setPaths(imageBitmap);

            if (counterOfAddedPictures == 4) {
                setPlayButtonOn();
            }
        }
    }

    public void startGameActivityOnClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        addPathsToTheList();
        intent.putStringArrayListExtra("listOfPaths", (ArrayList<String>) pathList);
        startActivity(intent);
    }

    private static String getIdOfTheSelectedPicture(View view) {
        return view.getResources().getResourceName(view.getId());
    }

    private int lengthOfId(String id) {
        return id.length() - 1;
    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directoryOfThePicture = cw.getDir("imageDir" + counterOfAddedPictures, Context.MODE_PRIVATE);
        File pathOfThePicture = new File(directoryOfThePicture, "profile.jpg");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(pathOfThePicture);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileOutputStream != null;
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directoryOfThePicture.getAbsolutePath();
    }

    private void setPlayButtonOn() {
        Button play = findViewById(R.id.playButton);
        play.setEnabled(true);
    }

    private void setPlayButtonOff() {
        Button play = findViewById(R.id.playButton);
        play.setEnabled(false);
    }

    private void addPathsToTheList() {
        pathList.add(0, getPathOfTheFirstImage());
        pathList.add(1, getPathOfTheFirstImage());
        pathList.add(2, getPathOfTheSecondImage());
        pathList.add(3, getPathOfTheSecondImage());
        pathList.add(4, getPathOfTheThirdImage());
        pathList.add(5, getPathOfTheThirdImage());
        pathList.add(6, getPathOfTheFourthImage());
        pathList.add(7, getPathOfTheFourthImage());
    }

    private void setPaths(Bitmap imageBitmap) {
        setPathOfTheFirstImage(saveToInternalStorage(imageBitmap));
        setPathOfTheSecondImage(saveToInternalStorage(imageBitmap));
        setPathOfTheFourthImage(saveToInternalStorage(imageBitmap));
        setPathOfTheThirdImage(saveToInternalStorage(imageBitmap));
    }

    private void setPathOfTheFirstImage(String path) {
        if (counterOfAddedPictures == 1) {
            pathOfTheFirstImage = path;

        }
    }

    private void setPathOfTheSecondImage(String path) {
        if (counterOfAddedPictures == 2) {
            pathOfTheSecondImage = path;
        }
    }

    private void setPathOfTheThirdImage(String path) {
        if (counterOfAddedPictures == 3) {
            pathOfTheThirdImage = path;
        }
    }

    private void setPathOfTheFourthImage(String path) {
        if (counterOfAddedPictures == 4) {
            pathOfTheFourthImage = path;
        }
    }

    private String getPathOfTheFirstImage() {
        return pathOfTheFirstImage;
    }

    private String getPathOfTheSecondImage() {
        return pathOfTheSecondImage;
    }

    private String getPathOfTheThirdImage() {
        return pathOfTheThirdImage;
    }

    private String getPathOfTheFourthImage() {
        return pathOfTheFourthImage;
    }
}