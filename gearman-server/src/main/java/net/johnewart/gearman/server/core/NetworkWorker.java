package net.johnewart.gearman.server.core;

import io.netty.channel.Channel;
import net.johnewart.gearman.common.interfaces.Worker;
import net.johnewart.gearman.common.packets.Packet;
import net.johnewart.gearman.common.packets.response.NoOp;

import java.util.HashSet;
import java.util.Set;

public class NetworkWorker implements Worker {
    private final Channel channel;
    private final Set<String> abilities;

    public NetworkWorker(Channel channel)
    {
        this.channel = channel;
        this.abilities = new HashSet<>();
    }

    public void send(Packet packet)
    {
        channel.writeAndFlush(packet);
    }

    @Override
    public Set<String> getAbilities() {
        return abilities;
    }

    @Override
    public void addAbility(String ability)
    {
        this.abilities.add(ability);
    }

    @Override
    public void wakeUp()
    {
        send(new NoOp());
    }

}

