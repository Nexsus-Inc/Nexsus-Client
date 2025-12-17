package de.constt.nexsus_client.client.discordRPC;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.ActivityType;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.entities.StatusDisplayType;
import com.jagrosh.discordipc.entities.User;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import de.constt.nexsus_client.client.config.NexsusConfigData;
import de.constt.nexsus_client.client.states.AddressState;
import net.minecraft.client.MinecraftClient;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.Arrays;

public final class discordIPCCore {

    private static IPCClient client;
    private static final long CLIENT_ID = 1450138306141618227L;

    public static void start() throws NoDiscordClientException {
        client = new IPCClient(CLIENT_ID);

        MinecraftClient mc = MinecraftClient.getInstance();

        String clientVersion = NexsusConfigData.getConfigValue("clientVersion");


        var v = net.minecraft.SharedConstants.getGameVersion();
        String versionId = v.getId();

        assert mc.getServer() != null;

        JsonObject buttons = new JsonObject();
        buttons.add("button1", new JsonObject());
        buttons.getAsJsonObject("button1").addProperty("label", "GitHub");
        buttons.getAsJsonObject("button1").addProperty("url", "https://github.com/Nexsus-Inc/Nexsus-Client");

        client.setListener(new IPCListener() {
            @Override
            public void onReady(IPCClient client) {
                RichPresence.Builder builder = new RichPresence.Builder()
                        .setActivityType(ActivityType.Playing)
                        .setStatusDisplayType(StatusDisplayType.State)
                        .setName("Nexsus Client")
                        .setState("v"+clientVersion)
                        .setDetails(AddressState.getAddress()+" | "+versionId)
                        .setLargeImage("logo")
                        .setStartTimestamp(OffsetDateTime.now().toEpochSecond());

                client.sendRichPresence(builder.build());
            }

            @Override public void onPacketSent(IPCClient c, Packet p) {}
            @Override public void onPacketReceived(IPCClient c, Packet p) {}
            @Override public void onActivityJoin(IPCClient c, String s) {}
            @Override public void onActivitySpectate(IPCClient c, String s) {}
            @Override public void onActivityJoinRequest(IPCClient c, String s, User u) {}
            @Override public void onClose(IPCClient c, JsonObject j) {}
            @Override public void onDisconnect(IPCClient c, Throwable t) {}
        });

        client.connect();
    }

    public static void shutdown() {
        if (client != null) {
            client.close();
            client = null;
        }
    }
}
