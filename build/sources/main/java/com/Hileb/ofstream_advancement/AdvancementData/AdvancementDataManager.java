package com.Hileb.ofstream_advancement.AdvancementData;

import com.Hileb.ofstream.ofstream.IDataOfstream;
import com.Hileb.ofstream_advancement.IdlFramework;
import com.Hileb.ofstream.ofstream.OfstreamEvent;
import com.Hileb.ofstream.ofstream.OfstreamRegisterObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = IdlFramework.MODID,value = Side.CLIENT)
public class AdvancementDataManager {
    public static  List<DataAdvancement> ADVANCEMENTS=new ArrayList<>();

    public static HashMap<String,List<IDataOfstream>> MODS=new HashMap<>();


    @SubscribeEvent
    public static void onEventRegister(OfstreamEvent.Register event){
        init();
        event.register(new OfstreamRegisterObject(MODS,"advancement"));
    }
    @SubscribeEvent
    public static void onEventEnUs(OfstreamEvent.EnUs event){
        for (DataAdvancement dataAdvancement: AdvancementDataManager.ADVANCEMENTS){
            dataAdvancement.setEnglishName(getText(dataAdvancement.advancement.getDisplay().getTitle(),event.getI18n()));
            dataAdvancement.setEnglishDesc(getText(dataAdvancement.advancement.getDisplay().getDescription(),event.getI18n()));
        }
    }
    @SubscribeEvent
    public static void onEventZhCn(OfstreamEvent.ZhCn event){
        for (DataAdvancement dataAdvancement: AdvancementDataManager.ADVANCEMENTS){
            dataAdvancement.setChineseName(getText(dataAdvancement.advancement.getDisplay().getTitle(),event.getI18n()));
            dataAdvancement.setChineseDesc(getText(dataAdvancement.advancement.getDisplay().getDescription(),event.getI18n()));
        }
    }
    public static String getText(ITextComponent component, com.Hileb.ofstream.ofstream.lang.I18n i18n){
        if (component instanceof TextComponentString){
            TextComponentString stringText=(TextComponentString) component;
            return stringText.getText();
        }
        else if (component instanceof TextComponentTranslation){
            TextComponentTranslation textComponents=(TextComponentTranslation) component;
            return i18n.format(textComponents.getKey());
        }
        else return null;
    }

    public static void init(){
        ADVANCEMENTS.clear();
        MODS.clear();


        AdvancementList advancementList=Minecraft.getMinecraft().player.connection.getAdvancementManager().getAdvancementList();

        Collection<Advancement>  collection= (Collection<Advancement>)advancementList.getAdvancements();
        for(Advancement advancement:collection){
            if (advancement.getDisplay() != null && !advancement.getDisplay().getIcon().isEmpty()){
                DataAdvancement dataAdvancement=new DataAdvancement(advancement);ADVANCEMENTS.add(dataAdvancement);
                if (!MODS.containsKey(dataAdvancement.registerNameResourceLocation.getResourceDomain())){
                    MODS.put(dataAdvancement.registerNameResourceLocation.getResourceDomain(),new ArrayList<>());
                }
                MODS.get(dataAdvancement.registerNameResourceLocation.getResourceDomain()).add(dataAdvancement);

            }
        }
    }
}
