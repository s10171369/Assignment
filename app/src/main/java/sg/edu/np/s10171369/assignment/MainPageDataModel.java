package sg.edu.np.s10171369.assignment;

public class MainPageDataModel {
    private int Image;
    private String Header;

    public MainPageDataModel(int image, String header) {
        Image = image;
        Header = header;
    }

    public String getHeader() {
        return Header;
    }

    public int getImage(){
        return Image;
    }

}
