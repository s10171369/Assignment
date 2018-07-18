package sg.edu.np.s10171369.assignment;

public class Skill {
    private int SkillImage;
    private String SkillName;
    private String SkillDescription;
    private int SkillCooldown;
    private String SkillLight;
    private String SkillDark;

    public Skill(int skillImage, String skillName, String skillDescription, String skillLight, String skillDark) {
        SkillImage = skillImage;
        SkillName = skillName;
        SkillDescription = skillDescription;
        SkillCooldown = -1;
        SkillLight = skillLight;
        SkillDark = skillDark;
    }

    public Skill(int skillImage, String skillName, String skillDescription, int cooldown) {
        SkillImage = skillImage;
        SkillName = skillName;
        SkillDescription = skillDescription;
        SkillCooldown = cooldown;
        SkillLight = null;
        SkillDark = null;
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

    public int getSkillCooldown(){
        return SkillCooldown;
    }

    public String getSkillLight(){
        return SkillLight;
    }

    public String getSkillDark(){
        return SkillDark;
    }

}
