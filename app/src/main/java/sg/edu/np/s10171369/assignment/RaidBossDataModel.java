package sg.edu.np.s10171369.assignment;

import android.graphics.Bitmap;

import java.util.List;

public class RaidBossDataModel {
    private String RaidName;
    private String BossName;
    private int HardDifficulty;
    private Bitmap BossImage;
    private String BossDescription;
    private List<Skill> SkillList;
    private String RecommendedHeroesList;

    public RaidBossDataModel() {}

    public RaidBossDataModel( Bitmap bossImage, String raidName, String bossName, String bossDesc, List<Skill> skillList, String recommendedHeroes)
    {
        RaidName = raidName;
        BossName = bossName;
        HardDifficulty = 0;
        BossImage = bossImage;
        BossDescription = bossDesc;
        SkillList = skillList;
        RecommendedHeroesList = recommendedHeroes;
    }

    public RaidBossDataModel(Bitmap bossImage, int difficulty, String raidName, String bossName, String bossDesc, List<Skill> skillList, String recommendedHeroes)
    {
        RaidName = raidName;
        BossName = bossName;
        HardDifficulty = difficulty;
        BossImage = bossImage;
        BossDescription = bossDesc;
        SkillList = skillList;
        RecommendedHeroesList = recommendedHeroes;
    }


    public String getRaidName(){
        return RaidName;
    }

    public void setRaidName(String raidName) { RaidName = raidName; }

    public String getBossName(){
        return BossName;
    }

    public void setBossName(String  bossName) { BossName = bossName; }

    public Bitmap getBossImage(){
        return BossImage;
    }

    public void setBossImage(Bitmap bossImage) { BossImage = bossImage; }

    public int getHardDifficulty() {
        return HardDifficulty;
    }

    public void setHardDifficulty(int hard) { HardDifficulty = hard; }

    public String getBossDescription(){
        return BossDescription;
    }

    public void setBossDescription(String bossDescription) { BossDescription = bossDescription; }

    public List<Skill> getSkillList(){
        return SkillList;
    }

    public String getRecommendedHeroes(){
        return RecommendedHeroesList;
    }

    public void setRecommendedHeroes(String recommendedHeroes) { RecommendedHeroesList = recommendedHeroes; }
}
