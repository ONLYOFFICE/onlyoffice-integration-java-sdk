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

package com.onlyoffice.model.config.document.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onlyoffice.model.config.document.info.saringsettings.SharingSettings;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Info {
    @Deprecated
    private String author;
    @Deprecated
    private String created;
    private Boolean favorite;
    private String folder;
    private String owner;
    private List<SharingSettings> sharingSettings;
    private String uploaded;
}