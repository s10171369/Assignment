package sg.edu.np.s10171369.assignment;

import java.util.ArrayList;
import java.util.List;

public class HeroDataModel {
    private int HeroImage;
    private String HeroName;
    private String HeroTitle;
    private String HeroStory;
    private int HeroUW;
    private int UT3Image;
    private List<Skill> SkillList;

    /*private Skill Skill1;
    private Skill Skill2;
    private Skill Skill3;
    private Skill Skill4;*/

    public HeroDataModel(int heroImage, String heroName, String heroTitle, String heroStory, int heroUW, int ut3Image, List<Skill> skillList)
    {
        HeroImage = heroImage;
        HeroName = heroName;
        HeroTitle = heroTitle;
        HeroStory = heroStory;
        HeroUW = heroUW;
        UT3Image = ut3Image;
        /*Skill1 = s1;
        Skill2 = s2;
        Skill3 = s3;
        Skill4 = s4;*/
        /*SkillList.add(s1);
        SkillList.add(s2);
        SkillList.add(s3);
        SkillList.add(s4);*/
        SkillList = skillList;
    }

    public int getHeroImage(){
        return HeroImage;
    }

    public String getHeroName(){
        return HeroName;
    }

    public String getHeroTitle() {
        return HeroTitle;
    }

    public String getHeroStory(){
        return HeroStory;
    }

    public int getHeroUW() {
        return HeroUW;
    }

    public int getUT3Image() {
        return UT3Image;
    }

    public List<Skill> getSkillList(){
        return SkillList;
    }

}
