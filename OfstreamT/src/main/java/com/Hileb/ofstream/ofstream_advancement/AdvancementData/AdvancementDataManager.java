package com.Hileb.ofstream.ofstream_advancement.AdvancementData;

import com.Hileb.ofstream.OfstreamAdvancement;
import com.Hileb.ofstream.ofstream.DataManager;
import com.Hileb.ofstream.ofstream.OfstreamEvent;
import com.Hileb.ofstream.ofstream.lang.I18n;
import com.Hileb.ofstream.ofstream.lang.LangHelper;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = OfstreamAdvancement.MODID,value = Dist.CLIENT)
public class AdvancementDataManager {

    public static final DataManager ADVANCEMENT=new DataManager("advancement");
    public static void init(){
        ADVANCEMENT.clear();


        try {
            Collection<Advancement> collection = Minecraft.getInstance().player.connection.getAdvancementManager().getAdvancementList().getAll();
            for (Advancement advancement : collection) {
                if (advancement.getDisplay() != null && !advancement.getDisplay().getIcon().isEmpty()) {
                    DataAdvancement dataAdvancement = new DataAdvancement(advancement);
                    I18n zh_cn=LangHelper.getI18n(LangHelper.ZH_CN);
                    I18n en_us=LangHelper.getI18n(LangHelper.ZH_CN);
                    dataAdvancement.setChineseName(zh_cn.format(String.valueOf(dataAdvancement.advancement.getDisplay().getTitle())));
                    dataAdvancement.setChineseDesc(zh_cn.format(String.valueOf(dataAdvancement.advancement.getDisplay().getDescription())));
                    dataAdvancement.setEnglishName(en_us.format(String.valueOf(dataAdvancement.advancement.getDisplay().getTitle())));
                    dataAdvancement.setEnglishDesc(en_us.format(String.valueOf(dataAdvancement.advancement.getDisplay().getDescription())));
                    ADVANCEMENT.register(dataAdvancement.registerNameResourceLocation.getNamespace(),dataAdvancement);
                }
            }
        }catch (NullPointerException e){
            OfstreamAdvancement.LOGGER.error("has no advancement.Maybe you should enter a world,as a playerEntity!");
            OfstreamAdvancement.LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }


    @SubscribeEvent
    public static void onRegister(OfstreamEvent.Register event){
        init();

        event.register(ADVANCEMENT.getRegisterObject());
    }

}
