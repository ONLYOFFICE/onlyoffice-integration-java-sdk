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

package com.onlyoffice.manager.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.onlyoffice.manager.settings.SettingsManager;
import com.onlyoffice.model.common.RequestEntity;
import com.onlyoffice.model.common.RequestedService;
import com.onlyoffice.model.security.Credentials;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

public interface RequestManager {
    /**
     * Executes a POST request to the specified service. The authorization data and the address of the document server
     * are taken from {@link SettingsManager}.
     *
     * @param <R> The result type.
     * @param requestedService The requested service.
     * @param requestEntity The request entity.
     * @param callback The callback method.
     * @see SettingsManager
     * @see RequestedService
     * @see RequestEntity
     * @return The result of the execution callback method.
     */
    <R> R executePostRequest(RequestedService requestedService, RequestEntity requestEntity, Callback<R> callback)
            throws Exception;

    /**
     * Executes a POST request to the specified service. The authorization data and the address of the document server
     * are taken from {@link SettingsManager}.
     *
     * @param <R> The result type.
     * @param url The URL address to the document server.
     * @param requestEntity The request entity.
     * @param credentials The credentials.
     * @param callback The callback method.
     * @see RequestEntity
     * @see Credentials
     * @return The result of the execution callback method.
     */
    <R> R executePostRequest(String url, RequestEntity requestEntity, Credentials credentials, Callback<R> callback)
            throws Exception;


    /**
     * Execute a POST request to the specified service with the {@link HttpUriRequest} parameter.
     *
     * @param <R> The result type.
     * @param request The {@link HttpUriRequest} request.
     * @param callback The callback method.
     * @return The result of the execution callback method.
     */
    <R> R executeRequest(HttpUriRequest request, Callback<R> callback)
            throws Exception;

    /**
     * Returns jwt signed http request.
     *
     * @param url The URL address to the document server.
     * @param requestEntity The request entity.
     * @param credentials The credentials.
     * @return The jwt signed http request.
     * @throws JsonProcessingException
     */
    HttpPost createPostRequest(String url, RequestEntity requestEntity, Credentials credentials)
            throws JsonProcessingException;

    interface Callback<Result> {

        /**
         * The callback method. Implement this method depending on your needs.
         *
         * @param httpEntity The result type.
         * @return The result of the execution callback method.
         */
        Result doWork(HttpEntity httpEntity) throws Exception;
    }
}
