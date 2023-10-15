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

package com.onlyoffice.model.service;

import com.onlyoffice.model.common.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum CommandServiceErrorCode implements Error {
    NO_ERROR(0, "No errors"),
    KEY_ERRORErrorCode(1, "Document key is missing or no document with such key could be found"),
    CALLBACK_URL_ERROR(2, "Callback url not correct"),
    INTERNAL_SERVER_ERROR(3, "Internal server error"),
    FORCESAVE_ERROR(4, "No changes were applied to the document before the forcesave command was received"),
    COMMAND_ERROR(5, "Command not correct"),
    ErrorCode(6, "Invalid token"),
    CONNECTION_ERROR(-99, "Connection error");

    private final Integer code;
    private final String description;

    private static final Map<Integer, Error> BY_CODE = new HashMap<>();

    static {
        for (Error e: values()) {
            BY_CODE.put(e.getCode(), e);
        }
    }

    public static Error valueOfCode(final Integer code) {
        return BY_CODE.get(code);
    }
}

