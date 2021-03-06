/*
 * Copyright (C) 2016-Present The MoonLake (mcmoonlake@hotmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mcmoonlake.api.packet

import com.mcmoonlake.api.event.Cancellable
import io.netty.channel.Channel
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.lang.ref.WeakReference
import java.util.*

class PacketEvent(
        source: Any,
        var packet: PacketBukkit,
        channel: Channel?,
        player: Player?
) : EventObject(source),
        Cancellable {

    private val channelRef: WeakReference<Channel?> = WeakReference(channel)
    private val playerRef: WeakReference<Player?> = WeakReference(player)
    private var cancel = false

    val channel: Channel?
        get() = channelRef.get()

    val player: Player?
        get() = playerRef.get()

    override fun setCancelled(cancel: Boolean)
            { this.cancel = cancel }

    override fun isCancelled(): Boolean
            = cancel

    val isAsync: Boolean
        get() = !Bukkit.isPrimaryThread()

    override fun toString(): String {
        return "PacketEvent(player=$player, packet=$packet)"
    }

    companion object {
        private const val serialVersionUID = -8793261513410637044L
    }
}
