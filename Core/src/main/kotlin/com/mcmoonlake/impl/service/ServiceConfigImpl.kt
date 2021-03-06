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

package com.mcmoonlake.impl.service

import com.mcmoonlake.api.getMoonLake
import com.mcmoonlake.api.service.ServiceConfig
import org.bukkit.configuration.file.FileConfiguration
import java.io.File

class ServiceConfigImpl : ServiceAbstractCore(), ServiceConfig {

    private val config: FileConfiguration by lazy { getMoonLake().config }

    override fun onInitialized() {
        val dataFolder = getMoonLake().dataFolder
        if(!dataFolder.exists())
            dataFolder.mkdirs()
        val config = File(dataFolder, "config.yml")
        if(!config.exists())
            getMoonLake().saveDefaultConfig()
        else
            checkConfigVersion(config)
    }

    override fun onUnloaded() {
    }

    private fun checkConfigVersion(configFile: File) {
        val version = config.getString("version") ?: null
        if(version != null && version == getMoonLake().pluginVersion)
            return
        getMoonLake().logger.warning("检测到配置文件版本与插件版本不符, 已进行重写覆盖.")
        getMoonLake().saveResource("config.yml", true)
        getMoonLake().reloadConfig()
    }

    override fun reload()
            = getMoonLake().reloadConfig()

    override fun hasVersionCheck(): Boolean
            = config.getBoolean("versionCheck", true)

    override fun hasPacketListener(): Boolean
            = config.getBoolean("packetListenerService", true)
}
