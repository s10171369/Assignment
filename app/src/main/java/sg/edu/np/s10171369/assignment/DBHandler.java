package sg.edu.np.s10171369.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static String DB_NAME = "DBMAD_Assignment.db";
    private static String DB_PATH = "";
    //C:UsersjoahAndroidStudioProjectsAssignmentappsrcmainres
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private Context mContext;
    private boolean mNeedUpdate = false;

    public static final String TABLE_HEROES = "Heroes";
    public static final int COL_HEROIMAGE = 0;
    public static final int COL_HERONAME = 1;
    public static final int COL_HEROTITLE = 2;
    public static final int COL_HEROSTORY = 3;
    public static final int COL_HEROUW = 4;
    public static final int COL_HEROUT2IMAGE = 5;
    public static final int COL_HEROUT3IMAGE = 6;

    public static final String TABLE_BOSSES = "Boss";
    public static final String DISTINCTCOL_BOSSNAME = "BossName";
    public static final int COL_RAIDNAME = 0;
    public static final int COL_BOSSNAME = 1;
    public static final int COL_BOSSIMAGE = 2;
    public static final int COL_BOSSDESCRIPTION = 3;
    public static final int COL_HARDMODE= 4;
    public static final int COL_HEROESLIST = 5;


    public static final String TABLE_SKILLS = "Skills";
    public static final int COL_SKILLIMAGE = 0;
    public static final int COL_SKILLNAME = 1;
    public static final int COL_SKILLDESCRIPTION = 2;
    public static final int COL_SKILLLIGHT = 3;
    public static final int COL_SKILLDARK = 4;
    public static final int COL_SKILLHERONAME = 5;
    public static final int COL_SKILLBOSSNAME = 6;
    public static final int COL_SKILLCOOLDOWN = 7;
    public static final int COL_HARD = 8;
    public static final String COLUMN_HERONAME = "HeroName";
    public static final String COLUMN_BOSSNAME = "BossName";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        //InputStream mInput = mContext.getAssets().open(DB_NAME);
        InputStream mInput = mContext.getResources().openRawResource(R.raw.dbmad);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    // Methods to find information
    public List<HeroDataModel> getAllHeroes() {
        List<HeroDataModel> heroList = new ArrayList<HeroDataModel>();

        String query = "SELECT * FROM " + TABLE_HEROES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                // Get Hero Details
                HeroDataModel hero = new HeroDataModel();

                byte[] heroImgByte = cursor.getBlob(COL_HEROIMAGE);
                Bitmap heroImg = BitmapFactory.decodeByteArray(heroImgByte, 0, heroImgByte.length);

                byte[] uwImgByte = cursor.getBlob(COL_HEROUW);
                Bitmap uwImg = BitmapFactory.decodeByteArray(uwImgByte, 0, uwImgByte.length);

                byte[] ut2ImgByte = cursor.getBlob(COL_HEROUT2IMAGE);
                Bitmap ut2Img = BitmapFactory.decodeByteArray(ut2ImgByte, 0, ut2ImgByte.length);

                byte[] ut3ImgByte = cursor.getBlob(COL_HEROUT3IMAGE);
                Bitmap ut3Img = BitmapFactory.decodeByteArray(ut3ImgByte, 0, ut3ImgByte.length);

                hero.setHeroImage(heroImg);
                hero.setHeroName(cursor.getString(COL_HERONAME));
                hero.setHeroTitle(cursor.getString(COL_HEROTITLE));
                hero.setHeroStory(cursor.getString(COL_HEROSTORY));
                hero.setHeroUW(uwImg);
                hero.setHeroUT2Image(ut2Img);
                hero.setHeroUT3Image(ut3Img);

                // Add hero to list
                heroList.add(hero);
            }
            while (cursor.moveToNext());
        }
        return heroList;
    }

    public List<Skill> getHeroSkills(String heroName) {
        List<Skill> skillList = new ArrayList<Skill>();

        String query = "SELECT * FROM " + TABLE_SKILLS
                + " WHERE " + COLUMN_HERONAME
                + " = \"" + heroName + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                // Get Hero Details
                Skill skill = new Skill();

                byte[] skillImgByte = cursor.getBlob(COL_SKILLIMAGE);
                Bitmap skillImg = BitmapFactory.decodeByteArray(skillImgByte, 0, skillImgByte.length);

                skill.setSkillImage(skillImg);
                skill.setSkillName(cursor.getString(COL_SKILLNAME));
                skill.setSkillDescription(cursor.getString(COL_SKILLDESCRIPTION));
                skill.setSkillLight(cursor.getString(COL_SKILLLIGHT));
                skill.setSkillDark(cursor.getString(COL_SKILLDARK));

                // Add hero to list
                skillList.add(skill);
            }
            while (cursor.moveToNext());
        }
        return skillList;
    }

    public List<RaidBossDataModel> getDistinctRaidBosses() {
        List<RaidBossDataModel> raidBossList = new ArrayList<RaidBossDataModel>();

        String query = "SELECT DISTINCT " + DISTINCTCOL_BOSSNAME + " FROM " + TABLE_BOSSES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                RaidBossDataModel boss = new RaidBossDataModel();

                byte[] bossImgByte = cursor.getBlob(COL_BOSSIMAGE);
                Bitmap bossImg = BitmapFactory.decodeByteArray(bossImgByte, 0, bossImgByte.length);

                boss.setBossImage(bossImg);
                boss.setRaidName(cursor.getString(COL_RAIDNAME));
                boss.setBossName(cursor.getString(COL_BOSSNAME));
                boss.setBossDescription(cursor.getString(COL_BOSSDESCRIPTION));
                boss.setHardDifficulty(cursor.getInt(COL_HARDMODE));

                raidBossList.add(boss);
            }
            while (cursor.moveToNext());
        }
        return raidBossList;
    }

    public List<RaidBossDataModel> getAllRaidBosses() {
        List<RaidBossDataModel> raidBossList = new ArrayList<RaidBossDataModel>();

        String query = "SELECT * FROM " + TABLE_BOSSES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                RaidBossDataModel boss = new RaidBossDataModel();

                byte[] bossImgByte = cursor.getBlob(COL_BOSSIMAGE);
                Bitmap bossImg = BitmapFactory.decodeByteArray(bossImgByte, 0, bossImgByte.length);

                boss.setBossImage(bossImg);
                boss.setRaidName(cursor.getString(COL_RAIDNAME));
                boss.setBossName(cursor.getString(COL_BOSSNAME));
                boss.setBossDescription(cursor.getString(COL_BOSSDESCRIPTION));
                boss.setHardDifficulty(cursor.getInt(COL_HARDMODE));
                boss.setRecommendedHeroes(cursor.getString(COL_HEROESLIST));

                raidBossList.add(boss);
            }
            while (cursor.moveToNext());
        }
        return raidBossList;
    }

    public RaidBossDataModel findHardBoss(String bossName){

        RaidBossDataModel hardBoss = new RaidBossDataModel();

        String query = "SELECT * FROM " + TABLE_BOSSES
                + " WHERE " + COL_BOSSNAME
                + " = \"" + bossName + "\""
                + " AND " + COL_HARDMODE + " = 1 ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
             byte[] bossImgByte = cursor.getBlob(COL_BOSSIMAGE);
             Bitmap bossImg = BitmapFactory.decodeByteArray(bossImgByte, 0, bossImgByte.length);

             hardBoss.setBossImage(bossImg);
             hardBoss.setRaidName(cursor.getString(COL_RAIDNAME));
             hardBoss.setBossName(cursor.getString(COL_BOSSNAME));
             hardBoss.setBossDescription(cursor.getString(COL_BOSSDESCRIPTION));
             hardBoss.setHardDifficulty(cursor.getInt(COL_HARDMODE));
             hardBoss.setRecommendedHeroes(cursor.getString(COL_HEROESLIST));
        }
        return hardBoss;
    }

    public List<Skill> getBossSkills(String bossName, int difficulty) {
        List<Skill> skillList = new ArrayList<Skill>();

        String query = "SELECT * FROM " + TABLE_SKILLS
                + " WHERE " + COLUMN_BOSSNAME
                + " = \"" + bossName + "\""
                + " AND " + COL_HARD + " = " + difficulty;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                // Get Hero Details
                Skill skill = new Skill();

                byte[] skillImgByte = cursor.getBlob(COL_SKILLIMAGE);
                Bitmap skillImg = BitmapFactory.decodeByteArray(skillImgByte, 0, skillImgByte.length);

                skill.setSkillImage(skillImg);
                skill.setSkillName(cursor.getString(COL_SKILLNAME));
                skill.setSkillDescription(cursor.getString(COL_SKILLDESCRIPTION));
                skill.setSkillCooldown(cursor.getInt(COL_SKILLCOOLDOWN));

                // Add hero to list
                skillList.add(skill);
            }
            while (cursor.moveToNext());
        }
        return skillList;
    }

    public List<HeroDataModel> getRecommendedHeroes(String heroString) {

        List<HeroDataModel> recommendedHeroes = new ArrayList<HeroDataModel>();

        String tempString = heroString;
        int findIndex;
        while (tempString != "") {
            findIndex = tempString.indexOf(",");
            String heroName = tempString.substring(findIndex);

            String query = "SELECT * FROM " + TABLE_HEROES
                    + " WHERE " + COL_HERONAME
                    + " = \"" + heroString;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                // Get Hero Details
                HeroDataModel hero = new HeroDataModel();

                byte[] heroImgByte = cursor.getBlob(COL_HEROIMAGE);
                Bitmap heroImg = BitmapFactory.decodeByteArray(heroImgByte, 0, heroImgByte.length);

                hero.setHeroImage(heroImg);
                hero.setHeroName(cursor.getString(COL_HERONAME));

                // Add hero to list
                recommendedHeroes.add(hero);
                tempString.replaceFirst(heroString, "");
            }
        }
        return recommendedHeroes;
    }
}
