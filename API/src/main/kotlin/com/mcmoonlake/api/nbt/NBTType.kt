/*
 *  ProtocolLib - Bukkit server library that allows access to the Minecraft protocol.
 *  Copyright (C) 2012 Kristian S. Stangeland
 *
 *  This program is free software; you can redistribute it and/or modify it under the terms of the
 *  GNU General Public License as published by the Free Software Foundation; either version 2 of
 *  the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with this program;
 *  if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 *  02111-1307 USA
 */

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

package com.mcmoonlake.api.nbt

enum class NBTType(
        val id: Int,
        val primitive: Class<*>,
        val reference: Class<*>,
        val mojangsonSuffix: String? = null) {

    /** enums */

    TAG_END(0, java.lang.Void.TYPE, java.lang.Void::class.java),
    TAG_BYTE(1, java.lang.Byte.TYPE, java.lang.Byte::class.java, "b"),
    TAG_SHORT(2, java.lang.Short.TYPE, java.lang.Short::class.java, "s"),
    TAG_INT(3, java.lang.Integer.TYPE, java.lang.Integer::class.java, ""),
    TAG_LONG(4, java.lang.Long.TYPE, java.lang.Long::class.java, "L"),
    TAG_FLOAT(5, java.lang.Float.TYPE, java.lang.Float::class.java, "f"),
    TAG_DOUBLE(6, java.lang.Double.TYPE, java.lang.Double::class.java, "d"),
    TAG_BYTE_ARRAY(7, ByteArray::class.java, ByteArray::class.java),
    TAG_STRING(8, java.lang.String::class.java, java.lang.String::class.java),
    TAG_LIST(9, java.util.List::class.java, java.util.List::class.java),
    TAG_COMPOUND(10, java.util.Map::class.java, java.util.Map::class.java),
    TAG_INT_ARRAY(11, IntArray::class.java, IntArray::class.java),
    ;

    /**
     * @see [mojangsonSuffix]
     */
    fun isNumber(): Boolean
            = this == TAG_BYTE ||
            this == TAG_SHORT ||
            this == TAG_INT ||
            this == TAG_LONG ||
            this == TAG_FLOAT ||
            this == TAG_DOUBLE

    /** static */

    companion object {

        @JvmStatic private val ID_MAP: MutableMap<Int, NBTType> = HashMap()
        @JvmStatic private val CLASS_MAP: MutableMap<Class<*>, NBTType> = HashMap()

        init {
            values().forEach {
                ID_MAP[it.id] = it
                CLASS_MAP[it.primitive] = it
                CLASS_MAP[it.reference] = it
            }
        }

        @JvmStatic
        @JvmName("fromId")
        @Throws(IllegalArgumentException::class)
        fun fromId(id: Int): NBTType
                = ID_MAP[id] ?: throw IllegalArgumentException("未知的 NBT 类型 Id: $id.")

        @JvmStatic
        @JvmName("fromClass")
        @Throws(IllegalArgumentException::class)
        fun fromClass(clazz: Class<*>): NBTType
                = CLASS_MAP[clazz]?: throw IllegalArgumentException("未知的 NBT 类型 Class: $clazz.")
    }
}
