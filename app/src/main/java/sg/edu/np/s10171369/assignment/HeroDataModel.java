package sg.edu.np.s10171369.assignment;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class HeroDataModel {
    private Bitmap HeroImage;
    private String HeroName;
    private String HeroTitle;
    private String HeroStory;
    private Bitmap HeroUW;
    private Bitmap UT2Image;
    private Bitmap UT3Image;
    private List<Skill> SkillList;

    /*private Skill Skill1;
    private Skill Skill2;
    private Skill Skill3;
    private Skill Skill4;*/

    public HeroDataModel() {

    }

    public HeroDataModel(Bitmap heroImage, String heroName, String heroTitle, String heroStory, Bitmap heroUW, Bitmap ut2Image, Bitmap ut3Image, List<Skill> skillList)
    {
        HeroImage = heroImage;
        HeroName = heroName;
        HeroTitle = heroTitle;
        HeroStory = heroStory;
        HeroUW = heroUW;
        UT2Image = ut2Image;
        UT3Image = ut3Image;
        SkillList = skillList;
    }

    public Bitmap getHeroImage(){
        return HeroImage;
    }

    public void setHeroImage(Bitmap heroImage) { HeroImage = heroImage; }

    public String getHeroName(){
        return HeroName;
    }

    public void setHeroName(String heroName) { HeroName = heroName; }

    public String getHeroTitle() {
        return HeroTitle;
    }

    public void setHeroTitle(String title) { HeroTitle = title; }

    public String getHeroStory(){
        return HeroStory;
    }

    public void setHeroStory(String story) { HeroStory = story; }

    public Bitmap getHeroUW() {
        return HeroUW;
    }

    public void setHeroUW(Bitmap uwImage) { HeroUW = uwImage; }

    public Bitmap getUT2Image() { return UT2Image; }

    public void setHeroUT2Image(Bitmap ut2Image) { UT2Image = ut2Image; }

    public Bitmap getUT3Image() {
        return UT3Image;
    }

    public void setHeroUT3Image(Bitmap ut3Image) { UT3Image = ut3Image; }

    public List<Skill> getSkillList(){
        return SkillList;
    }

}
