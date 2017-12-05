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

package com.mcmoonlake.api.event

import com.mcmoonlake.api.player.MoonLakePlayer

/**
 * ## MoonLakePlayerEvent (月色之湖玩家事件)
 *
 * @see [MoonLakeEvent]
 * @author lgou2w
 * @since 2.0
 * @constructor MoonLakePlayerEvent
 * @param player MoonLake Player.
 * @param player 月色之湖玩家.
 * @param isAsync Whether this event is executed asynchronously.
 * @param isAsync 此事件是否为异步执行.
 */
abstract class MoonLakePlayerEvent(
        /**
         * * This event moonlake player object.
         * * 此事件的月色之湖玩家对象.
         */
        val player: MoonLakePlayer,
        isAsync: Boolean = false) : MoonLakeEvent(isAsync) {
}
