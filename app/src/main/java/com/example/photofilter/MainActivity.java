package com.example.photofilter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView buckysImageView;
    Drawable buckysFace;
    Bitmap bitmapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buckysImageView=(ImageView)findViewById(R.id.buckysImageView);//Reference to imageview

       buckysFace=getResources().getDrawable(R.drawable.bucky);   //Reference to jpg image
       bitmapImage=((BitmapDrawable) buckysFace).getBitmap();    //convert drawable into bitmap
        Bitmap newPhoto=invertImage(bitmapImage); //Add some effects to photo
       // buckysImageView.setImageBitmap(newPhoto);   //Set the inverted image to imageview

        //Layering of photos
        Drawable[]layers=new Drawable[2];
        layers[0]=getResources().getDrawable(R.drawable.bucky);
        layers[1]=getResources().getDrawable(R.drawable.dirty);
        LayerDrawable layerDrawable=new LayerDrawable(layers);  //convert the photos in layers
        buckysImageView.setImageDrawable(layerDrawable);//Set layers to ImageView
        //Save the file to user device.
       MediaStore.Images.Media.insertImage(getContentResolver(),newPhoto,"title","description");
    }

    //Invert an image
    public static Bitmap invertImage(Bitmap original){
        Bitmap finalImage=Bitmap.createBitmap(original.getWidth(),original.getHeight(),original.getConfig());//finalImage is of same size as original
        int A,R,G,B;
        int pixelColor;
        // Storing pixel
        int height=original.getHeight();
        int width=original.getWidth();

        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                pixelColor=original.getPixel(x,y);//get the coordinates
                //Set the new values for color
                A= Color.alpha(pixelColor);
                R=255-Color.red(pixelColor);
                G= 255-Color.green(pixelColor);
                B=255-Color.blue(pixelColor);
                finalImage.setPixel(x,y,Color.argb(A,R,G,B));//convert the image to a new one
            }
        }
        return finalImage;
    }
}
