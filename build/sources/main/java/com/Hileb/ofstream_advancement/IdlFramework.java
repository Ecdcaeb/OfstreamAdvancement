package com.Hileb.ofstream_advancement;

import com.Hileb.ofstream_advancement.command.CommandReloadAdvancement;
import com.Hileb.ofstream_advancement.proxy.ProxyBase;
import com.Hileb.ofstream_advancement.proxy.ClientProxy;
import com.Hileb.ofstream_advancement.proxy.ServerProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = IdlFramework.MODID, name = IdlFramework.NAME, version = IdlFramework.VERSION,dependencies = "required-after:ofstream_t")
public class IdlFramework {
    public static final String MODID = "ofstream_advancement";
    public static final String NAME = "Ofstream Advancement";
    public static final String VERSION = "1.0.0.0";

    public static Logger logger;

    public static final boolean SHOW_WARN = true;

    @Mod.Instance
    public static IdlFramework instance;

    @SidedProxy(clientSide = ClientProxy.PROXY, serverSide = ServerProxy.PROXY)
    public static ProxyBase proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();



    }
    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandReloadAdvancement());
    }



    public static void LogWarning(String str, Object... args) {
        if (SHOW_WARN) {
            logger.warn(String.format(str, args));
        }
    }

    public static void LogWarning(String str) {
        if (SHOW_WARN) {
            logger.warn(str);
        }
    }

    public static void Log(String str) {
        logger.info(str);
    }

    public static void Log(String str, Object... args) {
        logger.info(String.format(str, args));
    }
}