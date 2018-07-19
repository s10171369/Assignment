package sg.edu.np.s10171369.assignment;

import java.util.List;

public class RaidBossDataModel {
    private String RaidName;
    private String BossName;
    private int HardDifficulty;
    private int BossImage;
    private String BossDescription;
    private List<Skill> SkillList;
    private List<HeroDataModel> RecommendedHeroesList;

    public RaidBossDataModel( int bossImage, String raidName, String bossName, String bossDesc, List<Skill> skillList, List<HeroDataModel> recommendedHeroes)
    {
        RaidName = raidName;
        BossName = bossName;
        HardDifficulty = 0;
        BossImage = bossImage;
        BossDescription = bossDesc;
        SkillList = skillList;
        RecommendedHeroesList = recommendedHeroes;
    }

    public RaidBossDataModel(int bossImage, int difficulty, String raidName, String bossName, String bossDesc, List<Skill> skillList, List<HeroDataModel> recommendedHeroes)
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

    public String getBossName(){
        return BossName;
    }

    public int getBossImage(){
        return BossImage;
    }

    public int getHardDifficulty() {
        return HardDifficulty;
    }

    public String getBossDescription(){
        return BossDescription;
    }

    public List<Skill> getSkillList(){
        return SkillList;
    }

    public List<HeroDataModel> getRecommendedHeroes(){
        return RecommendedHeroesList;
    }
}
