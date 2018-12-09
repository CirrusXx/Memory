package adam.uni.math.lodz.pl.memory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Parcel;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    int counter = 1;
    ImageView imageView1,imageView2,imageView3,imageView4;
    DataBaseManager dataBase = new DataBaseManager(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disableReplaceButtons();
        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        imageView3 = findViewById(R.id.image3);
        imageView4 = findViewById(R.id.image4);

    }

    public void takePhotoOnClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            int viewID = getResources().getIdentifier("image"+counter,"id",getPackageName());
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView image = findViewById(viewID);
            image.setImageBitmap(imageBitmap);

            if(counter == 4)
            {
                enableReplaceAndGameActivityButtons();
            }
            counter++;
        }
    }

    public void replacePictureOnClick(View view)
    {
        TextView button = (TextView)view;
        String pictureNumber = button.getText().toString();
        counter = Integer.parseInt(pictureNumber);
        takePhotoOnClick(view);
    }

    public void disableReplaceButtons()
    {
        Button btn1 = findViewById(R.id.replacePicture1);
        btn1.setEnabled(false);
        Button btn2 = findViewById(R.id.replacePicture2);
        btn2.setEnabled(false);
        Button btn3 = findViewById(R.id.replacePicture3);
        btn3.setEnabled(false);
        Button btn4 = findViewById(R.id.replacePicture4);
        btn4.setEnabled(false);
        Button btn5 = findViewById(R.id.playButton);
        btn5.setEnabled(false);
    }

    public void enableReplaceAndGameActivityButtons()
    {
        Button btn1 = findViewById(R.id.replacePicture1);
        btn1.setEnabled(true);
        Button btn2 = findViewById(R.id.replacePicture2);
        btn2.setEnabled(true);
        Button btn3 = findViewById(R.id.replacePicture3);
        btn3.setEnabled(true);
        Button btn4 = findViewById(R.id.replacePicture4);
        btn4.setEnabled(true);
        Button btn5 = findViewById(R.id.playButton);
        btn5.setEnabled(true);
        Button btn6 = findViewById(R.id.takePhotoButton);
        btn6.setEnabled(false);
    }
    public void startGameActivity(View view)
    {
        BitmapDrawable drawable1 = (BitmapDrawable)imageView1.getDrawable();
        Bitmap picture1 = drawable1.getBitmap();
        BitmapDrawable drawable2 = (BitmapDrawable)imageView2.getDrawable();
        Bitmap picture2 = drawable2.getBitmap();
        BitmapDrawable drawable3 = (BitmapDrawable)imageView3.getDrawable();
        Bitmap picture3 = drawable3.getBitmap();
        BitmapDrawable drawable4 = (BitmapDrawable)imageView4.getDrawable();
        Bitmap picture4 = drawable4.getBitmap();


        Intent intent = new Intent(this,Game.class);
    //    intent.putExtra("images1",bundle);
        startActivity(intent);
    }



}