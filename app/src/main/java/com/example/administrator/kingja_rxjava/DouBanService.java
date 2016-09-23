package com.example.administrator.kingja_rxjava;

import com.example.administrator.kingja_rxjava.entiy.HotShowing;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/5/12 16:37
 * 修改备注：
 */
public interface DouBanService {
    @GET("in_theaters")
     Observable<HotShowing> getHotShowing();
  @GET("in_theaters")
  Call<HotShowing> getHotShowingByR();


//    @GET("in_theaters")
//     Observable<HotShowing> getHotShowing(@Query("start") int start, @Query("count") int count);
}
