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

package com.mcmoonlake.api.particle

/**
 * @author [DarkBlade12](https://github.com/DarkBlade12) by origin, [lgou2w](https://github.com/lgou2w) by modified.
 */
class ParticleColorNote : ParticleColor {

    val note: Int

    @Throws(IllegalArgumentException::class)
    constructor(note: Int) : super() {
        if(note < 0 || note > 24)
            throw IllegalArgumentException("音符颜色不能小于 0 或大于 24 值. (given: $note)")
        this.note = note
    }

    override val valueX: Float
        get() = note.div(24f)

    override val valueY: Float
        get() = 0f

    override val valueZ: Float
        get() = 0f
}
