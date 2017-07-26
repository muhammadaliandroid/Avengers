/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package mali.avengers.injector.components;

import dagger.Component;
import mali.avengers.domain.GetCharacterInformationUsecase;
import mali.avengers.domain.GetCollectionUsecase;
import mali.avengers.injector.Activity;
import mali.avengers.injector.modules.ActivityModule;
import mali.avengers.injector.modules.AvengerInformationModule;
import mali.avengers.views.activities.CharacterDetailActivity;
import mali.avengers.views.activities.CollectionActivity;

@Activity
@Component(dependencies = AppComponent.class, modules = {AvengerInformationModule.class, ActivityModule.class})
public interface AvengerInformationComponent extends ActivityComponent {

    void inject (CharacterDetailActivity detailActivity);

    void inject (CollectionActivity detailActivity);

    GetCharacterInformationUsecase getCharacerInformationUsecase();
    GetCollectionUsecase getCollectionUsecase();
}
