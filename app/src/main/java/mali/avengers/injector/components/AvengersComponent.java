/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package mali.avengers.injector.components;

import android.content.Context;

import mali.avengers.injector.Activity;
import mali.avengers.injector.modules.ActivityModule;
import mali.avengers.views.activities.CharacterListListActivity;
import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface AvengersComponent extends ActivityComponent {
    void inject (CharacterListListActivity activity);

    Context activityContext();
}
