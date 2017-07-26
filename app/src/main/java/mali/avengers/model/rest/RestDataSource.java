/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package mali.avengers.model.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import javax.inject.Inject;

import mali.avengers.BuildConfig;
import mali.avengers.model.entities.Character;
import mali.avengers.model.entities.CollectionItem;
import mali.avengers.model.repository.Repository;
import mali.avengers.model.rest.exceptions.ServerErrorException;
import mali.avengers.model.rest.exceptions.UknownErrorException;
import mali.avengers.model.rest.utils.deserializers.MarvelResultsDeserializer;
import mali.avengers.model.rest.utils.interceptors.HttpLoggingInterceptor;
import mali.avengers.model.rest.utils.interceptors.MarvelSigningIterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

public class RestDataSource implements Repository {

    private final MarvelApi mMarvelApi;
    public final static int MAX_ATTEMPS = 3;

    @Inject
    public RestDataSource() {
        OkHttpClient client = new OkHttpClient();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        MarvelSigningIterceptor signingIterceptor = new MarvelSigningIterceptor(
            BuildConfig.MARVEL_PUBLIC_KEY, BuildConfig.MARVEL_PRIVATE_KEY);

        client.interceptors().add(signingIterceptor);
        client.interceptors().add(loggingInterceptor);

        Gson customGsonInstance = new GsonBuilder()
            .registerTypeAdapter(new TypeToken<List<Character>>() {}.getType(),
                new MarvelResultsDeserializer<Character>())

            .registerTypeAdapter(new TypeToken<List<CollectionItem>>() {}.getType(),
                new MarvelResultsDeserializer<CollectionItem>())

            .create();

        Retrofit marvelApiAdapter = new Retrofit.Builder()
            .baseUrl(MarvelApi.END_POINT)
            .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client)
            .build();

        mMarvelApi =  marvelApiAdapter.create(MarvelApi.class);
    }

	@Override
    public Observable<Character> getCharacter(int characterId) {
           return mMarvelApi.getCharacterById(characterId).flatMap(
               characters -> Observable.just(characters.get(0)));
	}

    @Override
    public Observable<List<Character>> getCharacters(int currentOffset) {
        return mMarvelApi.getCharacters(currentOffset)
            .onErrorResumeNext(throwable -> {
                boolean serverError = throwable.getMessage().equals(HttpErrors.SERVER_ERROR);
                return Observable.error((serverError) ? new ServerErrorException() : new UknownErrorException());
            });
    }

    @Override
    public Observable<List<CollectionItem>> getCharacterCollection(int characterId, String type) {
        if (!type.equals(CollectionItem.COMIC) && !type.equals(CollectionItem.EVENT) && !type.equals(CollectionItem.SERIES) && !type.equals(CollectionItem.STORY))
            throw new IllegalArgumentException("Collection type must be: events|series|comics|stories");

        return mMarvelApi.getCharacterCollection(characterId, type);
    }
}
