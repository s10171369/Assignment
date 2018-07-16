package sg.edu.np.s10171369.assignment;

public class HeroDataModel {
    private int HeroImage;
    private String HeroName;
    private String HeroTitle;
    private String HeroStory;
    private int HeroUW;
    private int UT3Image;

    private Skill Skill1;
    private Skill Skill2;
    private Skill Skill3;
    private Skill Skill4;

    public HeroDataModel(int heroImage, String heroName, String heroTitle, String heroStory, int heroUW, int ut3Image, Skill s1, Skill s2, Skill s3, Skill s4)
    {
        HeroImage = heroImage;
        HeroName = heroName;
        HeroTitle = heroTitle;
        HeroStory = heroStory;
        HeroUW = heroUW;
        UT3Image = ut3Image;
        Skill1 = s1;
        Skill2 = s2;
        Skill3 = s3;
        Skill4 = s4;
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

    public Skill getSkill1() {
        return Skill1;
    }

    public Skill getSkill2() {
        return Skill2;
    }

    public Skill getSkill3() {
        return Skill3;
    }

    public Skill getSkill4() {
        return Skill4;
    }

}
class Skill {
    private int SkillImage;
    private String SkillName;
    private String SkillDescription;
    private String SkillLight;
    private String SkillDark;

    public Skill(int skillImage, String skillName, String skillDescription, String skillLight, String skillDark) {
        SkillImage = skillImage;
        SkillName = skillName;
        SkillDescription = skillDescription;
        SkillLight = skillLight;
        SkillDark = skillDark;
    }

    public int getSkillImage(){
        return SkillImage;
    }

    public String getSkillName(){
        return SkillName;
    }

    public String getSkillDescription(){
        return SkillDescription;
    }

    public String getSkillLight(){
        return SkillLight;
    }

    public String getSkillDark(){
        return SkillDark;
    }

}