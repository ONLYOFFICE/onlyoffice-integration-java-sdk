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

package com.onlyoffice.manager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlyoffice.manager.settings.SettingsManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Base64;
import java.util.Map;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultJwtManager implements JwtManager {
    private SettingsManager settingsManager;

    private static final long ACCEPT_LEEWAY = 3;

    public String createToken(final Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, ?> payloadMap = objectMapper.convertValue(object, Map.class);

        return createToken(payloadMap, settingsManager.getSecuritySecret());
    }

    public String createToken(final Object object, final String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, ?> payloadMap = objectMapper.convertValue(object, Map.class);

        return createToken(payloadMap, key);
    }

    public String createToken(final JSONObject payload, final String key) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, ?> payloadMap = objectMapper.readValue(payload.toString(), Map.class);

        return createToken(payloadMap, key);
    }

    public String createToken(final Map<String, ?> payloadMap, final String key) {
        Algorithm algorithm = Algorithm.HMAC256(key);

        String token = JWT.create()
                .withPayload(payloadMap)
                .sign(algorithm);

        return token;
    }

    public String verify(final String token) {
        return verifyToken(token, settingsManager.getSecuritySecret());
    }

    public String verifyToken(final String token, final String key) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        Base64.Decoder decoder = Base64.getUrlDecoder();

        DecodedJWT jwt = JWT.require(algorithm)
                .acceptLeeway(ACCEPT_LEEWAY)
                .build()
                .verify(token);

        return new String(decoder.decode(jwt.getPayload()));
    }
}