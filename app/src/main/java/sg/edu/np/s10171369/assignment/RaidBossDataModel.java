package sg.edu.np.s10171369.assignment;

import java.util.List;

public class RaidBossDataModel {
    private String RaidName;
    private String BossName;
    private int BossImage;
    private String BossDescription;
    private List<Skill> SkillList;
    private List<HeroDataModel> RecommendedHeroesList;

    public RaidBossDataModel(String raidName, String bossName, int bossImage, String bossDesc, List<Skill> skillList, List<HeroDataModel> recommendedHeroes)
    {
        RaidName = raidName;
        BossName = bossName;
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
