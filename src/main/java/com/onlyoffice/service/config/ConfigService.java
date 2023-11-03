/**
 *
 * (c) Copyright Ascensio System SIA 2023
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.onlyoffice.service.config;

import com.onlyoffice.model.config.Config;
import com.onlyoffice.model.config.document.info.Info;
import com.onlyoffice.model.config.document.permissions.Permissions;
import com.onlyoffice.model.config.document.referencedata.ReferenceData;
import com.onlyoffice.model.config.document.type.Type;
import com.onlyoffice.model.config.editor.coediting.CoEditing;
import com.onlyoffice.model.config.editor.customization.Customization;
import com.onlyoffice.model.config.editor.embedded.Embedded;
import com.onlyoffice.model.config.editor.mode.Mode;
import com.onlyoffice.model.config.editor.plugins.Plugins;
import com.onlyoffice.model.config.editor.recent.Recent;
import com.onlyoffice.model.config.editor.template.Template;
import com.onlyoffice.model.config.editor.user.User;

import java.util.List;

public interface ConfigService {

    /**
     * Creates a configuration for the document editor using the User-Agent request header.
     *
     * @param fileId The file ID.
     * @param mode The editor opening {@link Mode mode}.
     * @param userAgent The User-Agent request header that is used to determine the platform type ("desktop" or "mobile").
     * @return The {@link Config} object.
     */
    Config createConfig(String fileId, Mode mode, String userAgent);

    /**
     * Creates a configuration for the document editor using the {@link Type} parameter.
     *
     * @param fileId The file ID.
     * @param mode The editor opening {@link Mode mode}.
     * @param type The platform {@link Type type} used to access the document.
     * @return The {@link Config} object.
     */
    Config createConfig(String fileId, Mode mode, Type type);

    /**
     * Returns the {@link ReferenceData} object.
     *
     * @param fileId The file ID.
     * @return The {@link ReferenceData} object.
     */
    ReferenceData getReferenceData(String fileId);

    /**
     * Returns the {@link Info} object.
     *
     * @param fileId The file ID.
     * @return The {@link Info} object.
     */
    Info getInfo(String fileId);

    /**
     * Returns the {@link Permissions} object.
     *
     * @param fileId The file ID.
     * @return The {@link Permissions} object.
     */
    Permissions getPermissions(String fileId);

    /**
     * Returns the {@link CoEditing} object.
     *
     * @param object
     * @return The {@link CoEditing} object.
     */
    CoEditing getCoEditing(Object object);

    /**
     * Returns a list of the {@link Recent} objects.
     *
     * @param object
     * @return A list of the {@link Recent} objects.
     */
    List<Recent> getRecent(Object object);

    /**
     * Returns a list of the {@link Template} objects.
     *
     * @param fileId The file ID.
     * @return A list of the {@link Template} objects.
     */
    List<Template> getTemplates(String fileId);

    /**
     * Returns the {@link User} object.
     *
     * @return The {@link User} object.
     */
    User getUser();

    /**
     * Returns the {@link Customization} object.
     *
     * @param fileId The file ID.
     * @return The {@link Customization} object.
     */
    Customization getCustomization(String fileId);

    /**
     * Returns the {@link Embedded} object.
     *
     * @param fileId The file ID.
     * @return The {@link Embedded} object.
     */
    Embedded getEmbedded(String fileId);

    /**
     * Returns the {@link Plugins} object.
     *
     * @param object
     * @return The {@link Plugins} object.
     */
    Plugins getPlugins(Object object);

    /**
     * Returns the {@link Type} object.
     *
     * @param userAgent The User-Agent request header that is used to determine the platform type ("desktop" or "mobile").
     * @return The {@link Type} object.
     */
    Type getType(String userAgent);
}
