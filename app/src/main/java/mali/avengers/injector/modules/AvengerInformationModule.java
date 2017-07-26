/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package mali.avengers.injector.modules;

import mali.avengers.domain.GetCharacterInformationUsecase;
import mali.avengers.injector.Activity;
import dagger.Module;
import dagger.Provides;
import mali.avengers.domain.GetCollectionUsecase;
import mali.avengers.model.repository.Repository;

@Module
public class AvengerInformationModule {
    private final int mCharacterId;

    public AvengerInformationModule(int characterId) {
        mCharacterId = characterId;
    }

    @Provides @Activity
    GetCharacterInformationUsecase provideGetCharacterInformationUsecase (Repository repository) {
        return new GetCharacterInformationUsecase(mCharacterId, repository);
    }

    @Provides @Activity GetCollectionUsecase provideGetCharacterComicsUsecase (Repository repository) {
        return new GetCollectionUsecase(mCharacterId, repository);
    }
}
