package org.shirakawatyu;

import net.kronos.rkon.core.Rcon;
import net.kronos.rkon.core.ex.AuthenticationException;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import java.io.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

import static org.shirakawatyu.utils.FileUtil.readFile;
import static org.shirakawatyu.utils.FileUtil.readFileLines;

public final class Qc2mc extends JavaPlugin {
    public static final Qc2mc INSTANCE = new Qc2mc();
    private Qc2mc() {
        super(new JvmPluginDescriptionBuilder("org.shirakawatyu.qc2mc", "1.0-SNAPSHOT")
                .name("QQChat2MC")
                .author("ShirkawaTyu")
                .build());
    }
    @Override
    public void onEnable() {
        getLogger().info("QQChat2MC loaded!");
        File path = new File("." + File.separator + "config" + File.separator + "org.shirakawatyu.qc2mc");
        File ip = new File("." + File.separator + "config" + File.separator + "org.shirakawatyu.qc2mc" + File.separator + "ip");
        File port = new File("." + File.separator + "config" + File.separator + "org.shirakawatyu.qc2mc" + File.separator + "port");
        File password = new File("." + File.separator + "config" + File.separator + "org.shirakawatyu.qc2mc" + File.separator + "password");
        File gruopnumber = new File("." + File.separator + "config" + File.separator + "org.shirakawatyu.qc2mc" + File.separator + "groupnumber");
        try {
            if(!ip.exists()){
                path.mkdirs();
                ip.createNewFile();
                port.createNewFile();
                password.createNewFile();
                gruopnumber.createNewFile();
                getLogger().info("******************************************************************************");
                getLogger().info("配置文件将在下次接收到消息时生效");
                getLogger().info("请填写配置文件，配置文件位于./config/org.shirakawatyu.qc2mc/");
                getLogger().info("ip,port,password,groupnumber分别对应IP地址，RCON端口，RCON密码，需要转发的群号");
                getLogger().info("******************************************************************************");
            }
            else{
                Rcon rcon = new Rcon(readFile(ip), Integer.parseInt(readFile(port)), readFile(password).getBytes());
                getLogger().info(rcon.command("list"));
            }
        } catch (IOException | AuthenticationException e) {
            e.printStackTrace();
        }
        Listener listener = GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
            try{
                if(event.getGroup().getId() == Long.parseLong(readFile(gruopnumber))){
                    for (String ipTemp:readFileLines(ip)) {
                        Rcon rcon = new Rcon(ipTemp, Integer.parseInt(readFile(port)), readFile(password).getBytes());
                        String chat = "[" + event.getSenderName() + "] " + event.getMessage().contentToString();
                        getLogger().info(rcon.command("say " + chat));
                    }
                }
            } catch (IOException | AuthenticationException e) {
                e.printStackTrace();
            }
        });
    }

}