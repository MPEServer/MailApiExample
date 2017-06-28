package test;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import tech.teslex.mail.Mail;

import javax.mail.MessagingException;

/**
 * ------------------------------
 * Created by expexes on 28.06.2017.
 * Site: teslex.tech
 * -------------------------------
 */
public class Main extends PluginBase {

    Mail mail;

    @Override
    public void onEnable() {
        this.getLogger().info("MailApiExample enabled!");
        mail = new Mail("you@gmail.com", "yourpassword");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getLabel().equals("mail")) {

            if (args.length < 4) {
                sender.sendMessage(command.getUsage());
            } else {
                String text = "";
                for (int i = 3; i < args.length; i++) {
                    text += args[i] + " ";
                }
                sender.sendMessage("Sending...");
                String finalText = text;
                new Thread(() -> {
                    try {
                        mail.send(args[0], args[1], args[2], finalText, "text/plain");
                        sender.sendMessage("Ok");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        }
        return true;
    }
}
