package com.Hileb.ofstream_advancement.command;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import java.util.Collection;


public class CommandReloadAdvancement extends CommandBase {
    @Override
    public String getName() {
        return "getAllAdvancement";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "null";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayerMP){
            EntityPlayerMP player =(EntityPlayerMP)sender;

            Collection<Advancement> advancements=(Collection<Advancement>)player.getServerWorld().getAdvancementManager().getAdvancements();
            for (Advancement advancement:advancements){
                giveAdvancement(player,advancement);
            }
        }
        else throw new CommandException("this can only send by player who have the permissionLevel above 2!");
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    public static boolean giveAdvancement(EntityPlayerMP playerMP, Advancement advancement)
    {
        if (playerMP==null || advancement==null){
            return false;
        }
        AdvancementProgress advancementprogress = playerMP.getAdvancements().getProgress(advancement);

        if (advancementprogress.isDone())
        {
            return false;
        }
        else
        {
            for (String s : advancementprogress.getRemaningCriteria())
            {
                playerMP.getAdvancements().grantCriterion(advancement, s);
            }

            return true;
        }
    }

}
