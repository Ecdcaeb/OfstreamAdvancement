package com.Hileb.ofstream_advancement.AdvancementData;

public class OfstreamDataAdvancement {
    public final String chineseName;
    public final String englishName;

    public final String chineseDesc;
    public final String englishDesc;
    public final String registerName;
    public final String parent;

    public final String smallIcon;
    public final String largeIcon;
    public final String rewards;
    public OfstreamDataAdvancement(
            String chineseNameIn,
    String englishNameIn,

     String chineseDescIn,
     String englishDescIn,
    String registerNameIn,
    String parentIn,

   String smallIconIn,
   String largeIconIn,
            String rewardsIn

    ){
        chineseName=chineseNameIn;
        englishName=englishNameIn;

        chineseDesc=chineseDescIn;
        englishDesc=englishDescIn;
        registerName= registerNameIn;
        parent=parentIn;

        smallIcon=smallIconIn;
        largeIcon=largeIconIn;
        rewards=rewardsIn;
    }
}
