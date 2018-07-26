package sg.edu.np.s10171369.assignment;

import android.graphics.Bitmap;

public class MainPageDataModel {
    private int Image;
    private Bitmap bitmapImage;
    private String Header;

    //public MainPageDataModel(int image, String header) {
    //    Image = image;
    //    Header = header;
    //}

    public MainPageDataModel(Bitmap image, String header){
        bitmapImage = image;
        Header = header;
    }

    public String getHeader() {
        return Header;
    }

    //public int getImage(){
    //    return Image;
    //}

    public Bitmap getImage() { return bitmapImage; }
}
