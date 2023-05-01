package com.Hileb.ofstream.ofstream_advancement.command;

import com.Hileb.ofstream.OfstreamAdvancement;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.Collection;


public class CommandReloadAdvancement  {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal(getName()).requires((commandSource) -> {
            return commandSource.hasPermissionLevel(getRequiredPermissionLevel());
        }).executes((commandContext) -> {
            return execute(commandContext.getSource());
        }));
    }
    public static String getName() {
        return "getAllAdvancement";
    }


    public static int execute(CommandSource source) {
        try{
            ServerPlayerEntity player=source.asPlayer();

            Collection<Advancement> advancements=(Collection<Advancement>)player.getServerWorld().getServer().getAdvancementManager().getAllAdvancements();
            for (Advancement advancement:advancements){
                giveAdvancement(player,advancement);
            }
            return advancements.size();
        }catch (CommandSyntaxException e) {
            OfstreamAdvancement.LOGGER.error(e);
            source.sendFeedback(new StringTextComponent("this can only send by player who have the permissionLevel above 2!"),true);
        }
        return 0;
    }


    public static int getRequiredPermissionLevel() {
        return 2;
    }

    public static boolean giveAdvancement(ServerPlayerEntity playerMP, Advancement advancement)
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
