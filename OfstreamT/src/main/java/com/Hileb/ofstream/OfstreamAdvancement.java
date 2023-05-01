package com.Hileb.ofstream;

import com.Hileb.ofstream.ofstream_advancement.AdvancementData.AdvancementDataManager;
import com.Hileb.ofstream.ofstream_advancement.command.CommandReloadAdvancement;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



// The value here should match an entry in the META-INF/mods.toml file


@Mod(OfstreamAdvancement.MODID)
public class OfstreamAdvancement {
    public static final String MODID="ofstream_advancement";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public OfstreamAdvancement() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public  void onServerStart(FMLServerAboutToStartEvent event){
        CommandDispatcher<CommandSource>  dispatcher=event.getServer().getCommandManager().getDispatcher();
        CommandReloadAdvancement.register(dispatcher);
    }


}
