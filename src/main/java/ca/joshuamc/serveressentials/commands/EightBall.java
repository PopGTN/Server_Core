package ca.joshuamc.serveressentials.commands;

import ca.joshuamc.serveressentials.DefaultConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class EightBall implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean isplayer = sender instanceof Player;

        if (isplayer) {
            Player player = (Player) sender;
            if (player.hasPermission("se.8ball")) {
                int randomAnswer = getRandom();

                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < args.length; i++) {

                    builder.append(args[i]);
                    builder.append(" ");
                }
                String finalMessage = builder.toString().stripTrailing();


                String answer = null;
                switch (randomAnswer) {
                    case 1:
                        answer = "Yes you should.";

                        break;
                    case 2:
                        answer = "No you shouldn't.";

                        break;
                    case 3:
                        answer = "No you should.";

                        break;
                    case 4:
                        answer = "Maybe you should.";

                        break;
                    case 5:
                        answer = "Yes!";

                        break;
                    case 6:
                        answer = "I don't know";

                        break;
                    case 7:
                        answer = "I didn't hear you";

                        break;
                    case 8:
                        answer = "Maybe...";

                        break;
                    case 9:
                        answer = "Figuring it out on your own!";

                        break;
                    case 10:
                        answer = "Ask again later!";
                        break;
                }
                if (DefaultConfig.getEightBallQuestion() == false) {
                    sender.sendMessage("<8 ball> Your answer for " + finalMessage);
                    sender.sendMessage("<8 ball> " + answer);
                } else {
                    Bukkit.broadcastMessage("<8 ball>"+ player.getDisplayName() +"answer for " + finalMessage);
                    Bukkit.broadcastMessage("<8 ball> " + answer);
                }
                return true;
            }
        }else {
            sender.sendMessage("A Error occurred only");
        }
        sender.sendMessage("Something Went Wrong");
        return true;
    }
    public int getRandom(int max, int min) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
    private int getRandom() {
        return (int) Math.floor(Math.random() * (10 - 1 + 1) + 1);
    }
}
