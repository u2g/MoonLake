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

package com.minecraft.moonlake.api.block

import com.minecraft.moonlake.api.util.ComparisonChain

data class BlockPosition(val x: Int, val y: Int, val z: Int) : Comparable<BlockPosition> {

    override fun compareTo(other: BlockPosition): Int
            = ComparisonChain.start()
            .compare(x, other.x)
            .compare(y, other.y)
            .compare(z, other.z)
            .result

    companion object {

        @JvmField
        val ZERO = BlockPosition(0, 0, 0)
    }
}