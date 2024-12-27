/**
 *
 * (c) Copyright Ascensio System SIA 2025
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

package com.onlyoffice.model.documenteditor.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlyoffice.model.documenteditor.config.document.Info;
import com.onlyoffice.model.documenteditor.config.document.Permissions;
import com.onlyoffice.model.documenteditor.config.document.ReferenceData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Defines the document parameters.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Document {

    /**
     * Defines the type of the file for the source viewed or edited document.
     * Must be lowercase. The available file types can be found in the
     * <a target="_top"
     * href="https://github.com/ONLYOFFICE/document-formats">Formats repository</a>.
     */
    private String fileType;

    /**
     * Defines the unique document identifier used by the service to recognize the document.
     * In case the known key is sent, the document will be taken from the cache.
     * Every time the document is edited and saved, the key must be generated anew.
     * The document url can be used as the key but without the special characters and the length is limited to
     * 128 symbols.
     */
    private String key;

    /**
     * Defines an object that is generated by the integrator to uniquely identify a file in its system.
     */
    private ReferenceData referenceData;

    /**
     * Defines the desired file name for the viewed or edited document which will also be used as file name when the
     * document is downloaded.
     * The length is limited to 128 symbols.
     */
    private String title;

    /**
     * Defines the absolute URL where the source viewed or edited document is stored.
     * Be sure to add a token when using local links. Otherwise, an error will occur.
     */
    private String url;

    /**
     * Defines the additional parameters for the document.
     */
    private Info info;

    /**
     * Defines the permission for the document to be edited and downloaded or not.
     */
    private Permissions permissions;
}
