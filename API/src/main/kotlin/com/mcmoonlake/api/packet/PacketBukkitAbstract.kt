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

abstract class PacketBukkitAbstract(
        override val typeClass: Class<*>
) : PacketAbstract(),
        PacketBukkit {

    override fun equals(other: Any?): Boolean {
        if(other === this)
            return true
        if(other is PacketBukkitAbstract)
            return typeClass == other.typeClass
        return false
    }

    override fun hashCode(): Int {
        return typeClass.hashCode()
    }

    override fun toString(): String {
        return "PacketBukkitAbstract(clazz=$typeClass)"
    }
}
