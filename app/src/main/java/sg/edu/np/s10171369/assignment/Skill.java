package sg.edu.np.s10171369.assignment;

import android.graphics.Bitmap;

public class Skill {
    private Bitmap SkillImage;
    private String SkillName;
    private String SkillDescription;
    private int SkillCooldown;
    private String SkillLight;
    private String SkillDark;
    private String HeroName;
    private String BossName;

    public Skill() { }

    public Skill(Bitmap skillImage, String skillName, String skillDescription, String skillLight, String skillDark) {
        SkillImage = skillImage;
        SkillName = skillName;
        SkillDescription = skillDescription;
        SkillCooldown = -1;
        SkillLight = skillLight;
        SkillDark = skillDark;
        HeroName = null;
        BossName = null;
    }

    public Skill(Bitmap skillImage, String skillName, String skillDescription, int cooldown) {
        SkillImage = skillImage;
        SkillName = skillName;
        SkillDescription = skillDescription;
        SkillCooldown = cooldown;
        SkillLight = null;
        SkillDark = null;
        HeroName = null;
        BossName = null;
    }

    public Skill(Bitmap skillImage, String skillName, String skillDescription, String skillLight, String skillDark, String heroName) {
        SkillImage = skillImage;
        SkillName = skillName;
        SkillDescription = skillDescription;
        SkillCooldown = -1;
        SkillLight = skillLight;
        SkillDark = skillDark;
        HeroName = heroName;
        BossName = null;
    }

    public Skill(Bitmap skillImage, String skillName, String skillDescription, int cooldown, String raidBoss) {
        SkillImage = skillImage;
        SkillName = skillName;
        SkillDescription = skillDescription;
        SkillCooldown = cooldown;
        SkillLight = null;
        SkillDark = null;
        HeroName = null;
        BossName = raidBoss;
    }

    public Bitmap getSkillImage(){
        return SkillImage;
    }

    public void setSkillImage(Bitmap skillImage) { SkillImage = skillImage; }

    public String getSkillName(){
        return SkillName;
    }

    public void setSkillName(String skillName) { SkillName = skillName; }

    public String getSkillDescription(){
        return SkillDescription;
    }

    public void setSkillDescription(String skillDescription) { SkillDescription = skillDescription; }

    public int getSkillCooldown(){
        return SkillCooldown;
    }

    public void setSkillCooldown(int cooldown) { SkillCooldown = cooldown; }

    public String getSkillLight(){
        return SkillLight;
    }

    public void setSkillLight(String skillLight) { SkillLight = skillLight; }

    public String getSkillDark(){
        return SkillDark;
    }

    public void setSkillDark(String skillDark) { SkillDark = skillDark; }

}
