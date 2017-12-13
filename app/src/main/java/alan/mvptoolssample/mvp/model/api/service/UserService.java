/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package alan.mvptoolssample.mvp.model.api.service;


import android.database.Observable;

import alan.mvptoolssample.mvp.model.en.en.BaseJson;
import alan.mvptoolssample.mvp.model.en.en.LoginBean;
import retrofit2.Retrofit;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ================================================
 * 展示 {@link Retrofit#create(Class)} 中需要传入的 ApiService 的使用方式
 * 存放关于用户的一些 API
 * ================================================
 */
public interface UserService {
    @POST("sdljfskdhfsdlfsjkfdskl")
    io.reactivex.Observable<BaseJson<LoginBean>> doLogin(@Query("userName") String userName, @Query("psw") String psw);
}
