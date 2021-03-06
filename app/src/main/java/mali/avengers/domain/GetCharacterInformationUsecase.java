/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package mali.avengers.domain;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import mali.avengers.model.entities.Character;
import mali.avengers.model.repository.Repository;

public class GetCharacterInformationUsecase implements Usecase<Character> {

    private final Repository mRepository;
    private int mCharacterId;

    @Inject public GetCharacterInformationUsecase(int characterId, Repository repository) {

        mCharacterId = characterId;
        mRepository = repository;
    }

    @Override
    public Observable<Character> execute() {
        return mRepository.getCharacter(mCharacterId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread());
    }
}
