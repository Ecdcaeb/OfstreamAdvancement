
package com.Hileb.ofstream.ofstream_advancement.AdvancementData;

import com.Hileb.ofstream.ofstream.IDataOfstream;
import com.Hileb.ofstream.ofstream.irr.ExportUtils;
import net.minecraft.advancements.Advancement;
import net.minecraft.util.ResourceLocation;


public class DataAdvancement implements IDataOfstream {
    public transient final Advancement advancement;

    public String chineseName;
    public String englishName;

    public String chineseDesc;
    public String englishDesc;
    public transient final ResourceLocation registerNameResourceLocation;
    public final String registerName;
    public final String parent;

    public final String smallIcon;
    public final String largeIcon;
    public DataAdvancement(Advancement advancementIn){
        advancement=advancementIn;
        //虽然idea告诉我这里会null，但是mc在display时也没有判断null，这意味着这实际上不会是null
        smallIcon = ExportUtils.INSTANCE.getSmallIcon(advancementIn.getDisplay().getIcon());
        largeIcon = ExportUtils.INSTANCE.getLargeIcon(advancementIn.getDisplay().getIcon());
        registerNameResourceLocation=advancementIn.getId();
        if (advancementIn.getParent()!=null){
            parent=advancementIn.getParent().getId().toString();
        }
        else parent="";

        registerName=registerNameResourceLocation.toString();

    }

    @Override
    public Object returnObj() {
        return new OfstreamDataAdvancement(
                chineseName,
                englishName,
                chineseDesc,
                englishDesc,
                registerName,
                parent,
                smallIcon,
                largeIcon,
                advancement.getRewards().toString()
        );
    }

    public void setChineseName(String chineseNameIn) {
        if (this.chineseName==null)this.chineseName = chineseNameIn;
    }
    public void setEnglishName(String englishNameIn) {
        if (this.englishName==null )this.englishName = englishNameIn;
    }

    public void setChineseDesc(String chineseDescIn) {
        if (this.chineseDesc==null)this.chineseDesc = chineseDescIn;
    }
    public void setEnglishDesc(String englishDescIn) {
        if (this.englishDesc==null )this.englishDesc = englishDescIn;
    }
}
